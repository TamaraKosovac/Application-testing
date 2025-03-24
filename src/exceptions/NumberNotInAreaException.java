package exceptions;

/**
 * Exception thrown when a number is not within a specified area or range.
 * This exception is a subclass of {@link Exception} and provides two constructors:
 * one with no arguments and another that accepts a message to describe the error.
 * 
 * @author Tamara Kosovac
 * @version 1.0
 * @since 2024-12-12
 */
public class NumberNotInAreaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * Constructs a new {@code NumberNotInAreaException} with the specified detail message.
     * The message provides additional context about the error.
     * 
	 * @param message The detail message.
	 */
	public NumberNotInAreaException(String message) {
		super(message);
	}
}
