/**
 * 
 */
package edu.ncsu.csc216.app_manager.model.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.app_manager.model.application.Application.AppType;
import edu.ncsu.csc216.app_manager.model.command.Command;
import edu.ncsu.csc216.app_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.app_manager.model.command.Command.Resolution;

/**
 * Tests the Application class
 * 
 * @author Anoushka Piduru
 */
class ApplicationTest {
	/**
	 * An instance of Application.
	 */
	private Application application;

	/**
	 * Tests the construction of an Application object.
	 */
	@Test
	void testApplicationIntAppTypeStringString() {
		application = new Application(1, AppType.NEW, "Summary", "Note 1");
		assertEquals(1, application.getAppId());
		assertEquals("New", application.getAppType());
		assertEquals("Summary", application.getSummary());
		assertEquals("[Review] Note 1", application.getNotes().get(0));

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Application(-1, null, null, null));
		assertEquals(e1.getMessage(), "Application cannot be created.");
	}

	/**
	 * Tests the second Application constructor.
	 */
	@Test
	void testApplicationIntStringStringStringStringBooleanStringArrayListOfString() {
		ArrayList<String> notes = new ArrayList<>();

		notes.add("Note 1");
		notes.add("Note 2");

		Application newApp = new Application(2, Application.CLOSED_NAME, Application.A_NEW, "Summary", "Reviewer",
				false, Command.R_REVCOMPLETED, notes);

		assertEquals("Note 1", newApp.getNotes().get(0));
		assertEquals("Note 2", newApp.getNotes().get(1));

		assertEquals(2, newApp.getAppId());
		assertEquals(Application.CLOSED_NAME, newApp.getState());
		assertEquals(Application.A_NEW, newApp.getAppType());
		assertEquals("Summary", newApp.getSummary());
		assertEquals("Reviewer", newApp.getReviewer());
		assertFalse(newApp.isProcessed());
		assertEquals(Command.R_REVCOMPLETED, newApp.getResolution());
		assertEquals(2, newApp.getNotes().size());
	}

	/**
	 * Tests the update state method with the accept command for review state.
	 */
	@Test
	void testReviewAcceptUpdate() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("Note 1");
		application = new Application(1, Application.REVIEW_NAME, Application.A_NEW, "Summary", "Reviewer", false,
				Command.R_REVCOMPLETED, notes);

		assertEquals("Review", application.getStateName());

		Command acceptCommand = new Command(Command.CommandValue.ACCEPT, "reviewer", null, "note");
		application.update(acceptCommand);
		assertEquals("Interview", application.getState());
		assertEquals("-Note 1\n-[Interview] note\n", application.getNotesString());
	}

	/**
	 * Tests the update state method with the standby command for review state.
	 */
	@Test
	void testReviewStandbyUpdate() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("Note 1");
		application = new Application(1, Application.REVIEW_NAME, Application.A_NEW, "Summary", "Reviewer", false,
				Command.R_REVCOMPLETED, notes);

		assertEquals("Review", application.getStateName());

		Command acceptCommand = new Command(Command.CommandValue.STANDBY, "reviewer", Resolution.REVCOMPLETED, "note");
		application.update(acceptCommand);
		assertEquals("ReviewCompleted", application.getResolution());
		assertEquals("Waitlist", application.getState());
		assertEquals("-Note 1\n-[Waitlist] note\n", application.getNotesString());
	}

	/**
	 * Tests the update state method with the standby command for review state.
	 */
	@Test
	void testReviewRejectUpdate() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("Note 1");
		application = new Application(1, Application.REVIEW_NAME, Application.A_NEW, "Summary", "Reviewer", false,
				Command.R_REVCOMPLETED, notes);

		assertEquals("Review", application.getStateName());

		Command acceptCommand = new Command(Command.CommandValue.REJECT, "reviewer", Resolution.REVCOMPLETED, "note");
		application.update(acceptCommand);
		assertEquals("ReviewCompleted", application.getResolution());
		assertEquals("Closed", application.getState());
		assertEquals("-Note 1\n-[Closed] note\n", application.getNotesString());
	}

	/**
	 * Tests the update state method with the accept command for interview state.
	 */
	@Test
	void testInterviewAcceptUpdate() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("Note 1");
		application = new Application(1, Application.INTERVIEW_NAME, Application.A_NEW, "Summary", "Reviewer", true,
				Command.R_REVCOMPLETED, notes);

		assertEquals("Interview", application.getStateName());

		Command acceptCommand = new Command(Command.CommandValue.ACCEPT, "reviewer", Resolution.REVCOMPLETED, "note");
		application.update(acceptCommand);
		assertEquals("reviewer", application.getReviewer());
		assertEquals("RefCheck", application.getState());
		assertEquals("-Note 1\n-[RefCheck] note\n", application.getNotesString());
	}

	/**
	 * Tests the update state method with the standby command for interview state.
	 */
	@Test
	void testInterviewStandbyUpdate() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("Note 1");
		application = new Application(1, Application.INTERVIEW_NAME, Application.A_NEW, "Summary", "Reviewer", true,
				Command.R_REVCOMPLETED, notes);

		assertEquals("Interview", application.getStateName());

		Command acceptCommand = new Command(Command.CommandValue.STANDBY, "reviewer", Resolution.REVCOMPLETED, "note");
		application.update(acceptCommand);
		assertEquals("InterviewCompleted", application.getResolution());
		assertEquals("Reviewer", application.getReviewer());
		assertEquals("Waitlist", application.getState());
		assertEquals("-Note 1\n-[Waitlist] note\n", application.getNotesString());
	}

}
