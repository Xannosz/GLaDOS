package hu.xannosz.glados.component;

import hu.xannosz.glados.core.ComponentCheckResult;
import hu.xannosz.glados.core.Manager;
import lombok.Getter;
import lombok.Setter;

public abstract class Component {

	private int actualTick = 0;
	@Getter
	@Setter
	private boolean isActive = false;

	@Getter
	@Setter
	protected String name;
	protected int tickSpeed = 1;
	protected Manager manager;

	public Component(Manager manager, String name) {
		this.manager = manager;
		this.name = name;
	}

	public void tick() {
		actualTick++;
		if (actualTick >= tickSpeed) {
			actualTick = 0;
			run();
		}
	}

	public ComponentCheckResult check() {
		ComponentCheckResult result = getCheckResult();
		result.setComponentName(name);
		result.setActive(isActive);
		return result;
	}

	protected abstract ComponentCheckResult getCheckResult();

	protected abstract void run();
}
