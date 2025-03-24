package calculators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Calculator} class.
 * This class tests functionality of the {@link Calculator} class.
 * 
 * @author Tamara Kosovac
 * @version 1.0
 * @since 2024-12-12
 */
class CalculatorTest {
	/**
	 * Instance of Calculator used for performing basic calculations.
	 */
	private Calculator calculator;

	/**
	 * Set up method that runs once before all tests.
	 * This method is used to set up any global resources or configurations.
	 * 
	 * @throws Exception if an error occurs during setup
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tear down method that runs once after all tests.
	 * This method is used to clean up any global resources or configurations.
	 * 
	 * @throws Exception if an error occurs during cleanup
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Set up method that runs before each test.
	 * This method is used to initialize the calculator instance for each test.
	 * 
	 * @throws Exception if an error occurs during setup
	 */
	@BeforeEach
	void setUp() throws Exception {
		calculator = new Calculator();
	}
    
	/**
	 * Tear down method that runs after each test.
	 * This method is used to clean up after each test.
	 * 
	 * @throws Exception if an error occurs during cleanup
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
    
	/**
	 * Test the constructor of the {@link Calculator} class.
	 * This test verifies that the calculator is initialized with a default current value of 0.0.
	 */
	@Test
	void testCalculatorConstructor() {
		assertThat(0.0, is(calculator.getCurrentValue()));
	}
	
	/**
	 * Test the setter and getter for the current value of the calculator.
	 * This test verifies that the current value can be set and retrieved correctly.
	 */
	@Test
	void testSetAndGetCurrentValue() {
		calculator.setCurrentValue(10.0);
		assertThat(10.0, is(calculator.getCurrentValue()));
	}
	
	
	/**
	 * Tests the calculator's basic arithmetic operations using parameterized test data.
     * This method uses various sets of input data provided by the {@link #provideCalculateTestData()} method
     * to verify that the calculator correctly calculates the expected result based on a given operator
     * and value.
     * 
	 * @param startValue The start value for currentValue.
	 * @param value The value to be used in the arithmetic operation.
	 * @param result The expected result of the calculation.
	 * @param operator The arithmetic operator to perform the calculation.
	 * @throws DivisionByZeroException
	 * @throws NotSupportedOperationException
	 */
	@ParameterizedTest
	@MethodSource("provideCalculateTestData")
	void testCalculate(Double startValue, Double value, Double result, char operator) throws DivisionByZeroException, NotSupportedOperationException {
		calculator.setCurrentValue(startValue);
		calculator.calculate(value, operator);
		assertThat(result, is(calculator.getCurrentValue()));
	}
	
