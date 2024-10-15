package edu.ncsu.csc216.app_manager.model.manager;

import edu.ncsu.csc216.app_manager.model.application.Application;
import edu.ncsu.csc216.app_manager.model.application.Application.AppType;
import edu.ncsu.csc216.app_manager.model.command.Command;
import edu.ncsu.csc216.app_manager.model.io.AppReader;
import edu.ncsu.csc216.app_manager.model.io.AppWriter;

/**
 * The AppManager class controls the creation and modification of applications
 * and provides information to the GUI.
 */
public class AppManager {

	/** The Singleton instance for AppManager. */
	private static AppManager instance;
	/** A list containing applications. */
	private AppList appList;

	/**
	 * Constructor for AppManager.
	 */
	private AppManager() {
		appList = new AppList();
	}

	/**
	 * Returns the instance of AppManager.
	 * 
	 * @return AppManager the instance of AppManager.
	 */
	public static AppManager getInstance() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	/**
	 * Saves applications to a given file.
	 * 
	 * @param filename the name of the file being saved to.
	 */
	public void saveAppsToFile(String filename) {
		AppWriter.writeAppsToFile(filename, appList.getApps());
	}

	/**
	 * Loads applications from the specified file.
	 * 
	 * @param filename the name of the file from which applications are loaded.
	 */
	public void loadAppsFromFile(String filename) {
		appList.addApps(AppReader.readAppsFromFile(filename));
	}

	/**
	 * Creates an empty list of applications.
	 */
	public void createNewAppList() {
		appList = new AppList();
	}

	/**
	 * Returns a 2D array representation of the application list.
	 * 
	 * @return a 2D array representation of the application list.
	 */
	public Object[][] getAppListAsArray() {
		if (appList == null) {
			return new Object[0][0];
		}
		Object[][] appListArr = new Object[appList.getApps().size()][4];

		for (int i = 0; i < appList.getApps().size(); i++) {
			appListArr[i][0] = appList.getApps().get(i).getAppId();
			appListArr[i][1] = appList.getApps().get(i).getState();
			appListArr[i][2] = appList.getApps().get(i).getAppType();
			appListArr[i][3] = appList.getApps().get(i).getSummary();
		}
		return appListArr;
	}

	/**
	 * Returns a list of applications categorized by specific application type as a
	 * 2D array.
	 * 
	 * @param type the application type.
	 * @return a 2D representation of the application list.
	 * @throws IllegalArgumentException if the type is invalid.
	 */
	public Object[][] getAppListAsArrayByAppType(String type) {
		if (type == null) {
			throw new IllegalArgumentException("Invalid type.");
		}
		if (!Application.A_NEW.equals(type) && !Application.A_OLD.equals(type) && !Application.A_HIRED.equals(type)) {
			return new Object[0][0];
		}

		Object[][] appListArr = new Object[appList.getAppsByType(type).size()][4];

		for (int i = 0; i < appList.getAppsByType(type).size(); i++) {
			Application application = appList.getAppsByType(type).get(i);
			appListArr[i][0] = application.getAppId();
			appListArr[i][1] = application.getState();
			appListArr[i][2] = application.getAppType();
			appListArr[i][3] = application.getSummary();
		}
		return appListArr;
	}

	/**
	 * Returns an application based on its ID.
	 * 
	 * @param id the ID of the application being searched.
	 * @return an Application of the appropriate ID number.
	 */
	public Application getAppById(int id) {
		return appList.getAppById(id);
	}

	/**
	 * Executes a certain command on the application of a given ID.
	 * 
	 * @param id      the ID of the application being altered.
	 * @param command the command to be executed on the application.
	 */
	public void executeCommand(int id, Command command) {
		appList.executeCommand(id, command);
	}

	/**
	 * Deletes the application of a specific given ID.
	 * 
	 * @param id the ID of the application being deleted.
	 */
	public void deleteAppById(int id) {
		appList.deleteAppById(id);
	}

	/**
	 * Adds a new application to list.
	 * 
	 * @param appType the type of application being added.
	 * @param summary the summary information of the application.
	 * @param note    the notes of the application.
	 */
	public void addAppToList(AppType appType, String summary, String note) {
		appList.addApp(appType, summary, note);
	}
}
