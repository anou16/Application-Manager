package edu.ncsu.csc216.app_manager.model.manager;

import java.util.ArrayList;
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
	/** A list which stores applications. */
	private ArrayList<Application> applications;

	/**
	 * Constructor for AppList.
	 */
	public AppList() {
		this.counter = 0;
		this.applications = new ArrayList<Application>();
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
		counter++;
		Application application = new Application(counter, appType, summary, note);
		applications.add(application);

		return counter;
	}

	/**
	 * Adds a collection of applications to the AppList.
	 * 
	 * @param appList the list of applications being added to.
	 */
	public void addApps(List<Application> appList) {
		applications.clear();
		for (int i = 0; i < appList.size(); i++) {
			Application application = appList.get(i);
			addApp(application);
		}
	}

	/**
	 * Adds a specific application to AppList.
	 * 
	 * @param application the application being added.
	 */
	private void addApp(Application application) {
		int index = 0;
		boolean isDuplicate = false;

		for (int i = 0; i < applications.size(); i++) {
			Application app = applications.get(i);
			if (application.getAppId() == app.getAppId()) {
				isDuplicate = true;
			}
			if (application.getAppId() > app.getAppId()) {
				index++;
			}
		}

		if (!isDuplicate) {
			applications.add(index, application);
			counter = applications.get(applications.size() - 1).getAppId() + 1;
		}
	}

	/**
	 * Retrieves the list of all applications.
	 * 
	 * @return a list of applications managed by AppList.
	 */
	public List<Application> getApps() {
		return applications;
	}

	/**
	 * Retrieves a list of applications categorized by application type.
	 * 
	 * @param type the type of applications to retrieve.
	 * @return a list of applications of the specific type.
	 * @throws IllegalArgumentException if the type is invalid.
	 */
	public List<Application> getAppsByType(String type) {
		List<Application> appByType = new ArrayList<>();

		if (type == null) {
			throw new IllegalArgumentException("Invalid type.");
		}

		for (int i = 0; i < applications.size(); i++) {
			Application application = applications.get(i);
			if (application.getAppType().equals(type)) {
				appByType.add(application);
			}
		}
		return appByType;
	}

	/**
	 * Retrieves a specific application by its ID.
	 * 
	 * @param id the application ID being retrieved.
	 * @return the application of the specified ID.
	 */
	public Application getAppById(int id) {
		for (int i = 0; i < applications.size(); i++) {
			Application application = applications.get(i);
			if (application.getAppId() == id) {
				return application;
			}
		}
		return null;
	}

	/**
	 * Executes a specified command on a specified application.
	 * 
	 * @param id      the ID of the application being modified.
	 * @param command the command to execute on the application.
	 */
	public void executeCommand(int id, Command command) {
		Application application = getAppById(id);
		if (application != null) {
			application.update(command);
		}
	}

	/**
	 * Deletes an application from AppList.
	 * 
	 * @param id the ID of the application to delete.
	 */
	public void deleteAppById(int id) {
		Application application = getAppById(id);
		if (application != null) {
			applications.remove(application);
		}
	}
}