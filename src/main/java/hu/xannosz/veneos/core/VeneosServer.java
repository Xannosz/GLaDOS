package hu.xannosz.veneos.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import hu.xannosz.microtools.pack.Douplet;
import hu.xannosz.veneos.core.HttpHandler.RequestMethod;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("restriction")
public class VeneosServer {

	@Setter
	private hu.xannosz.veneos.core.HttpHandler handler;
	@Getter
	@Setter
	private String encoding = "UTF-8";

	public void createServer(int port) {
		HttpServer server;
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/", new MainHandler());
			server.createContext("/files", new FileHandler());
			server.createContext("/css", new CSSHandler());
			server.setExecutor(null);
			server.start();
		} catch (IOException e) {
			System.out.println(e); // TODO
		}
	}

	public class MainHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			Douplet<Integer, Page> response = handler.getResponse(
					t.getRequestMethod().equals("GET") ? RequestMethod.GET : RequestMethod.POST,
					t.getRequestURI().getPath(), getRequestMap(t.getRequestBody()));
			response.getSecond().setCharset(encoding);
			byte[] responseSyntax = response.getSecond().getSyntax().getBytes(encoding);
			t.sendResponseHeaders(response.getFirst(), responseSyntax.length);
			OutputStream os = t.getResponseBody();
			os.write(responseSyntax);
			os.flush();
			os.close();
		}

		private Map<String, String> getRequestMap(InputStream requestBody) {
			StringWriter writer = new StringWriter();
			Map<String, String> result = new HashMap<>();

			try {
				IOUtils.copy(requestBody, writer, encoding);
			} catch (IOException e) {
				System.out.println(e); // TODO
			}

			for (String field : writer.toString().split("&")) {
				String[] fields = field.split("=");
				if (fields.length == 1) {
					result.put(characterRecombinator(fields[0]), "");
				} else if (fields.length == 2) {
					result.put(characterRecombinator(fields[0]), characterRecombinator(fields[1]));
				}
			}

			return result;
		}

		private String characterRecombinator(String input) {
			try {
				return URLDecoder.decode(input, encoding);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();// TODO
				return input;
			}
		}
	}

	public class CSSHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			String[] tags = t.getRequestURI().getPath().split("/");
			String id = tags[tags.length - 1];
			Theme theme = ThemeHandler.getTheme(id);
			Douplet<Integer, String> response;
			if (theme != null) {
				response = new Douplet<>(200, theme.getSyntax());
			} else {
				response = new Douplet<>(404, id + " theme not found.");
			}
			byte[] responseSyntax = response.getSecond().getBytes(encoding);
			t.sendResponseHeaders(response.getFirst(), responseSyntax.length);
			OutputStream os = t.getResponseBody();
			os.write(responseSyntax);
			os.flush();
			os.close();
		}
	}

	static class FileHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {

			// add the required response header for a PDF file
			Headers h = t.getResponseHeaders();
			h.add("Content-Type", "application/pdf");

			// a PDF (you provide your own!)
			File file = new File("c:/temp/doc.pdf");
			byte[] bytearray = new byte[(int) file.length()];
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(bytearray, 0, bytearray.length);

			// ok, we are ready to send the response.
			t.sendResponseHeaders(200, file.length());
			OutputStream os = t.getResponseBody();
			os.write(bytearray, 0, bytearray.length);
			os.close();
			bis.close();
		}
	}
}