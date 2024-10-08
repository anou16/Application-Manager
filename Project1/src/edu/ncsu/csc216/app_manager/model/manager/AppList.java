package edu.ncsu.csc216.app_manager.model.manager;

import java.util.List;

import edu.ncsu.csc216.app_manager.model.application.Application;
import edu.ncsu.csc216.app_manager.model.application.Application.AppType;
import edu.ncsu.csc216.app_manager.model.command.Command;

/**
 * AppList maintains and manages a list of Applications. Provides methods to
 * add, delete, retrieve, and manipulate applications.
 * 
 * @author Anoushka Piduru
 */
public class AppList {
	/** Counter for generating unique application IDs. */
	private int counter;

	/**
	 * Constructor for AppList.
	 */
	public AppList() {
		// Implement
	}

	/**
	 * Adds a new application to the application list.
	 * 
	 * @param appType the type of application being added.
	 * @param summary the summary of application information.
	 * @param note    the note added to the application.
	 * @return the ID assigned to the new application.
	 */
	public int addApp(AppType appType, String summary, String note) {
		return 0;
	}

	/**
	 * Adds a list of applications to the AppList.
	 * 
	 * @param list the list of applications being added.
	 */
	public void addApps(List<Application> list) {
		// Implement
	}

	/**
	 * Adds a specific application to AppList.
	 * 
	 * @param application the application being added.
	 */
	private void addApp(Application application) {
		// Implement
	}

	/**
	 * Retrieves the list of all applications.
	 * 
	 * @return a list of applications managed by AppList.
	 */
	public List<Application> getApps() {
		return null;
	}

	/**
	 * Retrieves a list of applications categorized by application type.
	 * 
	 * @param type the type of applications to retrieve.
	 * @return a list of applications of the specific type.
	 */
	public List<Application> getAppsByType(String type) {
		return null;
	}

	/**
	 * Retrieves a specific application by its ID.
	 * 
	 * @param id the application ID being retrieved.
	 * @return the application of the specified ID.
	 */
	public Application getAppById(int id) {
		return null;
	}

	/**
	 * Executes a specified command on a specified application.
	 * 
	 * @param id      the ID of the application being modified.
	 * @param command the command to execute on the application.
	 */
	public void executeCommand(int id, Command command) {
		// Implement
	}

	/**
	 * Deletes an application from AppList.
	 * 
	 * @param id the ID of the application to delete.
	 */
	public void deleteAppById(int id) {
		// Implement
	}
}