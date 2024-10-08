package edu.ncsu.csc216.app_manager.model.application;

import java.util.ArrayList;

import edu.ncsu.csc216.app_manager.model.command.Command;
import edu.ncsu.csc216.app_manager.model.command.Command.Resolution;

/**
 * The Application class handles the Application object and holds Application
 * (context), AppState (interface) and the 6 concrete state classes.
 * 
 * @author Anoushka Piduru
 */
public class Application {
	/** The possible categorized type of the application. */
	public enum AppType {
		/** A new application. */
		NEW,
		/** An old application. */
		OLD,
		/** A hired type application. */
		HIRED
	}

	/** New application type string. */
	public final static String A_NEW = "New";
	/** Old application type string. */
	public final static String A_OLD = "Old";
	/** Hired application type string. */
	public final static String A_HIRED = "Hired";
	/** Review state string. */
	public final static String REVIEW_NAME = "Review";
	/** Interview state string. */
	public final static String INTERVIEW_NAME = "Interview";
	/** Wait list state string. */
	public final static String WAITLIST_NAME = "Waitlist";
	/** Reference Check state string. */
	public final static String REFCHK_NAME = "RefCheck";
	/** Offer state string. */
	public final static String OFFER_NAME = "Offer";
	/** Closed state string. */
	public final static String CLOSED_NAME = "Closed";

	/** Unique application ID of application. */
	private int appId;
	/** The current state of the application of type AppState. */
	private AppState state;
	/** The categorized type of application, new or old. */
	private AppType appType;
	/**
	 * The application's summary information from when the application is created.
	 */
	private String summary;
	/**
	 * User ID of the application reviewer. Null if there is no assigned reviewer.
	 */
	private String reviewer;
	/**
	 * Boolean value representing whether paperwork required for current stage is
	 * processed.
	 */
	private boolean processPaperwork;
	/** Resolution of the application. */
	private Resolution resolution;
	/** An ArrayList of all the added notes of an application. */
	private ArrayList<String> notes;

	/** Final instance of the ReviewState inner class. */
	private final AppState reviewState = new ReviewState();
	/** Final instance of the InterviewState inner class. */
	private final AppState interviewState = new InterviewState();
	/** Final instance of the WaitlistState inner class. */
	private final AppState waitlistState = new WaitlistState();
	/** Final instance of the RefChkState inner class. */
	private final AppState refChkState = new RefChkState();
	/** Final instance of the OfferState inner class. */
	private final AppState offerState = new OfferState();
	/** Final instance of the ClosedState inner class. */
	private final AppState closedState = new ClosedState();

	/**
	 * Interface for states in the Application State Pattern. All concrete
	 * application states must implement the AppState interface. The AppState
	 * interface should be a private interface of the Application class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 * @author Dr. Chandrika Satyavolu (jsatyav@ncsu.edu)
	 */
	private interface AppState {

		/**
		 * Update the Application based on the given Command. An
		 * UnsupportedOperationException is thrown if the Command is not a valid action
		 * for the given state.
		 * 
		 * @param command Command describing the action that will update the
		 *                Application's state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 *                                       for the given state.
		 */
		void updateState(Command command);

		/**
		 * Returns the name of the current state as a String.
		 * 
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}

	/**
	 * The first constructor for Application taking AppType, summary, and note as
	 * parameters.
	 * 
	 * @param id      the application ID
	 * @param appType the type of application
	 * @param summary the summary information of application
	 * @param note    the note to be added to the application
	 * @throws IllegalArgumentException if the Application cannot be created
	 */
	public Application(int id, AppType appType, String summary, String note) {
		if (appType == null || summary.isEmpty() || note.isEmpty() || id < 1) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		this.appId = id;
		this.appType = appType;
		this.summary = summary;
		this.reviewer = null;
		this.processPaperwork = false;
		this.notes = new ArrayList<>();
		this.notes.add(note);
		this.state = reviewState;
	}

	/**
	 * The second constructor for Application taking id, state, appType, summary,
	 * reviewer, confirmed, resolution, and notes as parameters.
	 * 
	 * @param id         the application id
	 * @param state      the state of the application
	 * @param appType    the type of application
	 * @param summary    the summary information of the application
	 * @param reviewer   the application reviewer
	 * @param confirmed  ...
	 * @param resolution the application resolution
	 * @param notes      the notes to be added to the application
	 * @throws IllegalArgumentException if the Application cannot be created
	 */
	public Application(int id, String state, String appType, String summary, String reviewer, boolean confirmed,
			String resolution, ArrayList<String> notes) {
		// Implement
	}

	/**
	 * Getter for application ID.
	 * 
	 * @return appId the application ID.
	 */
	public int getAppId() {
		return appId;
	}

	/**
	 * Getter for application state.
	 * 
	 * @return state the application state.
	 */
	public AppState getState() {
		return state;
	}

	/**
	 * Getter for application type.
	 * 
	 * @return appType the application type.
	 */
	public String getAppType() {
		return null;
	}

	/**
	 * Getter for application summary.
	 * 
	 * @return summary the application summary.
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Getter for application reviewer.
	 * 
	 * @return reviewer the application reviewer.
	 */
	public String getReviewer() {
		return reviewer;
	}

	/**
	 * Getter for isProcessed boolean.
	 * 
	 * @return true if paperwork was processed, false if not.
	 */
	public boolean isProcessed() {
		return processPaperwork;
	}

	/**
	 * Getter for application resolution.
	 * 
	 * @return resolution the application resolution.
	 */
	public String getResolution() {
		return null;
	}

