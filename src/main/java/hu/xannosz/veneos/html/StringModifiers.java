package hu.xannosz.veneos.html;

public enum StringModifiers {

	BR("br"), B("b"), BDI("bdi"), CITE("cite"), CODE("code"), DEL("del"), DFN("dfn"), EM("em"), HR("hr"), I("i"), //
	INS("ins"), KBD("kbd"),MARK("mark"), PRE("pre"),Q("q"), S("s"), SAMP("samp"), SMALL("small"), //
	STRONG("strong"), SUB("sub"), SUP("sup"), TIME("time"), U("u"),VAR("var"), WBR("wbr");

	private String tag;

	private StringModifiers(String tag) {
		this.tag = tag;
	}

	public String set(String element) {
		return "<" + tag + ">" + element + "</" + tag + ">";
	}

	@Override
	public String toString() {
		return "<" + tag + ">";
	}
}
