package hu.xannosz.veneos.html;

public class H extends HtmlComponent {

	private int num;
	private String header;

	public H(int num, String header) {
		if (num < 1) {
			this.num = 1;
		} else if (num > 6) {
			this.num = 6;
		} else {
			this.num = num;
		}
		this.header = header;
	}

	@Override
	protected String getTag() {
		return "h" + num;
	}

	@Override
	protected String getContent() {
		return header;
	}
}
