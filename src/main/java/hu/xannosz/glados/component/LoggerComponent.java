package hu.xannosz.glados.component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hu.xannosz.glados.core.ComponentCheckResult;
import hu.xannosz.glados.core.Manager;
import hu.xannosz.veneos.html.Section;
import hu.xannosz.veneos.html.Table;

public class LoggerComponent extends Component {

	private List<LogRow> logs = new ArrayList<>();

	public LoggerComponent(Manager manager) {
		super(manager, "Logger");
	}

	public void log(String name, String log) {
		LogRow row = new LogRow();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS");
		row.name = name;
		row.log = log;
		row.time = sdf.format(cal.getTime());
		logs.add(row);
	}

	@Override
	protected ComponentCheckResult getCheckResult() {
		ComponentCheckResult result = new ComponentCheckResult();
		Table table = new Table();
		table.addHead("Name").addHead("Log").addHead("Time");
		for (LogRow log : logs) {
			table.newRow().add(log.name).add(log.log).add(log.time);
		}
		result.setSection((Section) new Section().add(table));
		return result;
	}

	@Override
	protected void run() {
	}

	private class LogRow {
		public String name;
		public String log;
		public String time;
	}
}
