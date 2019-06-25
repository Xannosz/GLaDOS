package hu.xannosz.glados.app;

import hu.xannosz.glados.core.Manager;
import hu.xannosz.veneos.core.VeneosServer;

public class App {
	public static void main(String[] args) {
		Manager manager = new Manager();
		VeneosServer server = new VeneosServer();
		server.setHandler(new RequestHandler(manager));
		server.createServer(8000);
		manager.run();
	}
}
