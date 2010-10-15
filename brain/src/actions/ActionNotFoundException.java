package actions;

public class ActionNotFoundException extends Exception {
	private static final long serialVersionUID = 899723553515687303L;
	
	public ActionNotFoundException() {
	}

	public ActionNotFoundException(String message) {
		super(message);
	}

	public ActionNotFoundException(Throwable cause) {
		super(cause);
	}

	public ActionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
