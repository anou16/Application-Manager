/**
 * 
 */
package edu.ncsu.csc216.app_manager.model.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.app_manager.model.application.Application.AppType;
import edu.ncsu.csc216.app_manager.model.command.Command;
import edu.ncsu.csc216.app_manager.model.command.Command.Resolution;

/**
 * Tests the AppManager class.
 * 
 * @author Anoushka Piduru
 */
class AppManagerTest {

	/** The Singleton instance of AppManager. */
	private AppManager appManager;
	/** A file for testing. */
	private static final String FILE = "test-files/appFile.txt";
	/** A file for testing. */
	private static final String FILE_2 = "test-files/act_app_closed.txt";

	/**
	 * Tests getting the application list as an array.
	 */
	@Test
	void testAppListAsArray() {
		appManager = AppManager.getInstance();
		appManager.createNewAppList();

		appManager.addAppToList(AppType.NEW, "Summary", "Note");
		Object[][] array = appManager.getAppListAsArray();

		assertEquals(1, array[0][0]);
		assertEquals("Review", array[0][1]);
		assertEquals("New", array[0][2]);
		assertEquals("Summary", array[0][3]);
	}

	/**
	 * Tests getting the application list sorted by type as an array.
	 */
	@Test
	void testAppListAsArrayByType() {
		appManager = AppManager.getInstance();
		appManager.createNewAppList();

		AppManager.getInstance().addAppToList(AppType.NEW, "Summary", "Note");
		AppManager.getInstance().addAppToList(AppType.OLD, "Summary 2", "Note 2");
		AppManager.getInstance().addAppToList(AppType.NEW, "Summary 3", "Note 3");
		AppManager.getInstance().addAppToList(AppType.OLD, "Summary 4", "Note 4");
		AppManager.getInstance().addAppToList(AppType.OLD, "Summary 5", "Note 5");
		AppManager.getInstance().addAppToList(AppType.NEW, "Summary 6", "Note 6");

		Object[][] arr = AppManager.getInstance().getAppListAsArrayByAppType("Old");

		assertEquals(3, arr.length);

		assertEquals(2, arr[0][0]);
		assertEquals("Review", arr[0][1]);
		assertEquals("Old", arr[0][2]);
		assertEquals("Summary 2", arr[0][3]);

		assertEquals(4, arr[1][0]);
		assertEquals("Review", arr[1][1]);
		assertEquals("Old", arr[1][2]);
		assertEquals("Summary 4", arr[1][3]);

		assertEquals(5, arr[2][0]);
		assertEquals("Review", arr[2][1]);
		assertEquals("Old", arr[2][2]);
		assertEquals("Summary 5", arr[2][3]);

		// Null type
		assertThrows(IllegalArgumentException.class, () -> AppManager.getInstance().getAppListAsArrayByAppType(null));

		// Invalid type
		Object[][] array = AppManager.getInstance().getAppListAsArrayByAppType("InvalidType");
		assertEquals(0, array.length);
	}

	/**
	 * Tests the execute command method.
	 */
	@Test
	void testExecuteCommand() {
		appManager = AppManager.getInstance();
		appManager.createNewAppList();

		appManager.addAppToList(AppType.NEW, "Summary", "Note");
		appManager.addAppToList(AppType.NEW, "Summary", "Note");
		appManager.addAppToList(AppType.OLD, "Summary", "Note");

		Command command = new Command(Command.CommandValue.ACCEPT, "Reviewer", Resolution.REVCOMPLETED, "Note");
		appManager.executeCommand(1, command);

		assertEquals("Interview", appManager.getAppById(1).getStateName());
		assertEquals("Summary", appManager.getAppById(1).getSummary());
		assertEquals("Old", appManager.getAppById(1).getAppType());
	}

	/**
	 * Tests the delete application by ID method.
	 */
	@Test
	void testDeleteAppById() {
		appManager = AppManager.getInstance();
		appManager.createNewAppList();

		appManager.addAppToList(AppType.NEW, "Summary", "Note");
		appManager.addAppToList(AppType.NEW, "Summary", "Note");
		appManager.addAppToList(AppType.OLD, "Summary", "Note");

		assertEquals("New", appManager.getAppById(1).getAppType());
		appManager.deleteAppById(1);
		assertEquals(2, appManager.getAppListAsArray().length);
	}

	/**
	 * Tests saving applications to a file.
	 */
	@Test
	void testSaveAppsToFile() {
		appManager = AppManager.getInstance();
		appManager.createNewAppList();

		appManager.addAppToList(AppType.NEW, "Summary", "Note");
		appManager.addAppToList(AppType.NEW, "Summary", "Note");
		appManager.addAppToList(AppType.OLD, "Summary", "Note");

		appManager.saveAppsToFile(FILE);
		File filename = new File(FILE);
		assertTrue(filename.exists());

		assertTrue(filename.length() > 0);
	}

	/**
	 * Tests loading applications from a file.
	 */
	@Test
	void testLoadAppsFromFile() {
		appManager = AppManager.getInstance();
		appManager.createNewAppList();

		appManager.loadAppsFromFile(FILE_2);
		assertEquals("Closed", appManager.getAppById(1).getState());
		assertEquals("Old", appManager.getAppById(1).getAppType());
		assertEquals("Summary", appManager.getAppById(1).getSummary());
		assertEquals("null", appManager.getAppById(1).getReviewer());
		assertTrue(appManager.getAppById(1).isProcessed());
		assertEquals("OfferCompleted", appManager.getAppById(1).getResolution());
	}
}
