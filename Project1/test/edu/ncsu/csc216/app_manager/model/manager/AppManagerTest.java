/**
 * 
 */
package edu.ncsu.csc216.app_manager.model.manager;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.app_manager.model.application.Application.AppType;

/**
 * Tests the AppManager class.
 * 
 * @author Anoushka Piduru
 */
class AppManagerTest {

	/** The Singleton instance of AppManager. */
	private AppManager appManager;

	/**
	 * Tests getting the application list as an array.
	 */
	@Test
	void testAppListAsArray() {
		appManager = AppManager.getInstance();
		appManager.createNewAppList();

		appManager.addAppToList(AppType.NEW, "Summary", "Note");
		Object[][] arr = appManager.getAppListAsArray();

		assertEquals(1, arr[0][0]);
		assertEquals("Review", arr[0][1]);
		assertEquals("New", arr[0][2]);
		assertEquals("Summary", arr[0][3]);
	}

	/**
	 * Tests getting the application list sorted by type as an array.
	 */
	@Test
	void testAppListAsArrayByType() {
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
	}

}
