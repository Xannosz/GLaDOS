package hu.xannosz.veneos.css;

public enum CssAttribute {
	HEIGHT, WIDTH, POSITION, Z_INDEX, TOP, LEFT, BACKGROUND_COLOR, OVERFLOW_X, PADDING_TOP, PADDING, //
	TEXT_DECORATION, DISPLAY, COLOR, FONT_SIZE, TRANSITION;

	private String attr;

	public CssAttribute set(String attr) {
		this.attr = attr;
		return this;
	}

	public String getSyntax() {
		return this.toString().toLowerCase().replaceAll("_", "-") + ":" + attr + ";";
	}
}
