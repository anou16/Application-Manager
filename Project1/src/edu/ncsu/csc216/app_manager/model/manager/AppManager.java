package edu.ncsu.csc216.app_manager.model.manager;

import edu.ncsu.csc216.app_manager.model.application.Application;
import edu.ncsu.csc216.app_manager.model.application.Application.AppType;
import edu.ncsu.csc216.app_manager.model.command.Command;

/**
 * The AppManager class controls the creation and modification of applications
 * and provides information to the GUI.
 */
public class AppManager {
	/**
	 * Constructor for AppManager.
	 */
	private AppManager() {
		// Implement
	}

	/**
	 * Returns the instance of AppManager.
	 * 
	 * @return AppManager the instance of AppManager.
	 */
	public static AppManager getInstance() {
		return null;
	}

	/**
	 * Saves applications to a given file.
	 * 
	 * @param filename the name of the file being saved to.
	 */
	public void saveAppsToFile(String filename) {
		// Implement
	}

	/**
	 * Loads applications from the specified file.
	 * 
	 * @param filename the name of the file from which applications are loaded.
	 */
	public void loadAppsFromFile(String filename) {
		// Implement
	}

	/**
	 * Creates an empty list of applications.
	 */
	public void createNewAppList() {
		// Implement
	}

	/**
	 * Returns a 2D array representation of the application list.
	 * 
	 * @return a 2D array representation of the application list.
	 */
	public Object[][] getAppListAsArray() {
		return null;
	}

	/**
	 * Returns a list of applications categorized by specific application type as a
	 * 2D array.
	 * 
	 * @param type the application type.
	 * @return a 2D representation of the application list.
	 */
	public Object[][] getAppListAsArrayByAppType(String type) {
		return null;
	}

	/**
	 * Returns an application based on its ID.
	 * 
	 * @param id the ID of the application being searched.
	 * @return an Application of the appropriate ID number.
	 */
	public Application getAppById(int id) {
		return null;
	}

	/**
	 * Executes a certain command on the application of a given ID.
	 * 
	 * @param id      the ID of the application being altered.
	 * @param command the command to be executed on the application.
	 */
	public void executeCommand(int id, Command command) {
		// Implement
	}

	/**
	 * Deletes the application of a specific given ID.
	 * 
	 * @param id the ID of the application being deleted.
	 */
	public void deleteAppById(int id) {
		// Implement
	}

	/**
	 * Adds a new application to list.
	 * 
	 * @param appType the type of application being added.
	 * @param summary the summary information of the application.
	 * @param note    the notes of the application.
	 */
	public void addAppToList(AppType appType, String summary, String note) {
		// Implement
	}
}
