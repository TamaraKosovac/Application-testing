package calculators;

import exceptions.NumberNotInAreaException;
import exceptions.NotSupportedOperationException;

/**
 * The CalculatorAdvanced class extends the basic Calculator functionality
 * by adding support for advanced operations such as factorials, power calculations,
 * and characteristic checks like Armstrong and perfect numbers.
 * 
 * @author Tamara Kosovac
 * @version 1.0
 * @since 2024-12-12
 */
public class CalculatorAdvanced extends Calculator {
	/**
	 * Default constructor that initializes the advanced calculator.
	 */
	public CalculatorAdvanced() {
		super();
	}

	/**
	 * Performs advanced calculations based on the provided action.
	 * 
	 * @param action The operation to perform.
	 * @throws NumberNotInAreaException If the current value is out of the allowed range for the action.
	 * @throws NotSupportedOperationException If the action is not supported.
	 */
	public void calculateAdvanced(char action) throws NumberNotInAreaException, NotSupportedOperationException{
		Double currentValue = getCurrentValue();
		if(action == '!') {
			if(currentValue < 0.0 || currentValue > 10.0) {
				throw new NumberNotInAreaException("Number not in area");
			}
			setCurrentValue(factorial(currentValue.intValue()));
		} else if(action >= '0' && action <= '9') {
			if(currentValue < 0.0) {
				throw new IllegalArgumentException("Negative number");
			}
			setCurrentValue(powerChar(currentValue, action));
		} else {
			throw new NotSupportedOperationException("Action not supported");
		}
	}
	
	/**
	 * Calculates the factorial of a given integer using recursion.
	 * 
	 * @param n The integer for which the factorial is calculated.
	 * @return The factorial of the given integer as a Double.
	 */
	private Double factorial(int n) {
		if (n == 0) {
            return 1.0;
        }
        return n * factorial(n - 1);
	}
	
	/**
	 * Raises a value to the power of a digit represented as a character.
	 * 
	 * @param value The base value.
	 * @param action The character representing the power (0-9).
	 * @return The result of raising the value to the specified power as a Double.
	 */
	private Double powerChar(Double value, char action) {
	    int exponent = action - '0';  
	    if (exponent == 0) {
	        return 1.0;
	    }
	    return value * powerChar(value, (char) (exponent - 1 + '0'));  
	}


	/**
	 * Checks if the current value has a specific characteristic.
	 * 
	 * @param value The characteristic to check.
	 * @return True if the current value has the characteristic, false otherwise.
	 * @throws NumberNotInAreaException If the current value is less than 1.
	 * @throws NotSupportedOperationException If the value is not a supported characteristic.
	 */
	public Boolean hasCharacteristic(char value) throws NumberNotInAreaException, NotSupportedOperationException {
		Double currentValue = getCurrentValue();
		if (currentValue.intValue() < 1) {
            throw new NumberNotInAreaException("Number not in area");
        }
		if (value == 'A') {
            return isArmstrong(currentValue.intValue());
        } else if (value == 'P') {
            return isPerfect(currentValue.intValue());
        } else {
            throw new NotSupportedOperationException("Action not supported");
        }
	}
	
	/**
	 * Determines if a given integer is an Armstrong number. 
	 * 
	 * @param number The integer to check.
	 * @return True if the number is an Armstrong number, false otherwise.
	 */
	private Boolean isArmstrong(int number) {
	    return isArmstrongHelper(number, number, String.valueOf(number).length(), 0);
	}

	/**
	 * Helper method to determine if a number is an Armstrong number using recursion.
	 *  
	 * @param originalNumber The original number being checked.
	 * @param number The remaining part of the number.
	 * @param digits The number of digits in the original number.
	 * @param sum The cumulative sum of digits raised to the power of the digit count.
	 * @return True if the number is an Armstrong number, false otherwise.
	 */
	private Boolean isArmstrongHelper(int originalNumber, int number, int digits, double sum) {
	    if (number == 0) {
	        return sum == originalNumber;
	    }
	    int digit = number % 10;
	    sum += pow(digit, digits);
	    return isArmstrongHelper(originalNumber, number / 10, digits, sum);
	}
	
	/**
	 * Calculates the power of a base raised to an exponent using recursion.
	 * 
	 * @param base The base integer.
	 * @param exponent The exponent integer.
	 * @return The result of base raised to the exponent as an integer.
	 */
	private int pow(int base, int exponent) {
		if (exponent == 0) {
	        return 1;
	    }
	    return base * pow(base, exponent - 1);
	}

	/**
	 * Determines if a given integer is a perfect number.
	 * 
	 * @param number The integer to check.
	 * @return True if the number is a perfect number, false otherwise.
	 */
	private Boolean isPerfect(int number) {
		if (number == 1) {
            return false;
        }

        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }

        return sum == number;
	}
}