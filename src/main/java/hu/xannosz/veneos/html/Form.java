package hu.xannosz.veneos.html;

public class Form extends AbstractBox{
	
	public Form(String action){
		meta.put("action", action);
		meta.put("method", "post");
	}

	@Override
	protected String getTag() {
		return "form";
	}

}
