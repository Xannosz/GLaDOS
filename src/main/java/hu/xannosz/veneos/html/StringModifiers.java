package hu.xannosz.veneos.html;

public enum StringModifiers {

	BR("br"), B("b"), CITE("cite"), CODE("code"), DFN("dfn"), EM("em"), HR("hr"), I("i"), MARK("mark"), //
	Q("q"), S("s"), SAMP("samp"), SMALL("small"), //
	STRONG("strong"), SUB("sub"), SUP("sup"), TIME("time"), U("u"), WBR("wbr");

	private String tag;

	private StringModifiers(String tag) {
		this.tag = tag;
	}

	public String getSyntax(String element) {
		return "<" + tag + ">" + element + "</" + tag + ">";
	}

	@Override
	public String toString() {
		return "<" + tag + ">";
	}
}
