package hu.xannosz.veneos.html;

import java.util.ArrayList;

import hu.xannosz.veneos.core.Theme;
import lombok.Getter;
import lombok.Setter;

public class Head extends HtmlComponent {

	@Setter
	private String title;
	@Getter
	@Setter
	private String charset;
	private java.util.List<Theme> themes = new ArrayList<>();

	public void addTheme(Theme theme) {
		themes.add(theme);
	}

	private String getTitleSyntax() {
		return "<title>" + title + "</title>";
	}

	private String getThemeSyntax() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Theme theme : themes) {
			stringBuilder.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/");
			stringBuilder.append(theme.getId());
			stringBuilder.append("\">");
		}
		return stringBuilder.toString();
	}

	private String getCharsetSyntax() {
		return "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=" + charset + "\">";
	}

	@Override
	protected String getTag() {
		return "head";
	}

	@Override
	protected String getContent() {
		return getCharsetSyntax() + getTitleSyntax() + getThemeSyntax();
	}
}
