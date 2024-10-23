/**
 * 
 */
package edu.ncsu.csc216.app_manager.model.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.app_manager.model.application.Application;
import edu.ncsu.csc216.app_manager.model.application.Application.AppType;
import edu.ncsu.csc216.app_manager.model.command.Command;

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
	 * Tests getAppsByType.
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

	/**
	 * Tests getAppsById.
	 */
	@Test
	void testGetAppsById() {
		appList = new AppList();
		appList.addApp(AppType.NEW, "Summary", "Note");
		appList.addApp(AppType.OLD, "Summary 2", "Note 2");
		appList.addApp(AppType.NEW, "Summary 3", "Note 3");
		appList.addApp(AppType.OLD, "Summary 4", "Note 4");
		appList.addApp(AppType.NEW, "Summary 5", "Note 5");
		appList.addApp(AppType.NEW, "Summary 6", "Note 6");

		Application application = appList.getAppById(1);

		assertEquals(1, application.getAppId());
		assertEquals("New", application.getAppType());
		assertEquals("Summary", application.getSummary());

		// Test nonexistent id
		Application app = appList.getAppById(365);
		assertNull(app);
	}

	/**
	 * Tests deleteAppById.
	 */
	@Test
	void testDeleteAppById() {
		appList = new AppList();
		appList.addApp(AppType.NEW, "Summary", "Note");
		appList.addApp(AppType.OLD, "Summary 2", "Note 2");
		appList.addApp(AppType.NEW, "Summary 3", "Note 3");
		appList.addApp(AppType.OLD, "Summary 4", "Note 4");
		appList.addApp(AppType.NEW, "Summary 5", "Note 5");
		appList.addApp(AppType.NEW, "Summary 6", "Note 6");

		List<Application> application = appList.getApps();
		assertEquals(6, application.size());
		assertEquals("New", application.get(0).getAppType());
		assertEquals("Old", application.get(3).getAppType());
		assertEquals("Summary 6", application.get(5).getSummary());

		appList.deleteAppById(3);
		assertEquals(5, appList.getApps().size());
	}

	/**
	 * Tests executeCommand.
	 */
	@Test
	void testExecuteCommand() {
		appList = new AppList();
		List<Application> applicationList = new ArrayList<>();
		ArrayList<String> notes = new ArrayList<>();
		notes.add("Note 1");

		Application app = new Application(1, "Interview", "Old", "Summary", "Reviewer", false, null, notes);
		applicationList.add(app);

		appList.addApps(applicationList);
		appList.executeCommand(1, new Command(Command.CommandValue.ACCEPT, "Reviewer", null, "Note"));

		Application a = appList.getAppById(1);

		assertEquals("Reviewer", a.getReviewer());
		assertEquals("RefCheck", a.getState());
		assertEquals("Old", a.getAppType());
		assertEquals("Summary", a.getSummary());
	}
}
