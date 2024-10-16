package edu.ncsu.csc216.app_manager.model.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import edu.ncsu.csc216.app_manager.model.application.Application;

/**
 * Handles Application file output and writing to a specific file.
 * 
 * @author Anoushka Piduru
 */
public class AppWriter {
	/**
	 * Writes to a specific file.
	 * 
	 * @param filename     the name of the file being written to.
	 * @param applications the ArrayList of applications being written to the file.
	 * @throws IllegalArgumentException if there are any errors in writing to the
	 *                                  file.
	 */
	public static void writeAppsToFile(String filename, List<Application> applications) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(filename));

			for (Application application : applications) {
				pw.print(application.toString());
				pw.close();
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
