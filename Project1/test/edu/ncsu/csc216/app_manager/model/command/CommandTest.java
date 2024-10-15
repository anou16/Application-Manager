/**
 * 
 */
package edu.ncsu.csc216.app_manager.model.command;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.app_manager.model.command.Command.Resolution;

/**
 * Tests the Command class.
 * 
 * @author Anoushka Piduru
 */
class CommandTest {

	/**
	 * Test command creation for valid command parameters.
	 */
	@Test
	void testCreateCommand() {
		Command c = new Command(Command.CommandValue.ACCEPT, "reviewer", null, "note 1");
		assertEquals(c.getCommand(), Command.CommandValue.ACCEPT);
		assertEquals(c.getReviewerId(), "reviewer");
		assertNull(c.getResolution());
		assertEquals(c.getNote(), "note 1");
	}

	/**
	 * Test command creation for invalid command.
	 */
	@Test
	void testInvalidCommand() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Command(null, "reviewer", null, "note 1"));
		assertEquals(e.getMessage(), "Invalid command.");
	}

	/**
	 * Test command creation for invalid id.
	 */
	@Test
	void testInvalidReviewer() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Command(null, null, null, "note 1"));
		assertEquals(e.getMessage(), "Invalid command.");
	}

	/**
	 * Test command creation for invalid resolution.
	 */
	@Test
	void testInvalidResolution() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Command(null, "reviewer", null, "note 1"));
		assertEquals(e.getMessage(), "Invalid command.");
	}

	/**
	 * Test command creation for invalid note.
	 */
	@Test
	void testInvalidNote() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Command(null, "reviewer", null, null));
		assertEquals(e.getMessage(), "Invalid command.");
	}

	/**
	 * Test command value of ACCEPT and reviewer id length of zero.
	 */
	@Test
	void testAcceptCommandAndInvalidReviewer() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.ACCEPT, "", null, "note 1"));
		assertEquals(e.getMessage(), "Invalid command.");
	}

	/**
	 * Test command value of STANDBY and null resolution.
	 */
	@Test
	void testStandbyCommandAndNullResolution() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.STANDBY, "Reviewer", null, "note 1"));
		assertEquals(e.getMessage(), "Invalid command.");

		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.REJECT, "Reviewer", null, "note 1"));
		assertEquals(e1.getMessage(), "Invalid command.");
	}

	/**
	 * Test empty note.
	 */
	@Test
	void testEmptyNote() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.ACCEPT, "Reviewer", null, ""));
		assertEquals(e.getMessage(), "Invalid command.");
	}

	/**
	 * Test get REVCOMPLETED resolution.
	 */
	@Test
	void testGetResolution() {
		Command c = new Command(Command.CommandValue.ACCEPT, "reviewer", Resolution.REVCOMPLETED, "note 1");
		assertEquals(c.getResolution(), Resolution.REVCOMPLETED);
	}

}
