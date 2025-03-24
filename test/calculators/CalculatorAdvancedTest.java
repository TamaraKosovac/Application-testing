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

import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

 /**
  * Unit tests for the {@link CalculatorAdvanced} class.
  * This class tests functionality of the {@link CalculatorAdvanced} class.
  * 
  * @author Tamara Kosovac
  * @version 1.0
  * @since 2024-12-12
  */
class CalculatorAdvancedTest {
	/**
	 * Instance of CalculatorAdvanced used for performing advanced calculations.
	 */
	private CalculatorAdvanced calculatorAdvanced;

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
		calculatorAdvanced = new CalculatorAdvanced();
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
	 * Test the constructor of the {@link CalculatorAdvanced} class.
	 * This test verifies that the calculator is initialized with a default current value of 0.0.
	 */
	@Test
	void testCalculatorAdvancedConstructor() {
		assertThat(0.0, is(calculatorAdvanced.getCurrentValue()));
	}
	
	/**
	 * Test the setter and getter for the current value of the calculator.
	 * This test verifies that the current value can be set and retrieved correctly.
	 */
	@Test
	void testSetAndGetCurrentValueCalculatorAdvanced() {
		calculatorAdvanced.setCurrentValue(10.0);
		assertThat(10.0, is(calculatorAdvanced.getCurrentValue()));
	}
	
	/**
	 * Tests the calculator's advanced operations using parameterized test data.
     * This method uses various sets of input data provided by the {@link #provideCalculateAdvancedTestData()} method
     * to verify that the calculator correctly calculates the expected result based on a given operator
     * and value.
     * 
	 * @param value The start value for currentValue.
	 * @param result The expected result of the calculation.
	 * @param action The action to perform.
	 * @throws NumberNotInAreaException
	 * @throws NotSupportedOperationException
	 */
	@ParameterizedTest
	@MethodSource("provideCalculateAdvancedTestData")
	void testCalculateAdvanced(Double value, Double result, char action) throws NumberNotInAreaException, NotSupportedOperationException {
		calculatorAdvanced.setCurrentValue(value);
		calculatorAdvanced.calculateAdvanced(action);
		assertThat(result, is(calculatorAdvanced.getCurrentValue()));
	}
	
	/**
	 * Provides a stream of test data for testing when action is valid.
	 * 
	 * @return A stream of object arrays.
	 */
	private static Stream<Arguments> provideCalculateAdvancedTestData() {
		return Stream.of(
				Arguments.of(2.0, 2.0, '!'),
				Arguments.of(2.0, 4.0, '2'),
				Arguments.of(2.0, 8.0, '3'),
				Arguments.of(0.0, 1.0, '!'),  
				Arguments.of(10.0, 3628800.0, '!'),
				Arguments.of(0.0, 0.0,'5'),
				Arguments.of(0.0, 1.0, '0'),
				Arguments.of(0.0, 0.0, '9'),
				Arguments.of(5.0, 1.0, '0'),
				Arguments.of(1.0, 1.0, '9'),
				Arguments.of(5.0, 120.0, '!'),
				Arguments.of(0.1, 1.0, '!'),
				Arguments.of(9.9, 362880.0, '!'),
				Arguments.of(10000.0, 100000000.0, '2'),
		        Arguments.of(0.0005, 0.00000025, '2'), 
		        Arguments.of(0.0001, 1.0, '!') ,
		        Arguments.of(0.0001, 0.00000001, '2'),
		        Arguments.of(Double.MIN_VALUE, 0.0, '2'),              
		        Arguments.of(-0.0, 1.0, '!'),                         
		        Arguments.of(0.999999, 1.0, '!')          

		);
	}
	
	/**
	 * Tests the {@link CalculatorAdvanced#calculateAdvanced(char)} method to ensure that
     * it throws a {@link NumberNotInAreaException} when a number is outside the allowed area for calculation.
     * 
	 * @param value The input value to be set in the calculator for the operation.
	 * @param action The arithmetic operation to be performed.
	 * @param message The expected exception message when number isn't in area.
	 */
	@ParameterizedTest
	@MethodSource("provideNumberNotInAreaTestData") 
	void testNumberNotInAreaException(Double value, char action, String message) {
		calculatorAdvanced.setCurrentValue(value);
		NumberNotInAreaException exception = assertThrows(NumberNotInAreaException.class,
				() -> calculatorAdvanced.calculateAdvanced(action));
	    assertThat("Messages should match", exception.getMessage(), is(message));
	}
	
