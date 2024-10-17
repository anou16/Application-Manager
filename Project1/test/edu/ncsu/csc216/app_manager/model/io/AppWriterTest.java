/**
 * 
 */
package edu.ncsu.csc216.app_manager.model.io;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.app_manager.model.application.Application;

/**
 * Tests the AppWriter class.
 * 
 * @author Anoushka Piduru
 */
class AppWriterTest {

	/** String representing the valid file path. */
	private String validTest = "test-files/actual-output.txt";
	/** An array list of applications. */
	private ArrayList<Application> apps = new ArrayList<>();

	/**
	 * Tests writing applications to a file.
	 */
	@Test
	void testWriteAppsToFile() {
		AppWriter.writeAppsToFile(validTest, apps);

		File filename = new File(validTest);
		assertTrue(filename.exists());

		ArrayList<String> notes = new ArrayList<>();
		String file = "test-files/act_app_closed.txt";

		notes.add("[Review] Note 1");
		notes.add("[Interview] Note 2");
		notes.add("[Watlist] Note 3");
		notes.add("[RefCheck] Note 4");
		notes.add("[Offer] Note 5");
		notes.add("[Closed] Note 6");

		apps.add(new Application(1, "Closed", "Old", "Summary", null, true, "OfferCompleted", notes));

		assertDoesNotThrow(() -> AppWriter.writeAppsToFile(file, apps));
	}

	/**
	 * Tests construction of AppWriter
	 */
	@Test
	void testAppWriterConstruction() {
		AppWriter appWriter = new AppWriter();
		assertTrue(appWriter instanceof AppWriter);
	}
}
