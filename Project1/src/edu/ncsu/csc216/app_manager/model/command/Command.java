package edu.ncsu.csc216.app_manager.model.command;

/**
 * The Command class handles user actions and transitions causing the update of
 * Application's state.
 * 
 * @author Anoushka Piduru
 */
public class Command {

	/**
	 * Enumeration representing one of the four possible commands a user can make
	 * for the Application Manager FSM.
	 */
	public enum CommandValue {
		/** Accepting the application. */
		ACCEPT,
		/** Rejecting the application. */
		REJECT,
		/** Putting the application on standby. */
		STANDBY,
		/** Reopening the application for further review. */
		REOPEN
	}

	/**
	 * Enumeration representing the four possible ways a user can resolve an
	 * application.
	 */
	public enum Resolution {
		/** Review has been completed. */
		REVCOMPLETED,
		/** The interview has been completed. */
		INTCOMPLETED,
		/** The reference check has been completed. */
		REFCHKCOMPLETED,
		/** The offer stage has been completed. */
		OFFERCOMPLETED
	}

	/** The command being executed on an application. */
	private CommandValue commandValue;
	/** The resolution of the application. */
	private Resolution resolution;
	/** The reviewer ID of the application. */
	private String reviewerId;
	/** The note added to the application. */
	private String note;

	/** "ReviewCompleted" resolution */
	public static final String R_REVCOMPLETED = "ReviewCompleted";
	/** "InterviewCompleted resolution */
	public static final String R_INTCOMPLETED = "InterviewCompleted";
	/** "ReferenceCheckCompleted" resolution */
	public static final String R_REFCHKCOMPLETED = "ReferenceCheckCompleted";
	/** "OfferCompleted" resolution */
	public static final String R_OFFERCOMPLETED = "OfferCompleted";

	/**
	 * Constructor for Command which allows application transitions to update.
	 * 
	 * @param c          the desired command for the application. Either ACCEPT,
	 *                   REJECT, STANDBY, or REOPEN.
	 * @param reviewerId the application's individual ID.
	 * @param r          the resolution of the application. Either Review Completed,
	 *                   Interview Completed, Reference Check Completed, or Offer
	 *                   Completed.
	 * @param note       the application's added note.
	 * @throws IllegalArgumentException if the command has an invalid parameter.
	 */
	public Command(CommandValue c, String reviewerId, Resolution r, String note) {
		if (c == null) {
			throw new IllegalArgumentException("Invalid command.");
		}

		if (c == CommandValue.ACCEPT && (reviewerId == null || reviewerId.isEmpty())) {
			throw new IllegalArgumentException("Invalid command.");
		}

		if ((c == CommandValue.STANDBY || c == CommandValue.REJECT) && r == null) {
			throw new IllegalArgumentException("Invalid command.");
		}

		if (note == null || note.isEmpty()) {
			throw new IllegalArgumentException("Invalid command.");
		}

		this.commandValue = c;
		this.reviewerId = reviewerId;
		this.resolution = r;
		this.note = note;
	}

	/**
	 * Getter for the command value.
	 * 
	 * @return the commandValue
	 */
	public CommandValue getCommand() {
		return commandValue;
	}

	/**
	 * Getter for the reviewer ID.
	 * 
	 * @return the reviewerId
	 */
	public String getReviewerId() {
		return reviewerId;
	}

	/**
	 * Getter for the resolution.
	 * 
	 * @return the resolution
	 */
	public Resolution getResolution() {
		return resolution;
	}

	/**
	 * Getter for the note.
	 * 
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

}
