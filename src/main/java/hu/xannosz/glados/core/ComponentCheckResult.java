package hu.xannosz.glados.core;

import hu.xannosz.veneos.html.Section;
import lombok.Getter;
import lombok.Setter;

public class ComponentCheckResult {
	@Getter
	@Setter
	private boolean isActive = false;
	@Getter
	@Setter
	private String componentName;
	@Getter
	@Setter
	private Section section;
}
