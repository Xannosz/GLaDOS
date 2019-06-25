package hu.xannosz.veneos.html;

public class Paragraph extends HtmlComponent {

	private String paragraph;

	public Paragraph(String paragraph) {
		this.paragraph = paragraph;
	}

	@Override
	protected String getTag() {
		return "p";
	}

	@Override
	protected String getContent() {
		return paragraph;
	}
}
