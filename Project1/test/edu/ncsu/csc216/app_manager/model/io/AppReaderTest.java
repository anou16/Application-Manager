/**
 * 
 */
package edu.ncsu.csc216.app_manager.model.io;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Tests the AppReader class.
 * 
 * @author Anoushka Piduru
 */
class AppReaderTest {

	/**
	 * Tests reading from a invalid file.
	 */
	@Test
	void testReadAppsFromInvalidFile() {
		assertThrows(IllegalArgumentException.class,
				() -> AppReader.readAppsFromFile("test-files/non_existent_file.txt"));
	}
}
