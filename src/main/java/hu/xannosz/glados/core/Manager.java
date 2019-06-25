package hu.xannosz.glados.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.xannosz.glados.component.Component;
import hu.xannosz.glados.component.DummyComponent;
import lombok.Getter;
import lombok.Setter;

public class Manager {

	private List<Component> components = Arrays.asList(new DummyComponent(this, "Dummy1"),
			new DummyComponent(this, "Dummy ketto"));
	@Getter
	@Setter
	private Map<String, ComponentCheckResult> results = new HashMap<>();

	public void run() {
		for (Component component : components) {
			if (component.isActive()) {
				component.tick();
			}
			results.put(component.getName(), component.check());
		}
	}
}
