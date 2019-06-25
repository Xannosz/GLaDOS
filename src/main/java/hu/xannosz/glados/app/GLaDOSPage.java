package hu.xannosz.glados.app;

import java.util.Map.Entry;

import hu.xannosz.glados.core.ComponentCheckResult;
import hu.xannosz.glados.core.Manager;
import hu.xannosz.veneos.core.Page;
import hu.xannosz.veneos.core.Theme;
import hu.xannosz.veneos.html.A;
import hu.xannosz.veneos.html.Nav;
import hu.xannosz.veneos.html.Section;

public class GLaDOSPage {

	private Manager manager;
	private Theme theme;

	public GLaDOSPage(Manager manager, Theme theme) {
		this.manager = manager;
		this.theme = theme;
	}

	public Page getPage(String name) {
		Page page = new Page();
		page.setTitle("GLaDOS UI: " + name);
		page.addTheme(theme);
		Nav nav = new Nav();
		Section section = null;
		for (Entry<String, ComponentCheckResult> result : manager.getResults().entrySet()) {
			String link = format(result.getKey());
			if (name.isEmpty()) {
				name = link;
			}
			if (link.equals(name)) {
				section = result.getValue().getSection();
				nav.add(new A("#", result.getKey()));
			} else {
				nav.add(new A(link, result.getKey()));
			}
		}
		page.addComponent(nav);
		page.addComponent(section);

		return page;
	}

	private String format(String string) {
		return string.toLowerCase().replace(" ", "_");
	}

}
