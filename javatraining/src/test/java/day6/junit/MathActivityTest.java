package day6.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import day6.junit.MathActivity;

class MathActivityTest {

	/**
	 * Test add function
	 */
	@Test
	void exec001() {
		assertEquals(5, MathActivity.add(3, 2));
	}
	
	/**
	 * Test subtract function
	 */
	@Test
	void exec002() {
		assertEquals(1, MathActivity.subtract(3, 2));
	}
	
	/**
	 * Test multiply function
	 */
	@Test
	void exec003() {
		assertEquals(6, MathActivity.multiply(3, 2));
	}
	
	/**
	 * Test divide function when divisor is valid
	 */
	@Test
	void exec004() {
		assertEquals(2, MathActivity.divide(4, 2));
	}
	
	/**
	 * Divide function should throw arithmetic exception when divisor is 0
	 */
	@Test
	void exec005() {
		Executable executable = () -> MathActivity.divide(100, 0);
		assertThrows(ArithmeticException.class, executable);
	}

}
