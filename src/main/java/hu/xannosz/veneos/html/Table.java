package hu.xannosz.veneos.html;

import java.util.ArrayList;

public class Table extends HtmlComponent {

	private java.util.List<java.util.List<HtmlComponent>> rows = new ArrayList<>();
	private java.util.List<HtmlComponent> actualRow = new ArrayList<>();
	private java.util.List<HtmlComponent> headRow = new ArrayList<>();

	public Table newRow() {
		if (!actualRow.isEmpty()) {
			rows.add(actualRow);
			actualRow = new ArrayList<>();
		}
		return this;
	}

	public Table add(HtmlComponent component) {
		actualRow.add(component);
		return this;
	}

	public Table add(String component) {
		return add(new StringHtmlComponent(component));
	}

	public Table addHead(HtmlComponent component) {
		headRow.add(component);
		return this;
	}

	public Table addHead(String component) {
		return addHead(new StringHtmlComponent(component));
	}

	@Override
	protected String getTag() {
		return "table";
	}

	@Override
	protected String getContent() {
		newRow();
		StringBuilder builder = new StringBuilder();
		builder.append("<tr>");
		for (HtmlComponent comp : headRow) {
			builder.append("<th>");
			builder.append(comp.getSyntax());
			builder.append("</th>");
		}
		builder.append("</tr>");
		for (java.util.List<HtmlComponent> row : rows) {
			builder.append("<tr>");
			for (HtmlComponent comp : row) {
				builder.append("<td>");
				builder.append(comp.getSyntax());
				builder.append("</td>");
			}
			builder.append("</tr>");
		}
		return builder.toString();

	}

}
