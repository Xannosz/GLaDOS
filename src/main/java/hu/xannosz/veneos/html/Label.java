package hu.xannosz.veneos.html;

public class Label extends HtmlComponent {

	private HtmlComponent element;

	public Label(String fora, HtmlComponent element) {
		this.element = element;
		meta.put("for", fora);
	}

	public Label(String fora, String element) {
		this.element = new StringHtmlComponent(element);
		meta.put("for", fora);
	}

	@Override
	protected String getTag() {
		return "label";
	}

	@Override
	protected String getContent() {
		return element.getSyntax();
	}

}
