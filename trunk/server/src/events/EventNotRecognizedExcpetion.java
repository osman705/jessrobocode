package events;

public class EventNotRecognizedExcpetion extends Exception {
	private static final long serialVersionUID = -7535220606169275694L;
	
	public EventNotRecognizedExcpetion() {
	}

	public EventNotRecognizedExcpetion(String message) {
		super(message);
	}

	public EventNotRecognizedExcpetion(Throwable cause) {
		super(cause);
	}

	public EventNotRecognizedExcpetion(String message, Throwable cause) {
		super(message, cause);
	}
}
