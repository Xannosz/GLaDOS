package hu.xannosz.veneos.html;

public class Option extends HtmlComponent {

	private HtmlComponent element;

	public Option(String value, HtmlComponent element) {
		this.element = element;
		meta.put("value", value);
	}

	public Option(String value, String element) {
		this.element = new StringHtmlComponent(element);
		meta.put("value", value);
	}

	@Override
	protected String getTag() {
		return "option";
	}

	@Override
	protected String getContent() {
		return element.getSyntax();
	}

}
