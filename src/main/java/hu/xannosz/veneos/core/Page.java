package hu.xannosz.veneos.core;

import hu.xannosz.veneos.html.Body;
import hu.xannosz.veneos.html.Head;
import hu.xannosz.veneos.html.HtmlComponent;

public class Page {

	private Head head = new Head();
	private Body body = new Body();

	public void setTitle(String title) {
		head.setTitle(title);
	}

	public void setCharset(String charset) {
		head.setCharset(charset);
	}

	public void addTheme(Theme theme) {
		head.addTheme(theme);
	}

	public void addComponent(HtmlComponent component) {
		body.add(component);
	}

	public String getSyntax() {
		return "<!DOCTYPE html><html>" + head.getSyntax() + body.getSyntax() + "</html>";
	}
}
