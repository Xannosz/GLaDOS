package hu.xannosz.glados.app;

import java.util.Map;

import hu.xannosz.glados.core.Manager;
import hu.xannosz.microtools.pack.Douplet;
import hu.xannosz.veneos.core.HttpHandler;
import hu.xannosz.veneos.core.Page;
import hu.xannosz.veneos.core.Theme;
import hu.xannosz.veneos.core.ThemeHandler;
import hu.xannosz.veneos.css.CssAttribute;
import hu.xannosz.veneos.css.CssComponent;
import hu.xannosz.veneos.css.Selector;

public class RequestHandler implements HttpHandler {

	private Manager manager;
	private Theme theme = new Theme();

	public RequestHandler(Manager manager) {
		this.manager = manager;
		theme.add(new CssComponent(new Selector("nav")).addAttribute(CssAttribute.HEIGHT.set("100%"))
				.addAttribute(CssAttribute.WIDTH.set("250px")).addAttribute(CssAttribute.POSITION.set("fixed"))
				.addAttribute(CssAttribute.Z_INDEX.set("1")).addAttribute(CssAttribute.TOP.set("0"))
				.addAttribute(CssAttribute.LEFT.set("0")).addAttribute(CssAttribute.BACKGROUND_COLOR.set("#111"))
				.addAttribute(CssAttribute.OVERFLOW_X.set("hidden"))
				.addAttribute(CssAttribute.PADDING_TOP.set("60px")));
		theme.add(new CssComponent(new Selector("nav a")).addAttribute(CssAttribute.PADDING.set("8px 8px 8px 32px"))
				.addAttribute(CssAttribute.TEXT_DECORATION.set("none")).addAttribute(CssAttribute.FONT_SIZE.set("25px"))
				.addAttribute(CssAttribute.COLOR.set("#00ff00")).addAttribute(CssAttribute.DISPLAY.set("block"))
				.addAttribute(CssAttribute.TRANSITION.set("0.3s")));
		theme.add(new CssComponent(new Selector("nav a:hover")).addAttribute(CssAttribute.COLOR.set("#f1f1f1")));
		ThemeHandler.registerTheme(theme);
	}

	@Override
	public Douplet<Integer, Page> getResponse(RequestMethod requestMethod, String requestURI,
			Map<String, String> requestMap) {
		String site = requestURI.replace("/", "").replace("#", "");
		if (requestMethod == RequestMethod.GET) {
			GLaDOSPage page = new GLaDOSPage(manager, theme);
			return new Douplet<Integer, Page>(200, page.getPage(site));
		} else {
			return null;
		}
	}
}
