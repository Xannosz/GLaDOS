package hu.xannosz.veneos.html;

import java.util.ArrayList;

public class List extends HtmlComponent {

	private ListType type;
	private java.util.List<HtmlComponent> components = new ArrayList<>();

	public List(ListType type) {
		this.type = type;
	}

	public void add(HtmlComponent component) {
		components.add(component);
	}

	public void add(String component) {
		components.add(new StringHtmlComponent(component));
	}

	@Override
	protected String getTag() {
		if (type == ListType.OL) {
			return "ol";
		}
		return "ul";
	}

	@Override
	protected String getContent() {
		StringBuilder builder = new StringBuilder();
		for (HtmlComponent component : components) {
			builder.append("<li>");
			builder.append(component.getSyntax());
			builder.append("</li>");
		}
		return builder.toString();
	}

	public enum ListType {
		OL, UL
	}
}