	/**
	 *  Provides a stream of test data for testing a calculator's basic operations.
	 *  
	 * @return A stream of object arrays.
	 */
	private static Stream<Arguments> provideCalculateTestData() {
		return Stream.of(
				Arguments.of(1.0, 1.0, 2.0, '+'),
				Arguments.of(1.0, 1.0, 0.0, '-'),
				Arguments.of(1.0, 1.0, 1.0, '*'),
				Arguments.of(1.0, 1.0, 1.0, '/'),
				Arguments.of(-1.0, -1.0, -2.0, '+'),
				Arguments.of(-1.0, -1.0, 0.0, '-'),
				Arguments.of(-1.0, -1.0, 1.0, '*'),
				Arguments.of(-1.0, -1.0, 1.0, '/'),
				Arguments.of(0.0, 0.0, 0.0, '+'),
				Arguments.of(0.0, 0.0, 0.0, '-'),
				Arguments.of(0.0, 0.0, 0.0, '*'),
				Arguments.of(-5.0, 2.0, -3.0, '+'),
				Arguments.of(-5.0, 2.0, -7.0, '-'),
				Arguments.of(-5.0, 2.0, -10.0, '*'),
				Arguments.of(-5.0, 2.0, -2.5, '/'),
				Arguments.of(5.0, -2.0, 3.0, '+'),
				Arguments.of(5.0, -2.0, 7.0, '-'),
				Arguments.of(5.0, -2.0, -10.0, '*'),
				Arguments.of(5.0, -2.0, -2.5, '/'),
				Arguments.of(0.0, 10.0, 10.0, '+'),
				Arguments.of(0.0, 10.0, -10.0, '-'),
				Arguments.of(0.0, 10.0, 0.0, '*'),
				Arguments.of(0.0, 10.0, 0.0, '/'),
				Arguments.of(0.0, -6.0, -6.0, '+'),
				Arguments.of(0.0, -6.0, 6.0, '-'),
				Arguments.of(0.0, -6.0, -0.0, '*'),
				Arguments.of(0.0, -6.0, -0.0, '/'),
				Arguments.of(20.0, 0.0, 20.0, '+'),
				Arguments.of(20.0, 0.0, 20.0, '-'),
				Arguments.of(20.0, 0.0, 0.0, '*'),
				Arguments.of(-18.0, 0.0, -18.0, '+'),
				Arguments.of(-18.0, 0.0, -18.0, '-'),
				Arguments.of(-18.0, 0.0, -0.0, '*')
		);
	}
	
	
	/**
	 * Tests the calculator's behavior when a division by zero operation is performed.
     * This method uses parameterized test data provided by the {@link #provideDivisionByZeroTestData()} method
     * to verify that the calculator correctly throws a {@link DivisionByZeroException} when attempting
     * a division by zero, and that the exception's message matches the expected message.
     * 
	 * @param startValue The initial value of the calculator before performing the operation.
	 * @param value The value to be used in the division operation.
	 * @param operator The arithmetic operator for the operation, expected to be '/'.
	 * @param message The expected exception message when a division by zero occurs.
	 */
	@ParameterizedTest
	@MethodSource("provideDivisionByZeroTestData")
	void testDivisionByZeroException(Double startValue, Double value, char operator, String message) {
		calculator.setCurrentValue(startValue);
		DivisionByZeroException exception = assertThrows(DivisionByZeroException.class,
			() -> calculator.calculate(value, operator));
		assertThat("Messages should match", exception.getMessage(), is(message));
	}
	
	
	/**
	 * Provides a stream of test data for testing division by zero scenarios.
	 * 
	 * @return A stream of object arrays.
	 */
	private static Stream<Arguments> provideDivisionByZeroTestData() {
	    return Stream.of(
	            Arguments.of(-5.0, 0.0, '/', "Cannot divide by zero"),
	            Arguments.of(0.0, 0.0, '/', "Cannot divide by zero"),
	            Arguments.of(5.0, 0.0, '/', "Cannot divide by zero"),
	            Arguments.of(100.0, 0.0, '/', "Cannot divide by zero"),
	            Arguments.of(-100.0, 0.0, '/', "Cannot divide by zero"),
	            Arguments.of(Double.MAX_VALUE, 0.0, '/', "Cannot divide by zero"),
	            Arguments.of(Double.MIN_VALUE, 0.0, '/', "Cannot divide by zero"),
	            Arguments.of(1.0, -0.0, '/', "Cannot divide by zero"), // Handle negative zero
	            Arguments.of(-1.0, -0.0, '/', "Cannot divide by zero"), // Handle negative zero
	            Arguments.of(Double.POSITIVE_INFINITY, 0.0, '/', "Cannot divide by zero"), // Infinity / 0.0
	            Arguments.of(Double.NEGATIVE_INFINITY, 0.0, '/', "Cannot divide by zero"), // -Infinity / 0.0
	            Arguments.of(Double.NaN, 0.0, '/', "Cannot divide by zero"), // NaN / 0.0
	            Arguments.of(1.0e-10, 0.0, '/', "Cannot divide by zero"), // Very small number / 0.0
	            Arguments.of(1.0e10, 0.0, '/', "Cannot divide by zero"), // Very large number / 0.0
	            Arguments.of(123.0, 0.0, '/', "Cannot divide by zero"), // Standard positive number / 0.0
	            Arguments.of(-123.0, 0.0, '/', "Cannot divide by zero")
	    );
	}
	
	
	/**
	 * Tests the behavior of the {@code calculator.calculate} method when an unsupported 
     * operation is attempted. Verifies that a {@link NotSupportedOperationException} is thrown 
     * and that the exception message matches the expected value.
     * 
	 * @param startValue The initial value of the calculator before performing the operation.
	 * @param value The value to be used in the division operation.
	 * @param operator The operator used for the calculation.
	 * @param message The expected exception message when not supported operators occurs.
	 */
	@ParameterizedTest
	@MethodSource("provideInvalidOperationTestData")
	void testNotSupportedOperationException(Double startValue, Double value, char operator, String message) {
		calculator.setCurrentValue(startValue);
		NotSupportedOperationException exception = assertThrows(NotSupportedOperationException.class,
				() -> calculator.calculate(value, operator));
		assertThat("Messages should match", exception.getMessage(), is(message));
	}
	

