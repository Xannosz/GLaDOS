package hu.xannosz.veneos.html;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBox extends HtmlComponent {

	private List<HtmlComponent> components = new ArrayList<>();

	public void add(HtmlComponent component) {
		components.add(component);
	}

	@Override
	protected String getContent() {
		StringBuilder builder = new StringBuilder();
		for (HtmlComponent component : components) {
			builder.append(component.getSyntax());
		}
		return builder.toString();
	}
}
