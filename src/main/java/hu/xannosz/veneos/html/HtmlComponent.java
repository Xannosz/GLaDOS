package hu.xannosz.veneos.html;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class HtmlComponent {

	protected Map<String, String> meta = new HashMap<>();

	protected abstract String getTag();

	protected abstract String getContent();

	protected String getMetaSyntax() {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> metaTag : meta.entrySet()) {
			builder.append(metaTag.getKey());
			builder.append("=\"");
			builder.append(metaTag.getValue());
			builder.append("\"");
		}
		return builder.toString();
	}

	public String getSyntax() {
		return "<" + getTag() + " " + getMetaSyntax() + ">" + getContent() + "</" + getTag() + ">";
	}
}
