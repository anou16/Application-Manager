package edu.ncsu.csc216.app_manager.model.io;

import java.util.ArrayList;

import edu.ncsu.csc216.app_manager.model.application.Application;

/**
 * Manages file input and reading/processing a given Application file.
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
	public ArrayList<Application> readAppsFromFile(String filename) {
		return null;
	}

	/**
	 * Helper method used to process application string and create an Application
	 * object.
	 * 
	 * @param filename the file being processed
	 * @return Application the application after processing is complete
	 * @throws IOException if there is an issue processing the file
	 */
	private Application processApp(String filename) {
		return null;
	}
}
