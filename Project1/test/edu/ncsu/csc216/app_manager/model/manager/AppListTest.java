/**
 * 
 */
package edu.ncsu.csc216.app_manager.model.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.app_manager.model.application.Application;
import edu.ncsu.csc216.app_manager.model.application.Application.AppType;

/**
 * Tests the AppList class.
 * 
 * @author Anoushka Piduru
 */
class AppListTest {

	/** An AppList object. */
	private AppList appList;

	/**
	 * Tests adding an application.
	 */
	@Test
	void testAddApp() {
		appList = new AppList();
		int counter = appList.addApp(AppType.NEW, "Summary", "Note");
		assertEquals(1, counter);
	}

	/**
	 * Tests adding a collection of applications.
	 */
	@Test
	void testAddApps() {
		appList = new AppList();
		List<Application> apps = new ArrayList<>();
		apps.add(new Application(1, AppType.NEW, "Summary", "Note"));
		apps.add(new Application(2, AppType.NEW, "Summary 2", "Note 2"));
		appList.addApps(apps);
		assertEquals(2, appList.getApps().size());
	}

	/**
	 * Tests the get application by type method.
	 */
	@Test
	void testGetAppsByType() {
		appList = new AppList();
		appList.addApp(AppType.NEW, "Summary", "Note");
		appList.addApp(AppType.OLD, "Summary 2", "Note 2");
		appList.addApp(AppType.NEW, "Summary 3", "Note 3");
		appList.addApp(AppType.OLD, "Summary 4", "Note 4");
		appList.addApp(AppType.NEW, "Summary 5", "Note 5");
		appList.addApp(AppType.NEW, "Summary 6", "Note 6");

		List<Application> newApp = appList.getAppsByType("New");
		assertEquals(4, newApp.size());

		List<Application> oldApp = appList.getAppsByType("Old");
		assertEquals(2, oldApp.size());
	}
}
