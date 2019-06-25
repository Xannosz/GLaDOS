package hu.xannosz.veneos.css;

public class StringCssComponent extends CssComponent {

	private String component;

	public StringCssComponent(Selector selector, String component) {
		super(selector);
		this.component = component;
	}

	@Override
	public String getSyntax() {
		return component;
	}
}