	/**
	 * Provides a stream of test data for testing invalid operation scenarios.
	 * 
	 * @return A stream of object arrays.
	 */
	private static Stream<Arguments> provideInvalidOperationTestData() {
	    return Stream.of(
	    		Arguments.of(5.0, 2.0, '^', "Operation not supported"), 
	            Arguments.of(10.0, -2.0, '&', "Operation not supported"), 
	            Arguments.of(-5.2, -2.5, '!', "Operation not supported"), 
	            Arguments.of(50.0, 10.0, '~', "Operation not supported"), 
	            Arguments.of(0.0, 10.0, '%', "Operation not supported"), 
	            Arguments.of(10.0, 3.0, '=', "Operation not supported"), 
	            Arguments.of(15.0, 3.0, '@', "Operation not supported"), 
	            Arguments.of(100.0, -5.0, '$', "Operation not supported"), 
	            Arguments.of(7.0, 3.0, '>', "Operation not supported"), 
	            Arguments.of(100.0, 2.0, '<', "Operation not supported"), 
	            Arguments.of(0.0, 1.0, '|', "Operation not supported"), 
	            Arguments.of(3.0, 2.0, '(', "Operation not supported"), 
	            Arguments.of(7.0, 3.0, ')', "Operation not supported"), 
	            Arguments.of(5.0, 2.0, 'a', "Operation not supported"), 
	            Arguments.of(10.0, 3.0, '#', "Operation not supported"),
	            Arguments.of(0.0, 5.0, '[', "Operation not supported"), 
	            Arguments.of(7.0, 2.0, ']', "Operation not supported"), 
	            Arguments.of(8.0, 2.0, '\\', "Operation not supported"),
	            Arguments.of(5.0, 2.0, ';', "Operation not supported"), 
	            Arguments.of(6.0, 2.0, ':', "Operation not supported"), 
	            Arguments.of(9.0, 2.0, ',', "Operation not supported"),
	            Arguments.of(3.0, 2.0, '.', "Operation not supported"), 
	            Arguments.of(10.0, 3.0, '@', "Operation not supported"), 
	            Arguments.of(4.0, 1.0, '_', "Operation not supported"), 
	            Arguments.of(5.0, 2.0, '?', "Operation not supported"), 
	            Arguments.of(8.0, 3.0, '!', "Operation not supported")
	            

	   
	    );
	}
	
	
	/**
	 * Tests the behavior of the {@code calculator.calculate} method when invalid arguments 
     * are provided. Verifies that an {@link IllegalArgumentException} is thrown and that the 
     * exception message matches the expected value.
	 * 
	 * @param value The value used in the operation, expected to cause an invalid argument scenario.
	 * @param operator The operator used for the calculation.
	 * @param message The expected exception message when illegal argument value occurs.
	 */
	@ParameterizedTest
	@MethodSource("provideIllegalArgumentTestData")
	void testIllegalArgumentException(Double value, char operator, String message) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> calculator.calculate(value, operator));
		assertThat("Messages should match", exception.getMessage(),is(message));
	}
	

	/**
	 * Provides a stream of test data for testing illegal argument scenarios.
	 * 
	 * @return A stream of object arrays.
	 */
	private static Stream<Arguments> provideIllegalArgumentTestData() {
		return Stream.of(
				Arguments.of(null, '+', "Value cannot be null"),
				Arguments.of(null, '-', "Value cannot be null"),
				Arguments.of(null, '*', "Value cannot be null"),
				Arguments.of(null, '/', "Value cannot be null"),
				Arguments.of(null, 'a', "Value cannot be null"),
				Arguments.of(null, '!', "Value cannot be null"),
				Arguments.of(null, '.', "Value cannot be null"),
				Arguments.of(null, '1', "Value cannot be null"),
				Arguments.of(null, ',', "Value cannot be null"),
				Arguments.of(null, '?', "Value cannot be null"),
				Arguments.of(null, '=', "Value cannot be null"),
				Arguments.of(null, '%', "Value cannot be null"),
	            Arguments.of(null, '&', "Value cannot be null"),
	            Arguments.of(null, '$', "Value cannot be null"),
	            Arguments.of(null, '@', "Value cannot be null"),
	            Arguments.of(null, '^', "Value cannot be null"),
	            Arguments.of(null, '#', "Value cannot be null"),
	            Arguments.of(null, '~', "Value cannot be null"),
	            Arguments.of(null, '>', "Value cannot be null"),
	            Arguments.of(null, '<', "Value cannot be null"),
	            Arguments.of(null, '|', "Value cannot be null"),
	            Arguments.of(null, '`', "Value cannot be null"),
	            Arguments.of(null, ' ', "Value cannot be null"),
	            Arguments.of(null, '\t', "Value cannot be null"),
	            Arguments.of(null, '\n', "Value cannot be null"),
	            Arguments.of(null, '\\', "Value cannot be null"),
	            Arguments.of(null, '[', "Value cannot be null"),
	            Arguments.of(null, ']', "Value cannot be null"),
	            Arguments.of(null, '{', "Value cannot be null"),
	            Arguments.of(null, '}', "Value cannot be null"),
	            Arguments.of(null, '0', "Value cannot be null"),
	            Arguments.of(null, '9', "Value cannot be null"),
	            Arguments.of(null, '\r', "Value cannot be null"),
	            Arguments.of(null, '\b', "Value cannot be null"),
	            Arguments.of(null, '\f', "Value cannot be null")
	           
		);
	}

}
