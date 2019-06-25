package hu.xannosz.veneos.core;

import java.util.Map;

import hu.xannosz.microtools.pack.Douplet;

public interface HttpHandler {
	public Douplet<Integer, Page> getResponse(RequestMethod requestMethod, String requestURI,
			Map<String, String> requestMap);

	public enum RequestMethod {
		GET, POST
	}
}
