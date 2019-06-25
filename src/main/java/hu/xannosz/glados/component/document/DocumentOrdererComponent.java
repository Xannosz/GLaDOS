package hu.xannosz.glados.component.document;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import hu.xannosz.glados.component.Component;
import hu.xannosz.glados.core.ComponentCheckResult;
import hu.xannosz.glados.core.Manager;
import hu.xannosz.microtools.pack.Triplet;

public class DocumentOrdererComponent extends Component {

	private List<Triplet<String, String, String>> data;

	public DocumentOrdererComponent(Manager manager) {
		super(manager, "Document Orderer System");
	}

	private void copy() {
		for (Triplet<String, String, String> row : data) {
			try {
				copyDirectory(new File(row.getFirst()), new File(row.getSecond()), Pattern.compile(row.getThird()));
				copyDirectory(new File(row.getSecond()), new File(row.getFirst()), Pattern.compile(row.getThird()));
			} catch (Exception e) {
				// I don't care.
			}
		}
	}

	private void copyDirectory(File original, File copied, Pattern pattern) throws IOException {
		if (original.isDirectory()) {
			for (File file : original.listFiles()) {
				copyDirectory(file, new File(copied.getPath() + "/" + file.getName()), pattern);
			}
		} else {
			if (pattern.matcher(original.getAbsolutePath()).matches()) {
				copyFile(original, copied);
			}
		}
	}

	private void copyFile(File original, File copied) throws IOException {
		if (!copied.exists() || original.lastModified() > copied.lastModified()) {
			FileUtils.copyFile(original, copied);
		}
	}

	private void readData() {
		data = new ArrayList<>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("data.csv"));
			scanner.useDelimiter(";|\n");
			while (scanner.hasNext()) {
				data.add(new Triplet<String, String, String>(scanner.next().trim(), scanner.next().trim(),
						scanner.next().trim()));
			}
			scanner.close();
		} catch (Exception e) {
			// I don't care.
		}
	}

	@Override
	protected ComponentCheckResult getCheckResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub

	}
}
