package edu.ncsu.csc216.app_manager.model.application;

import java.util.ArrayList;

import edu.ncsu.csc216.app_manager.model.command.Command;
import edu.ncsu.csc216.app_manager.model.command.Command.Resolution;

/**
 * The Application class handles the Application object and holds Application
 * (context), AppState (interface) and 6 concrete state classes.
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
		if (appType == null || summary == null || summary.isEmpty() || note == null || note.isEmpty() || id < 1) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		setAppId(id);
		setState(REVIEW_NAME);
		this.appType = appType;
		setAppType(getAppType());
		setSummary(summary);

		this.reviewer = null;
		this.processPaperwork = false;
		this.resolution = null;

		this.notes = new ArrayList<String>();
		addNote(note);
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
	 * @param confirmed  whether the paperwork has been processed.
	 * @param resolution the application resolution
	 * @param notes      the notes to be added to the application
	 */
	public Application(int id, String state, String appType, String summary, String reviewer, boolean confirmed,
			String resolution, ArrayList<String> notes) {
		setAppId(id);
		setState(state);
		setAppType(appType);
		setSummary(summary);
		setReviewer(reviewer);
		setProcessPaperwork(confirmed);
		setResolution(resolution);
		setNotes(notes);
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
	 * @return state the application state as a string.
	 */
	public String getState() {
		return getStateName();
	}

	/**
	 * Getter for application type.
	 * 
	 * @return appType the application type as a string.
	 * @throws IllegalArgumentException if the application type is invalid.
	 */
	public String getAppType() {
		switch (appType) {
		case AppType.NEW:
			return A_NEW;
		case AppType.OLD:
			return A_OLD;
		case AppType.HIRED:
			return A_HIRED;
		default:
			throw new IllegalArgumentException("Application cannot be created.");
		}
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
	 * @return resolution the application resolution as a string.
	 * @throws IllegalArgumentException if the resolution is invalid.
	 */
	public String getResolution() {
		switch (resolution) {
		case Resolution.REVCOMPLETED:
			return Command.R_REVCOMPLETED;
		case Resolution.INTCOMPLETED:
			return Command.R_INTCOMPLETED;
		case Resolution.REFCHKCOMPLETED:
			return Command.R_REFCHKCOMPLETED;
		case Resolution.OFFERCOMPLETED:
			return Command.R_OFFERCOMPLETED;
		case null:
			return null;
		default:
			throw new IllegalArgumentException("Application cannot be created.");
		}
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
	 * @param id the appId to set.
	 * @throws IllegalArgumentException if the id is invalid.
	 */
	private void setAppId(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		this.appId = id;
	}

	/**
	 * Setter for application state.
	 * 
	 * @param state the state to set.
	 * @throws IllegalArgumentException if the state is invalid.
	 */
	private void setState(String state) {
		if (state == null || state.isEmpty()) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		switch (state) {
		case REVIEW_NAME:
			this.state = reviewState;
			break;
		case INTERVIEW_NAME:
			this.state = interviewState;
			break;
		case WAITLIST_NAME:
			this.state = waitlistState;
			break;
		case REFCHK_NAME:
			this.state = refChkState;
			break;
		case OFFER_NAME:
			this.state = offerState;
			break;
		case CLOSED_NAME:
			this.state = closedState;
			break;
		default:
			throw new IllegalArgumentException("Application cannot be created.");
		}
	}

	/**
	 * Setter for application type.
	 * 
	 * @param appType the appType to set.
	 * @throws IllegalArgumentException if the application type is invalid.
	 */
	private void setAppType(String appType) {
		if (appType == null || appType.isEmpty()) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		switch (appType) {
		case A_NEW:
			this.appType = AppType.NEW;
			break;
		case A_OLD:
			this.appType = AppType.OLD;
			break;
		case A_HIRED:
			this.appType = AppType.HIRED;
			break;
		default:
			throw new IllegalArgumentException("Application cannot be created.");
		}
	}

	/**
	 * Setter for application summary.
	 * 
	 * @param summary the summary to set.
	 * @throws IllegalArgumentException if the summary is invalid.
	 */
	private void setSummary(String summary) {
		if (summary == null || summary.isEmpty()) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		this.summary = summary;
	}

	/**
	 * Setter for application reviewer.
	 * 
	 * @param reviewer the reviewer to set.
	 */
	private void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	/**
	 * Setter for processPaperwork boolean.
	 * 
	 * @param processPaperwork the processPaperwork to set.
	 */
	private void setProcessPaperwork(boolean processPaperwork) {
		this.processPaperwork = processPaperwork;
	}

	/**
	 * Setter for application resolution.
	 * 
	 * @param resolution the resolution to set.
	 */
	private void setResolution(String resolution) {
		if (resolution == null) {
			this.resolution = null;
			return;
		}
		switch (resolution) {
		case Command.R_REVCOMPLETED:
			this.resolution = Resolution.REVCOMPLETED;
			break;
		case Command.R_INTCOMPLETED:
			this.resolution = Resolution.INTCOMPLETED;
			break;
		case Command.R_REFCHKCOMPLETED:
			this.resolution = Resolution.REFCHKCOMPLETED;
			break;
		case Command.R_OFFERCOMPLETED:
			this.resolution = Resolution.OFFERCOMPLETED;
			break;
		default:
			this.resolution = null;
		}
	}

	/**
	 * Setter for notes ArrayList.
	 * 
	 * @param notes the notes to set.
	 * @throws IllegalArgumentException if the notes are invalid.
	 */
	private void setNotes(ArrayList<String> notes) {
		if (notes == null || notes.isEmpty()) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		this.notes = notes;
	}

	/**
	 * Returns the notes ArrayList as a string representation.
	 * 
	 * @return a string representation of the notes list.
	 */
	public String getNotesString() {
		String s = "";
		for (int i = 0; i < notes.size(); i++) {
			s += "-" + notes.get(i) + "\n";
		}
		return s;
	}

	/**
	 * Returns string representation of the Application that is printed during file
	 * save.
	 * 
	 * @return a string representation of the application.
	 */
	public String toString() {
		String s = "";
		s += "*" + getAppId() + "," + getState() + "," + getAppType() + "," + getSummary() + ",";

		if (getReviewer() == null || getReviewer().isEmpty()) {
			s += "null," + isProcessed() + ",";
		} else {
			s += getReviewer() + "," + isProcessed() + ",";
		}

		if (getResolution() == null || getResolution().isEmpty()) {
			s += "\n";
		} else {
			s += getResolution() + "\n";
		}

		s += "-" + notes.get(0);
		for (int i = 1; i < notes.size() - 1; i++) {
			String note = notes.get(i);
			s += " -" + note;
		}

		s += "\n";
		return s;
	}

	/**
	 * Adds a formatted note with an appended state name.
	 * 
	 * @param note the note being added to the application.
	 * @throws IllegalArgumentException if the note is invalid.
	 */
	private void addNote(String note) {
		if (note == null || note.isEmpty()) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		String formatNote = "[" + state.getStateName() + "] " + note;
		notes.add(formatNote);
	}

	/**
	 * Delegates to the current state's updateState method.
	 * 
	 * @param command the command being executed.
	 * @throws UnsupportedOperationException if the Command is not appropriate for
	 *                                       the current state.
	 */
	public void update(Command command) {
		if (command == null) {
			throw new UnsupportedOperationException("Invalid information.");
		}
		state.updateState(command);
	}

	/**
	 * Returns application state as a String.
	 * 
	 * @return state the string representation of appState.
	 * @throws IllegalArgumentException if the state is invalid.
	 */
	public String getStateName() {
		if (state instanceof ReviewState) {
			return REVIEW_NAME;
		} else if (state instanceof InterviewState) {
			return INTERVIEW_NAME;
		} else if (state instanceof WaitlistState) {
			return WAITLIST_NAME;
		} else if (state instanceof RefChkState) {
			return REFCHK_NAME;
		} else if (state instanceof OfferState) {
			return OFFER_NAME;
		} else if (state instanceof ClosedState) {
			return CLOSED_NAME;
		} else {
			throw new IllegalArgumentException("Application cannot be created.");
		}
	}

	/**
	 * Handles the review state of the application process.
	 */
	private class ReviewState implements AppState {

		/**
		 * Constructor for ReviewState.
		 */
		private ReviewState() {
			setState(REVIEW_NAME);
		}

		/**
		 * Updates the state of the application based on the given command.
		 * 
		 * @param command the command being executed.
		 * @throws UnsupportedOperationException if the Command is not valid for the
		 *                                       current state.
		 */
		public void updateState(Command command) {
			if (command == null) {
				throw new UnsupportedOperationException("Invalid information.");
			}

			switch (command.getCommand()) {
			case ACCEPT:
				if (command.getReviewerId() == null || command.getReviewerId().isEmpty()) {
					throw new UnsupportedOperationException("Invalid information.");
				}
				setReviewer(command.getReviewerId());
				setState(INTERVIEW_NAME);
				setAppType(A_OLD);
				addNote(command.getNote());
				break;
			case STANDBY:
				setResolution(Command.R_REVCOMPLETED);
				setState(WAITLIST_NAME);
				addNote(command.getNote());
				break;
			case REJECT:
				setResolution(Command.R_REVCOMPLETED);
				setState(CLOSED_NAME);
				addNote(command.getNote());
				break;
			default:
				throw new UnsupportedOperationException("Invalid information.");
			}
		}

		/**
		 * Getter for the state of the application.
		 * 
		 * @return the state of the application.
		 */
		public String getStateName() {
			return REVIEW_NAME;
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
			setState(INTERVIEW_NAME);
		}

		/**
		 * Updates the state of the application based on the given command.
		 * 
		 * @param command the command being executed.
		 * @throws UnsupportedOperationException if the Command is not valid for the
		 *                                       current state.
		 */
		public void updateState(Command command) {
			if (command == null || !isProcessed() || command.getNote().isEmpty()) {
				throw new UnsupportedOperationException("Invalid information.");
			}
			switch (command.getCommand()) {
			case ACCEPT:
				if (command.getReviewerId() != null && !command.getReviewerId().isEmpty()) {
					setReviewer(command.getReviewerId());
				}
				setState(REFCHK_NAME);
				addNote(command.getNote());
				break;
			case STANDBY:
				if (command.getReviewerId() == null && command.getReviewerId().isEmpty()) {
					throw new IllegalArgumentException("Invalid information.");
				}
				setReviewer(command.getReviewerId());
				setResolution(Command.R_INTCOMPLETED);
				setState(WAITLIST_NAME);
				addNote(command.getNote());
				break;
			case REJECT:
				setResolution(Command.R_INTCOMPLETED);
				setState(CLOSED_NAME);
				addNote(command.getNote());
				break;
			default:
				throw new UnsupportedOperationException("Invalid information.");
			}
		}

		/**
		 * Getter for the state of the application.
		 * 
		 * @return the state of the application.
		 */
		public String getStateName() {
			return INTERVIEW_NAME;
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
			setState(WAITLIST_NAME);
		}

		/**
		 * Updates the state of the application based on the given command.
		 * 
		 * @param command the command being executed.
		 * @throws UnsupportedOperationException if the Command is not valid for the
		 *                                       current state.
		 */
		public void updateState(Command command) {
			if (command.getNote().isEmpty() || isProcessed()) {
				throw new UnsupportedOperationException("Invalid information.");
			}
			switch (command.getCommand()) {
			case REOPEN:
				if (getResolution() == Command.R_INTCOMPLETED) {
					if (reviewer == null || reviewer.isEmpty()) {
						throw new UnsupportedOperationException("Invalid information.");
					}
					setState(REFCHK_NAME);
					addNote(command.getNote());
					break;
				}
				if (getResolution() == Command.R_REVCOMPLETED && getAppType() == A_NEW) {
					setAppType(A_OLD);
					setState(REVIEW_NAME);
					setResolution(null);
					addNote(command.getNote());
					break;
				}
			default:
				throw new UnsupportedOperationException("Invalid information.");
			}
		}

		/**
		 * Getter for the state of the application.
		 * 
		 * @return the state of the application.
		 */
		public String getStateName() {
			return WAITLIST_NAME;
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
			setState(REFCHK_NAME);
		}

		/**
		 * Updates the state of the application based on the given command.
		 * 
		 * @param command the command being executed.
		 * @throws UnsupportedOperationException if the Command is not valid for the
		 *                                       current state.
		 */
		public void updateState(Command command) {
			if (command.getNote().isEmpty() || isProcessed()) {
				throw new UnsupportedOperationException("Invalid information.");
			}
			switch (command.getCommand()) {
			case ACCEPT:
				if (reviewer == null || reviewer.isEmpty() || isProcessed()) {
					throw new UnsupportedOperationException("Invalid information.");
				}
				setState(OFFER_NAME);
				addNote(command.getNote());
				break;
			case REJECT:
				setResolution(Command.R_REFCHKCOMPLETED);
				setState(CLOSED_NAME);
				addNote(command.getNote());
				break;
			default:
				throw new UnsupportedOperationException("Invalid information.");
			}
		}

		/**
		 * Getter for the state of the application.
		 * 
		 * @return the state of the application.
		 */
		public String getStateName() {
			return REFCHK_NAME;
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
			setState(OFFER_NAME);
		}

		/**
		 * Updates the state of the application based on the given command.
		 * 
		 * @param command the command being executed.
		 * @throws UnsupportedOperationException if the Command is not valid for the
		 *                                       current state.
		 */
		public void updateState(Command command) {
			if (command.getNote().isEmpty()) {
				throw new UnsupportedOperationException("Invalid information.");
			}
			switch (command.getCommand()) {
			case ACCEPT:
				if (reviewer == null || !isProcessed()) {
					throw new UnsupportedOperationException("Invalid information.");
				}
				setResolution(Command.R_OFFERCOMPLETED);
				setState(CLOSED_NAME);
				addNote(command.getNote());
				break;
			case REJECT:
				setResolution(Command.R_OFFERCOMPLETED);
				setState(CLOSED_NAME);
				addNote(command.getNote());
				break;
			default:
				throw new UnsupportedOperationException("Invalid information.");
			}
		}

		/**
		 * Getter for the state of the application.
		 * 
		 * @return the state of the application.
		 */
		public String getStateName() {
			return OFFER_NAME;
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
			setState(CLOSED_NAME);
		}

		/**
		 * Updates the state of the application based on the given command.
		 * 
		 * @param command the command being executed.
		 * @throws UnsupportedOperationException if the Command is not valid for the
		 *                                       current state.
		 */
		public void updateState(Command command) {
			if (command.getNote().isEmpty()) {
				throw new UnsupportedOperationException("Invalid information.");
			}
			switch (command.getCommand()) {
			case REOPEN:
				if (getAppType() == A_NEW && getResolution() == Command.R_REVCOMPLETED) {
					setAppType(A_OLD);
					setState(REVIEW_NAME);
					addNote(command.getNote());
					setResolution(null);

				} else if (getAppType() == A_OLD || getResolution() != Command.R_REVCOMPLETED) {
					throw new UnsupportedOperationException("Invalid information.");
				}
				break;
			default:
				throw new UnsupportedOperationException("Invalid information.");
			}
		}

		/**
		 * Getter for the state of the application.
		 * 
		 * @return the state of the application.
		 */
		public String getStateName() {
			return CLOSED_NAME;
		}
	}
}
