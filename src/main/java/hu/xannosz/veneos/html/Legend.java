package hu.xannosz.veneos.html;

public class Legend extends HtmlComponent {

	private String legend;

	public Legend(String legend) {
		this.legend = legend;
	}

	@Override
	protected String getTag() {
		return "legend";
	}

	@Override
	protected String getContent() {
		return legend;
	}
}
