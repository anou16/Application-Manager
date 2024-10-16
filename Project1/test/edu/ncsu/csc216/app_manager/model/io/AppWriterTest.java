/**
 * 
 */
package edu.ncsu.csc216.app_manager.model.io;

import static org.junit.Assert.assertTrue;

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
