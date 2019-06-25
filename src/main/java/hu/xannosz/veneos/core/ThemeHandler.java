package hu.xannosz.veneos.core;

import java.util.HashMap;
import java.util.Map;

public class ThemeHandler {

	private static Map<String, Theme> themes = new HashMap<>();

	public static void registerTheme(Theme theme) {
		themes.put(theme.getId(), theme);
	}

	public static Theme getTheme(String id) {
		return themes.get(id);
	}
}
