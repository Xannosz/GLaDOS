package hu.xannosz.veneos.html;

public class Input extends SingleHtmlComponent {

	public Input(String type) {
		meta.put("type", type);
	}

	public void setName(String name) {
		meta.put("name", name);
	}

	public void setValue(String value) {
		meta.put("value", value);
	}

	@Override
	protected String getTag() {
		return "input";
	}

}
