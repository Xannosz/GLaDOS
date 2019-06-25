package hu.xannosz.veneos.html;

public class StringHtmlComponent extends HtmlComponent {

	private String component;

	public StringHtmlComponent(String component) {
		this.component = component;
	}

	@Override
	protected String getTag() {
		return null;
	}

	@Override
	protected String getContent() {
		return null;
	}

	@Override
	public String getSyntax() {
		return component;
	}
}
