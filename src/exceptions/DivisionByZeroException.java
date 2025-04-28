package exceptions;

/**
 * Exception thrown when a division by zero operation is attempted.
 * This class extends {@link Exception} and is used to indicate that
 * a division by zero has occurred, which is not a valid mathematical operation.
 * 
 * @author Tamara Kosovac
 * @version 1.0
 * @since 2024-12-12
 */
public class DivisionByZeroException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new {@code DivisionByZeroException} with the specified detail message.
	 * 
	 * @param message The detail message.
	 */
	public DivisionByZeroException(String message) {
		super(message);
	}
}
