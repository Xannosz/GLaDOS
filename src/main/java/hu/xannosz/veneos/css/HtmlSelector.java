package hu.xannosz.veneos.css;

public enum HtmlSelector {
	A, ABBR, ADDRESS, AREA, ARTICLE, ASIDE, B, BDI, BDO, BLOCKQUOTE, BODY, BUTTON, CANVAS, CAPTION, CITE, CODE, COL, COLGROUP, //
	DATALIST, DD, DEL, DETAILS, DFN, DIALOG, DIV, DL, DT, EM, FIELDSET, FIGCAPTION, FIGURE, FOOTER, FORM, HEADER, H1, H2, H3, H4, H5, H6, //
	I, IFRAME, IMG, INPUT, INS, KBD, LABEL, LEGEND, LI, MAIN, MAP, MARK, METER, NAV, NOSCRIPT, OL, OPTGROUP, OPTION, OUTPUT, P, PICTURE, PRE, PROGRESS, //
	Q, RP, RT, RUBY, S, SAMP, SECTION, SELECT, SMALL, STRONG, SUB, SUMMARY, SUP, TABLE, TBODY, TD, TEXTAREA, TFOOT, TH, THEAD, TIME, TR, U, UL, VAR;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
