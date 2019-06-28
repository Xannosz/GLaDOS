package hu.xannosz.glados.component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import hu.xannosz.glados.core.ComponentCheckResult;
import hu.xannosz.glados.core.Manager;
import hu.xannosz.microtools.pack.Triplet;
import hu.xannosz.veneos.html.Section;
import hu.xannosz.veneos.html.Table;

public class DocumentOrdererComponent extends Component {

	private List<Triplet<String, String, String>> data;
	private List<String> recognizedFolders;
	private List<String> unRecognizedFolders;
	private List<String> unAccessibleFolders;
	private List<String> deadRecognizedFolders;
	private Section section = new Section();

	public DocumentOrdererComponent(Manager manager) {
		super(manager, "Document Orderer System");
		setActive(true);
		tickSpeed = 20;
	}

	private void copyDirectory(File original, File copied) {
		try {
			FileUtils.copyDirectory(original, copied);
		} catch (IOException e) {
			log("Directory copy problem. Original: " + original.getAbsolutePath() + " Target: "
					+ copied.getAbsolutePath() + " Exception: " + ExceptionUtils.getFullStackTrace(e));
		}
	}

	private void deleteSubFolders(File file) {
		for (String subFolder : file.list()) {
			FileUtils.deleteQuietly(new File(file.getAbsolutePath() + "/" + subFolder));
		}
	}

	private void findNewFolders() {
		for (File root : File.listRoots()) {
			findFolder(root);
		}
	}

	private void findFolder(File folder) {
		if (folder.isDirectory()) {
			try {
				for (File file : folder.listFiles()) {
					boolean recognized = false;
					for (String f : recognizedFolders) {
						if (file.getAbsolutePath().equals(f)) {
							recognized = true;
							deadRecognizedFolders.remove(f);
						}
					}
					if (!recognized) {
						unRecognizedFolders.add(file.getAbsolutePath());
						findFolder(file);
					}
				}
			} catch (Exception e) {
				unAccessibleFolders.add(folder.getAbsolutePath());
			}
		}
	}

	private void readDataFile() {
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
			log("Exception during data.csv reading. Exception: " + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Override
	protected ComponentCheckResult getCheckResult() {
		ComponentCheckResult result = new ComponentCheckResult();
		result.setSection(section);
		return result;
	}

	@Override
	protected void run() {
		readDataFile();
		unRecognizedFolders = new ArrayList<>();
		recognizedFolders = new ArrayList<>();
		unAccessibleFolders = new ArrayList<>();
		for (Triplet<String, String, String> row : data) {
			recognizedFolders.add(row.getFirst());
			handleFolder(row);
		}
		deadRecognizedFolders = new ArrayList<>(recognizedFolders);
		findNewFolders();
		createNewSection();
	}

	private void createNewSection() {

		Table folders = new Table();
		folders.add("Folder Name").add("State");
		for (String folder : unRecognizedFolders) {
			folders.newRow().add(folder).add("Unrecognized");
		}
		for (String folder : unAccessibleFolders) {
			folders.newRow().add(folder).add("Unaccessible");
		}
		for (String folder : deadRecognizedFolders) {
			folders.newRow().add(folder).add("Deadrecognized");
		}

		Table handled = new Table();
		handled.add("Original").add("Target").add("Strategy");
		for (Triplet<String, String, String> row : data) {
			handled.newRow().add(row.getFirst()).add(row.getSecond()).add(row.getThird());
		}

		section = (Section) new Section().add(folders).add(handled);
	}

	private void handleFolder(Triplet<String, String, String> row) {
		switch (row.getThird()) {
		case "Copy":
			copyDirectory(new File(row.getFirst()), new File(row.getSecond()));
			return;
		case "Sync":
			copyDirectory(new File(row.getFirst()), new File(row.getSecond()));
			copyDirectory(new File(row.getSecond()), new File(row.getFirst()));
			return;
		case "Delete":
			deleteSubFolders(new File(row.getFirst()));
			return;
		case "Move":
			copyDirectory(new File(row.getFirst()), new File(row.getSecond()));
			deleteSubFolders(new File(row.getFirst()));
			return;
		case "Scan":
			findFolder(new File(row.getFirst()));
			return;
		case "Ignore":
			return;
		default:
			log("Wrong handle option: " + row.getThird() + " in row: " + row.getFirst() + ";" + row.getSecond() + ";"
					+ row.getThird());
			break;
		}
	}
}
