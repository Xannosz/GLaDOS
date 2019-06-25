package hu.xannosz.veneos.css;

import java.util.ArrayList;
import java.util.List;

public class CssComponent {

	private Selector selector;
	private List<CssAttribute> attributes = new ArrayList<>();

	public CssComponent(Selector selector) {
		this.selector = selector;
	}

	public CssComponent addAttribute(CssAttribute attribute) {
		attributes.add(attribute);
		return this;
	}

	public String getSyntax() {
		StringBuilder builder = new StringBuilder();
		for (CssAttribute attribute : attributes) {
			builder.append(attribute.getSyntax());
		}
		return selector.getSyntax() + " {" + builder.toString() + "}";
	}

}
