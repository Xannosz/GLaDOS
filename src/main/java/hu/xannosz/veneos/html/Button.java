package hu.xannosz.veneos.html;

public class Button extends HtmlComponent {

	private HtmlComponent element;

	public Button(HtmlComponent element) {
		this.element = element;
		meta.put("type", "button");
	}

	public Button(String element) {
		this.element = new StringHtmlComponent(element);
		meta.put("type", "button");
	}

	@Override
	protected String getTag() {
		return "button";
	}

	@Override
	protected String getContent() {
		return element.getSyntax();
	}
}
