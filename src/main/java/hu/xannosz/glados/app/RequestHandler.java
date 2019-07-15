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
		theme.add(new CssComponent(new Selector("nav")).addAttribute(CssAttribute.HEIGHT, "100%")
				.addAttribute(CssAttribute.WIDTH, "15%").addAttribute(CssAttribute.POSITION, "fixed")
				.addAttribute(CssAttribute.Z_INDEX, "1").addAttribute(CssAttribute.TOP, "0")
				.addAttribute(CssAttribute.LEFT, "0").addAttribute(CssAttribute.BACKGROUND_COLOR, "#073642")
				.addAttribute(CssAttribute.OVERFLOW_X, "hidden").addAttribute(CssAttribute.PADDING_TOP, "60px"));
		theme.add(new CssComponent(new Selector("nav a")).addAttribute(CssAttribute.PADDING, "3px")
				.addAttribute(CssAttribute.TEXT_DECORATION, "none").addAttribute(CssAttribute.FONT_SIZE, "25px")
				.addAttribute(CssAttribute.COLOR, "#2aa198").addAttribute(CssAttribute.DISPLAY, "block")
				.addAttribute(CssAttribute.TRANSITION, "0.3s").addAttribute(CssAttribute.BORDER, "2px")
				.addAttribute(CssAttribute.BORDER_STYLE, "solid").addAttribute(CssAttribute.MARGIN, "10px")
				.addAttribute(CssAttribute.BORDER_RADIUS, "25px").addAttribute(CssAttribute.TEXT_ALIGN, "center"));
		theme.add(new CssComponent(new Selector("nav a:hover")).addAttribute(CssAttribute.COLOR, "#268bd2"));
		theme.add(new CssComponent(new Selector("nav a[href=\"#\"]")).addAttribute(CssAttribute.COLOR, "#6c71c4"));
		theme.add(new CssComponent(new Selector("body")).addAttribute(CssAttribute.BACKGROUND_COLOR, "#002b36")
				.addAttribute(CssAttribute.COLOR, "#2aa198").addAttribute(CssAttribute.MARGIN, "2% 2% 2% 18%"));
		theme.add(new CssComponent(new Selector("table")).addAttribute(CssAttribute.WIDTH, "80%"));
		theme.add(new CssComponent(new Selector("th")).addAttribute(CssAttribute.COLOR, "#6c71c4")
				.addAttribute(CssAttribute.PADDING, "2%"));
		theme.add(new CssComponent(new Selector("td")).addAttribute(CssAttribute.PADDING, "2%")
				.addAttribute(CssAttribute.TEXT_ALIGN, "center"));
		theme.add(new CssComponent(new Selector("tr:nth-child(even)"))
				.addAttribute(CssAttribute.BACKGROUND_COLOR, "#073642")
				.addAttribute(CssAttribute.TEXT_ALIGN, "center"));
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
