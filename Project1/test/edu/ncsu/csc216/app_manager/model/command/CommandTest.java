/**
 * 
 */
package edu.ncsu.csc216.app_manager.model.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Command class.
 * 
 * @author Anoushka Piduru
 */
class CommandTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.app_manager.model.command.Command#getCommand()}.
	 */
	@Test
	void testGetCommand() {
		Command c = new Command(Command.CommandValue.ACCEPT, "reviewer", Command.Resolution.REVCOMPLETED, "note 1");
		assertEquals(c.getCommand(), Command.CommandValue.ACCEPT);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.app_manager.model.command.Command#getReviewerId()}.
	 */
	@Test
	void testGetReviewerId() {
		Command c = new Command(Command.CommandValue.ACCEPT, "reviewer", Command.Resolution.REVCOMPLETED, "note 1");
		assertEquals(c.getReviewerId(), "reviewer");
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.app_manager.model.command.Command#getResolution()}.
	 */
	@Test
	void testGetResolution() {
		Command c = new Command(Command.CommandValue.ACCEPT, "reviewer", Command.Resolution.REVCOMPLETED, "note 1");
		assertEquals(c.getResolution(), Command.Resolution.REVCOMPLETED);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.app_manager.model.command.Command#getNote()}.
	 */
	@Test
	void testGetNote() {
		Command c = new Command(Command.CommandValue.ACCEPT, "reviewer", Command.Resolution.REVCOMPLETED, "note 1");
		assertEquals(c.getNote(), "note 1");
	}

}
