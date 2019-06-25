package hu.xannosz.veneos.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import hu.xannosz.veneos.css.CssComponent;
import lombok.Getter;

public class Theme {

	@Getter
	private String id = UUID.randomUUID().toString();
	private List<CssComponent> components = new ArrayList<>();

	public void add(CssComponent component) {
		components.add(component);
	}

	public String getSyntax() {
		StringBuilder builder = new StringBuilder();
		for (CssComponent component : components) {
			builder.append(component.getSyntax());
		}
		return builder.toString();
	}
}
