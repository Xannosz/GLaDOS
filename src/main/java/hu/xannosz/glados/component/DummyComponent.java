package hu.xannosz.glados.component;

import hu.xannosz.glados.core.ComponentCheckResult;
import hu.xannosz.glados.core.Manager;
import hu.xannosz.veneos.html.Section;

public class DummyComponent extends Component {

	public DummyComponent(Manager manager, String name) {
		super(manager, name);
	}

	@Override
	protected ComponentCheckResult getCheckResult() {
		ComponentCheckResult result = new ComponentCheckResult();
		result.setSection(new Section());
		return result;
	}

	@Override
	protected void run() {
	}

}
