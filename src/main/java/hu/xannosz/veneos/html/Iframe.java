package hu.xannosz.veneos.html;

public class Iframe extends HtmlComponent {

	public Iframe(String src) {
		meta.put("src", src);
	}

	@Override
	protected String getTag() {
		return "iframe";
	}

	@Override
	protected String getContent() {
		return "";
	}

}
