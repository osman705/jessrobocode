package commands;

public class CommandNotFoundException extends Exception {
	private static final long serialVersionUID = 6298818546247963372L;

	public CommandNotFoundException() {

	}

	public CommandNotFoundException(String message) {
		super(message);
	}

	public CommandNotFoundException(Throwable cause) {
		super(cause);
	}

	public CommandNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
