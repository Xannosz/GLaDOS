package hu.xannosz.veneos.html;

public class Img extends SingleHtmlComponent {

	public Img(String src) {
		meta.put("src", src);
	}

	public void setAlt(String alt) {
		meta.put("alt", alt);
	}

	public void setHeight(String height) {
		meta.put("height", height);
	}

	public void setWidth(String width) {
		meta.put("width", width);
	}

	@Override
	protected String getTag() {
		return "img";
	}

}
