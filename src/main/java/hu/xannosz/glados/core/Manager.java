package hu.xannosz.glados.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;

import hu.xannosz.glados.component.Component;
import hu.xannosz.glados.component.DocumentOrdererComponent;
import hu.xannosz.glados.component.LoggerComponent;
import hu.xannosz.microtools.Sleep;
import lombok.Getter;

public class Manager {

	private LoggerComponent logger = new LoggerComponent(this);

	@Getter
	private List<Component> components = Arrays.asList(logger, new DocumentOrdererComponent(this));

	public void run() {
		for (int i = 1;; i++) {
			log("Manager", "Run state: " + i);
			for (Component component : components) {
				if (component.isActive()) {
					try {
						component.tick();
					} catch (Exception e) {
						log("Manager", "Exception in component: " + component.getName() + " Exception: "
								+ ExceptionUtils.getFullStackTrace(e));
					}
				}
			}
			Sleep.sleepSeconds(10);
		}
	}

	public void log(String name, String log) {
		logger.log(name, log);
	}

	public Map<String, ComponentCheckResult> getResults() {
		Map<String, ComponentCheckResult> results = new HashMap<>();
		for (Component component : components) {
			results.put(component.getName(), component.check());
		}
		return results;
	}
}