	/**
	 * Getter for notes ArrayList.
	 * 
	 * @return notes the ArrayList of notes.
	 */
	public ArrayList<String> getNotes() {
		return notes;
	}

	/**
	 * Setter for application ID.
	 * 
	 * @param appId the appId to set.
	 */
	public void setAppId(int appId) {
		this.appId = appId;
	}

	/**
	 * Setter for application state.
	 * 
	 * @param state the state to set.
	 */
	public void setState(AppState state) {
		this.state = state;
	}

	/**
	 * Setter for application type.
	 * 
	 * @param appType the appType to set.
	 */
	public void setAppType(AppType appType) {
		this.appType = appType;
	}

	/**
	 * Setter for application summary.
	 * 
	 * @param summary the summary to set.
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * Setter for application reviewer.
	 * 
	 * @param reviewer the reviewer to set.
	 */
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	/**
	 * Setter for processPaperwork boolean.
	 * 
	 * @param processPaperwork the processPaperwork to set.
	 */
	public void setProcessPaperwork(boolean processPaperwork) {
		this.processPaperwork = processPaperwork;
	}

	/**
	 * Setter for application resolution.
	 * 
	 * @param resolution the resolution to set.
	 */
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	/**
	 * Setter for notes ArrayList.
	 * 
	 * @param notes the notes to set.
	 */
	public void setNotes(ArrayList<String> notes) {
		this.notes = notes;
	}

	/**
	 * Returns the notes ArrayList as a string representation.
	 * 
	 * @return a string representation of the notes list.
	 */
	public String getNotesString() {
		return null;
	}

	/**
	 * Returns string representation of the Application that is printed during file
	 * save.
	 * 
	 * @return a string representation of the application.
	 */
	public String toString() {
		return null;
	}

	/**
	 * Ensures the note is not null or an empty string.
	 * 
	 * @param note the note being added to the application.
	 */
	private void addNote(String note) {
		// Implement
	}

	/**
	 * Delegates to the current state's updateState method.
	 * 
	 * @param command the command being executed.
	 * @throws UnsupportedOperationException if the Command is not appropriate for
	 *                                       the current state.
	 */
	public void update(Command command) {
		// Implement
	}

	/**
	 * Handles the review state of the application process.
	 */
	private class ReviewState implements AppState {
		/**
		 * Constructor for ReviewState.
		 */
		private ReviewState() {
			// Implement
		}

		/**
		 * Updates the state of the application based on the given command.
		 * 
		 * @param command the command being executed.
		 */
		public void updateState(Command command) {
			// Implement
		}

		/**
		 * Getter for the state of the application.
		 * 
		 * @return the state of the application.
		 */
		public String getStateName() {
			return null;
		}
	}

	/**
	 * Handles the interview state of the application process.
	 */
	private class InterviewState implements AppState {
		/**
		 * Constructor for InterviewState.
		 */
		private InterviewState() {
			// Implement
		}

		/**
		 * Updates the state of the application based on the given command.
		 * 
		 * @param command the command being executed.
		 */
		public void updateState(Command command) {
			// Implement
		}

		/**
		 * Getter for the state of the application.
		 * 
		 * @return the state of the application.
		 */
		public String getStateName() {
			return null;
		}
	}

	/**
	 * Handles the wait list state of the application process.
	 */
	private class WaitlistState implements AppState {
		/**
		 * Constructor for WaitlistState.
		 */
		private WaitlistState() {
			// Implement
		}

		/**
		 * Updates the state of the application based on the given command.
		 * 
		 * @param command the command being executed.
		 */
		public void updateState(Command command) {
			// Implement
		}

		/**
		 * Getter for the state of the application.
		 * 
		 * @return the state of the application.
		 */
		public String getStateName() {
			return null;
		}
	}

	/**
	 * Handles the reference check state of the application process.
	 */
	private class RefChkState implements AppState {
		/**
		 * Constructor for RefChkState.
		 */
		private RefChkState() {
			// Implement
		}

		/**
		 * Updates the state of the application based on the given command.
		 * 
		 * @param command the command being executed.
		 */
		public void updateState(Command command) {
			// Implement
		}

		/**
		 * Getter for the state of the application.
		 * 
		 * @return the state of the application.
		 */
		public String getStateName() {
			return null;
		}
	}

	/**
	 * Handles the offer state of the application process.
	 */
	private class OfferState implements AppState {
		/**
		 * Constructor for OfferState.
		 */
		private OfferState() {
			// Implement
		}

		/**
		 * Updates the state of the application based on the given command.
		 * 
		 * @param command the command being executed.
		 */
		public void updateState(Command command) {
			// Implement
		}

		/**
		 * Getter for the state of the application.
		 * 
		 * @return the state of the application.
		 */
		public String getStateName() {
			return null;
		}
	}

	/**
	 * Handles the closed state of the application process.
	 */
	private class ClosedState implements AppState {
		/**
		 * Constructor for ClosedState.
		 */
		private ClosedState() {
			// Implement
		}

		/**
		 * Updates the state of the application based on the given command.
		 * 
		 * @param command the command being executed.
		 */
		public void updateState(Command command) {
			// Implement
		}

		/**
		 * Getter for the state of the application.
		 * 
		 * @return the state of the application.
		 */
		public String getStateName() {
			return null;
		}
	}

	/**
	 * Getter for the current state of the application.
	 * 
	 * @return the state of the application.
	 */
	public String getStateName() {
		return null;
	}
}
