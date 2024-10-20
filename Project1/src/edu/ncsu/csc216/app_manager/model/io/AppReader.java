package edu.ncsu.csc216.app_manager.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.app_manager.model.application.Application;

/**
 * Manages file input and reading/processing for a given Application file.
 * 
 * @author Anoushka Piduru
 */
public class AppReader {
	/**
	 * Processes the given file.
	 *
	 * @param filename the name of the file being read.
	 * @return an ArrayList of Application objects that are read from file.
	 * @throws IllegalArgumentException if the file cannot be found or an error
	 *                                  during processing occurs.
	 */
	public static ArrayList<Application> readAppsFromFile(String filename) {
		ArrayList<Application> application = new ArrayList<Application>();
		String s = "";
		try {
			Scanner fileReader = new Scanner(new FileInputStream(filename));

			while (fileReader.hasNextLine()) {
				s += fileReader.nextLine() + "\n";
			}
			fileReader.close();
			String[] appStrings = s.toString().split("\\r?\\n?[*]");

			for (String app : appStrings) {
				if (!app.isEmpty()) {
					application.add(processApp(app));
				}
			}
			return application;
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}

	/**
	 * Helper method used to process application string and create an Application
	 * object.
	 * 
	 * @param line the line being processed
	 * @return Application the application after processing is complete
	 * @throws IOException if there is an issue processing the file
	 */
	private static Application processApp(String line) {
		String[] appLines = line.split("\\r?\\n?[-]");
		String[] appParams = appLines[0].split(",");

		if (appParams.length < 6) {
			throw new IllegalArgumentException("Unable to load file.");
		}

		int id;
		String state;
		String appType;
		String summary;
		String reviewer = "";
		boolean processPaperwork;
		String resolution = "";
		ArrayList<String> notes = new ArrayList<>();

		try {
			id = Integer.parseInt(appParams[0]);
			state = appParams[1];
			appType = appParams[2];
			summary = appParams[3];
			if (appParams[4].isBlank() || appParams[4] == null) {
				reviewer = "null";
			} else {
				reviewer = appParams[4].trim();
			}
			processPaperwork = Boolean.parseBoolean(appParams[5]);
			if (appParams.length == 7) {
				if (appParams[6].isBlank() || appParams[6] == null) {
					resolution = "null";
				} else {
					resolution = appParams[6].trim();
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}

		for (int i = 1; i < appLines.length; i++) {
			notes.add(appLines[i]);
		}
		return new Application(id, state, appType, summary, reviewer, processPaperwork, resolution, notes);
	}
}
