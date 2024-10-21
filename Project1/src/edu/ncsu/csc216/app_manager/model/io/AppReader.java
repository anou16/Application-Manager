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
			Scanner scnr = new Scanner(new FileInputStream(filename));
			while (scnr.hasNextLine()) {
				s += scnr.nextLine() + "\n";
			}
			scnr.close();

			String[] appStrings = s.toString().split("\\r?\\n?[*]");

			for (int i = 0; i < appStrings.length; i++) {
				if (!appStrings[i].isEmpty()) {
					application.add(processApp(appStrings[i]));
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
		String state = appParams[1];
		String appType = appParams[2];
		String summary = appParams[3];
		String reviewer = appParams[4];
		boolean processPaperwork;
		String resolution = null;

		if (appParams.length == 7) {
			resolution = appParams[6].trim();
			if (resolution.isBlank()) {
				resolution = null;
			}
		}
		ArrayList<String> notes = new ArrayList<>();

		try {
			id = Integer.parseInt(appParams[0].trim());
			processPaperwork = Boolean.parseBoolean(appParams[5]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}

		if (reviewer.isBlank()) {
			reviewer = null;
		}

		for (int i = 1; i < appLines.length; i++) {
			notes.add(appLines[i].trim());
		}
		return new Application(id, state, appType, summary, reviewer, processPaperwork, resolution, notes);
	}
}
