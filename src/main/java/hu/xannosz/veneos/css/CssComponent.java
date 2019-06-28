package hu.xannosz.veneos.css;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CssComponent {

	private Selector selector;
	private Map<CssAttribute, String> attributes = new HashMap<>();

	public CssComponent(Selector selector) {
		this.selector = selector;
	}

	public CssComponent addAttribute(CssAttribute attribute, String value) {
		attributes.put(attribute, value);
		return this;
	}

	public String getSyntax() {
		StringBuilder builder = new StringBuilder();
		for (Entry<CssAttribute, String> attribute : attributes.entrySet()) {
			builder.append(attribute.getKey().getSyntax());
			builder.append(": ");
			builder.append(attribute.getValue());
			builder.append(";");
		}
		return selector.getSyntax() + " {" + builder.toString() + "}";
	}

}
