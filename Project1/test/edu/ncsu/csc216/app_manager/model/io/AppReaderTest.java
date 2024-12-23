/**
 * 
 */
package edu.ncsu.csc216.app_manager.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.app_manager.model.application.Application;

/**
 * Tests the AppReader class.
 * 
 * @author Anoushka Piduru
 */
class AppReaderTest {

	/** String representing the valid file path. */
	private String validTest = "test-files/valid_application.txt";

	/**
	 * Tests reading from a valid file.
	 */
	@Test
	void testReadAppsFromValidFile() {
		assertDoesNotThrow(() -> new AppReader());
		ArrayList<Application> apps = AppReader.readAppsFromFile(validTest);

		assertEquals(6, apps.size());
		Application app = apps.get(0);

		assertEquals(1, app.getAppId());
		assertEquals("Review", app.getState());
		assertEquals("New", app.getAppType());
		assertEquals("Application summary", app.getSummary());
		assertNull(app.getReviewer());
		assertFalse(app.isProcessed());
		assertNull(app.getResolution());

		assertEquals(1, app.getNotes().size());
		assertEquals("[Review] Note 1", app.getNotes().get(0));
	}

	/**
	 * Tests reading from a nonexistent file.
	 */
	@Test
	void testReadAppsFromNonExistentFile() {
		assertThrows(IllegalArgumentException.class,
				() -> AppReader.readAppsFromFile("test-files/non_existent_file.txt"));
	}

	/**
	 * Tests reading from an invalid file.
	 */
	@Test
	void testReadAppsFromInvalidFile() {
		assertThrows(IllegalArgumentException.class,
				() -> AppReader.readAppsFromFile("test-files/invalid_application.txt"));
	}

	/**
	 * Tests reading from specific invalid files.
	 */
	@Test
	void testReadAppsFromInvalidTestFiles() {
		assertThrows(IllegalArgumentException.class, () -> AppReader.readAppsFromFile("test-files/app15.txt"));
		assertThrows(IllegalArgumentException.class, () -> AppReader.readAppsFromFile("test-files/app16.txt"));
		assertThrows(IllegalArgumentException.class, () -> AppReader.readAppsFromFile("test-files/app18.txt"));
	}
}
