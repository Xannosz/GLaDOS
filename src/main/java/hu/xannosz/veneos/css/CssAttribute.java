package hu.xannosz.veneos.css;

public enum CssAttribute {
	HEIGHT, WIDTH, POSITION, Z_INDEX, TOP, LEFT, BACKGROUND_COLOR, OVERFLOW_X, PADDING_TOP, PADDING, //
	TEXT_DECORATION, DISPLAY, COLOR, FONT_SIZE, TRANSITION, BORDER, BORDER_STYLE, MARGIN, BORDER_RADIUS, TEXT_ALIGN;

	public String getSyntax() {
		return this.toString().toLowerCase().replaceAll("_", "-");
	}
}
