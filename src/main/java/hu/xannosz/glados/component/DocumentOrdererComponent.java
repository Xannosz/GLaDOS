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
	private List<String> extrudedFolders;
	private Section section = new Section();

	public DocumentOrdererComponent(Manager manager) {
		super(manager, "Document Orderer System");
		setActive(true);
		tickSpeed = 20;
	}

	private void copyDirectory(File original, File copied) {
		try {
			FileUtils.copyDirectory(original, copied); // TODO
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
						if (file.getAbsolutePath().startsWith(f)) {
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
		extrudedFolders = new ArrayList<>();
		for (Triplet<String, String, String> row : data) {
			if (row.getThird().equals("Extruded")) {
				extrudedFolders.add(row.getFirst());
			} else {
				recognizedFolders.add(row.getFirst());
			}
		}
		deadRecognizedFolders = new ArrayList<>(recognizedFolders);
		for (Triplet<String, String, String> row : data) {
			handleFolder(row);
		}
		findNewFolders();
		createNewSection();
	}

	private void createNewSection() {

		Table folders = new Table();
		folders.addHead("Folder Name").addHead("State");
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
		handled.addHead("Original").addHead("Target").addHead("Strategy");
		for (Triplet<String, String, String> row : data) {
			handled.newRow().add(row.getFirst()).add(row.getSecond()).add(row.getThird());
		}

		section = (Section) new Section().add(folders).add(handled);
	}

	private void handleFolder(Triplet<String, String, String> row) {
		switch (row.getThird()) {
		case "Copy":
			deadRecognizedFolders.remove(row.getFirst());
			copyDirectory(new File(row.getFirst()), new File(row.getSecond()));
			return;
		case "Sync":
			deadRecognizedFolders.remove(row.getFirst());
			copyDirectory(new File(row.getFirst()), new File(row.getSecond()));
			copyDirectory(new File(row.getSecond()), new File(row.getFirst()));
			return;
		case "Delete":
			deadRecognizedFolders.remove(row.getFirst());
			deleteSubFolders(new File(row.getFirst()));
			return;
		case "Move":
			deadRecognizedFolders.remove(row.getFirst());
			copyDirectory(new File(row.getFirst()), new File(row.getSecond()));
			deleteSubFolders(new File(row.getFirst()));
			return;
		case "Scan":
			deadRecognizedFolders.remove(row.getFirst());
			findFolder(new File(row.getFirst()));
			return;
		case "Extruded":
		case "Ignore":
			return;
		default:
			log("Wrong handle option: " + row.getThird() + " in row: " + row.getFirst() + ";" + row.getSecond() + ";"
					+ row.getThird());
			break;
		}
	}
}
