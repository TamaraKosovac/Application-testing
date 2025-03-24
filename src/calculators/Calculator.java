package calculators;
import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

/**
 * A class representing a simple calculator that performs basic arithmetic operations.
 * The calculator holds a current value, which can be manipulated using various operations.
 * Supported operations include addition, subtraction, multiplication, and division.
 * The calculator also handles exceptions for division by zero and unsupported operations.
 * 
 * @author Tamara Kosovac
 * @version 1.0
 * @since 2024-12-12
 */
public class Calculator {
	/**
	 * Represents the current value stored in the calculator.
	 */
	private Double currentValue;
	
	/**
	 * Constructs a new Calculator instance with an initial current value of 0.0.
	 */
	public Calculator() {
		this.currentValue = 0.0;
	}
	
	/**
	 * Gets the current value stored in the calculator.
	 * 
	 * @return the current value
	 */
	public Double getCurrentValue() {
		return this.currentValue;
	}
	
	/**
	 * Sets the current value of the calculator.
	 * 
	 * @param currentValue The value to set as the current value.
	 */
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}
	
	/**
	 * Performs a basic arithmetic calculation based on the provided operator.
     * Supports addition ('+'), subtraction ('-'), multiplication ('*'), and division ('/').
     * If the operator is not supported or if there is an attempt to divide by zero, exceptions are thrown.
     * 
	 * @param value The value to operate with.
	 * @param operator The operator representing the arithmetic operation.
	 * @throws DivisionByZeroException If division by zero is attempted.
	 * @throws NotSupportedOperationException If the operator is not one of the supported operations.
	 * @throws IllegalArgumentException If the provided value is null.
	 */
	public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {
		if(value == null) {
			throw new IllegalArgumentException("Value cannot be null");
		}
		
		switch(operator) {
		case '+':
			currentValue += value;
			break;
		case '-':
			currentValue -= value;
			break;
		case '*':
			currentValue *= value;
			break;
		case '/':
			if(value == 0) {
				throw new DivisionByZeroException("Cannot divide by zero");
			}
			currentValue /= value;
			break;
		default: 
			throw new NotSupportedOperationException("Operation not supported");
		}
	}
}