	/**
	 * Provides a stream of test data for testing when number ins't in area.
	 * 
	 * @return A stream of object arrays.
	 */
	private static Stream<Arguments> provideNumberNotInAreaTestData() {
		return Stream.of(
				Arguments.of(-5.0, '!', "Number not in area"),
				Arguments.of(-1.0, '!', "Number not in area"),
				Arguments.of(11.0, '!', "Number not in area"),
				Arguments.of(15.0, '!', "Number not in area"),
				Arguments.of(20.0, '!', "Number not in area"),
				Arguments.of(-0.05, '!', "Number not in area"),
				Arguments.of(10.05, '!', "Number not in area"),
				Arguments.of(-0.001, '!', "Number not in area"),
				Arguments.of(10.001, '!', "Number not in area"),
			    Arguments.of(Double.MAX_VALUE, '!', "Number not in area"),
			    Arguments.of(-Double.MAX_VALUE, '!', "Number not in area"),
			    Arguments.of(-Double.MIN_VALUE, '!', "Number not in area")
	    );
	}
	
	/**
	 * Tests the {@link CalculatorAdvanced#calculateAdvanced(char)} method to ensure that
     * it throws a {@link IllegalArgumentException} when a number is less than zero.
     * 
	 * @param value The input value to be set in the calculator for the operation.
	 * @param action The arithmetic operation to be performed.
	 * @param message The expected exception message when a illegal argument exception occurs.
	 */
	@ParameterizedTest
	@MethodSource("provideIllegalArgumentsTestData")
	void testIllegalArgumentsException(Double value, char action, String message) {
		calculatorAdvanced.setCurrentValue(value);
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> calculatorAdvanced.calculateAdvanced(action));
		assertThat("Messages should match", exception.getMessage(), is(message));
	}
	
	/**
	 * Provides a stream of test data for testing when number is less than zero.
	 * 
	 * @return A stream of object arrays.
	 */
	private static Stream<Arguments> provideIllegalArgumentsTestData() {
		return Stream.of(
				Arguments.of(-10.0, '0', "Negative number"),
				Arguments.of(-5.0, '1', "Negative number"),
				Arguments.of(-1.0, '9', "Negative number"),
				Arguments.of(-0.001, '0', "Negative number"),
				Arguments.of(-15.003, '8', "Negative number"),
				Arguments.of(-Double.MAX_VALUE, '0', "Negative number"),
			    Arguments.of(-Double.MIN_VALUE, '1', "Negative number"),
			    Arguments.of(-0.000001, '9', "Negative number"),
			    Arguments.of(-999999.0, '8', "Negative number")
		);
	}
	
	/**
	 * Tests the method hasCharacteristic using parameterized test data.
     * This method uses various sets of input data provided by the {@link #provideNotSupportedOperationTestData()} method
     * to verify that the method correctly calculates when action isn't valid.
     * 
	 * @param action The action for method calculateAdvanced.
	 * @param message The expected exception message when a not supported operation occurs.
	 */
	@ParameterizedTest
	@MethodSource("provideNotSupportedOperationTestData")
	void testNotSupportedOperationsException(char action, String message) {
		NotSupportedOperationException exception = assertThrows(NotSupportedOperationException.class,
				() -> calculatorAdvanced.calculateAdvanced(action));
		assertThat("Messages should match", exception.getMessage(), is(message));
	}
	
	

	/**
	 * Provides a stream of test data for testing not supported operation scenarios.
	 * 
	 * @return A stream of object arrays.
	 */
	private static Stream<Arguments> provideNotSupportedOperationTestData() {
		return Stream.of(
				Arguments.of(',', "Action not supported"),
				Arguments.of('.', "Action not supported"),
				Arguments.of('?', "Action not supported"),
				Arguments.of('a', "Action not supported"),
				Arguments.of('%', "Action not supported"),
	            Arguments.of('@', "Action not supported"),
	            Arguments.of('^', "Action not supported"),
	            Arguments.of(';', "Action not supported"),
	            Arguments.of('[', "Action not supported"),
	            Arguments.of(']', "Action not supported"),
	            Arguments.of('$', "Action not supported"),
	            Arguments.of('#', "Action not supported"),
	            Arguments.of('&', "Action not supported"), 
	            Arguments.of('|', "Action not supported"), 
	            Arguments.of('~', "Action not supported"), 
	            Arguments.of('`', "Action not supported")
		);
	}
	
	/**
	 * Tests the method hasCharacteristic using parameterized test data.
     * This method uses various sets of input data provided by the {@link #provideHasCharacteristicTestData()} method
     * to verify that the method correctly calculates the expected result based on a given operator
     * and value.
     * 
	 * @param startValue The start value for currentValue.
	 * @param result Expected result.
	 * @param value The value for function hasCharacteristic.
	 * @throws NotSupportedOperationException
	 * @throws NumberNotInAreaException
	 */
	@ParameterizedTest
	@MethodSource("provideHasCharacteristicTestData")
	void testHasCharacteristic(Double startValue, Boolean result, char value) throws NotSupportedOperationException, NumberNotInAreaException {
		calculatorAdvanced.setCurrentValue(startValue);
		assertThat(result, is(calculatorAdvanced.hasCharacteristic(value)));
		
	}
	
	/**
	 * Provides a stream of test data for testing method hasCharacteristic.
	 * 
	 * @return A stream of object arrays.
	 */
	private static Stream<Arguments> provideHasCharacteristicTestData() {
		return Stream.of(
				Arguments.of(153.0, true, 'A'),
				Arguments.of(1634.0, true, 'A'),
				Arguments.of(200.0, false, 'A'),
				Arguments.of(450.0, false, 'A'),
				Arguments.of(6.0, true, 'P'),
				Arguments.of(28.0, true, 'P'),
				Arguments.of(50.0, false, 'P'),
				Arguments.of(21.0, false, 'P'),
				Arguments.of(1.0, false, 'P'),
				Arguments.of(370.0, true,'A'),
				Arguments.of(496.0, true, 'P'),
				Arguments.of(9474.0, true, 'A'),    
			    Arguments.of(8128.0, true, 'P'),   
			    Arguments.of(2.0, false, 'P')
	    );
	}
	
	/**
	 * Tests the method hasCharacteristic using parameterized test data.
     * This method uses various sets of input data provided by the {@link #provideNotSupportedOperationExceptionData()} method
     * to verify that the method correctly calculates when action isn't valid.
     * 
	 * @param startValue The start value for currentValue.
	 * @param value The value for function hasCharacteristic.
	 * @param message The expected exception message when a not supported operation occurs.
	 */
	@ParameterizedTest
	@MethodSource("provideNotSupportedOperationExceptionData")
	void testNotSupportedOperationExceptionData(Double startValue, char value, String message) {
		calculatorAdvanced.setCurrentValue(startValue);
		NotSupportedOperationException exception = assertThrows(NotSupportedOperationException.class,
				() -> calculatorAdvanced.hasCharacteristic(value));
		assertThat("Messages should match", exception.getMessage(), is(message));
	}
	
	/**
	 * Provides a stream of test data for testing NotSupportedOperationException.
	 * 
	 * @return A stream of object arrays.
	 */
	private static Stream<Arguments> provideNotSupportedOperationExceptionData() {
		return Stream.of(
				Arguments.of(1.0, 'B', "Action not supported"),
				Arguments.of(5.0, 'b', "Action not supported"),
				Arguments.of(124.5, '4', "Action not supported"),
				Arguments.of(13.6, '9', "Action not supported"),
				Arguments.of(1.05, '!', "Action not supported"),
				Arguments.of(100.0001, '#', "Action not supported")
		);
	}
	
	/**
	 * Tests the method hasCharacteristic using parameterized test data.
     * This method uses various sets of input data provided by the {@link #provideNumberNotInAreaData()} method
     * to verify that the method correctly calculates when number not in area.
     * 
	 * @param startValue The start value for currentValue.
	 * @param value The value for function hasCharacteristic.
	 * @param message The expected exception message when number not in area occurs.
	 */
	@ParameterizedTest
	@MethodSource("provideNumberNotInAreaData")
	void testNumberNotInAreaData(Double startValue, char value, String message) {
		calculatorAdvanced.setCurrentValue(startValue);
		NumberNotInAreaException exception = assertThrows(NumberNotInAreaException.class,
				() -> calculatorAdvanced.hasCharacteristic(value));
	    assertThat("Messages should match", exception.getMessage(), is(message));
	}
	
	 /**
	  * Provides a stream of test data for testing NumberNotInAreaException.
	  * 
	  * @return A stream of object arrays.
	  */
	private static Stream<Arguments> provideNumberNotInAreaData() {
		return Stream.of(
				Arguments.of(0.5, '5', "Number not in area"),
				Arguments.of(-1.0, '0', "Number not in area"),
				Arguments.of(-5.0, 'A', "Number not in area"),
				Arguments.of(0.999, '!', "Number not in area"),
				Arguments.of(0.0, '%', "Number not in area"),
				Arguments.of(-Double.MAX_VALUE, 'A', "Number not in area"),
			    Arguments.of(0.0, 'P', "Number not in area"),
			    Arguments.of(-1.0, 'P', "Number not in area")
		);
	}

}
