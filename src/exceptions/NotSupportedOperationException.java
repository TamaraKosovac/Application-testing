package exceptions;

/**
 * Exception thrown when an operation is not supported.
 * This class extends {@link Exception} and is used to indicate that
 * a particular operation or functionality is not executable.
 * 
 * @author Tamara Kosovac
 * @version 1.0
 * @since 2024-12-12
 */
public class NotSupportedOperationException extends Exception {

	private static final long serialVersionUID = 1L;
    
	 /**
	  * Constructs a new {@code NotSupportedOperationException} with the specified detail message.
	  * 
	  * @param message The detail message.
	  */
	public NotSupportedOperationException(String message) {
		super(message);
	}
}
