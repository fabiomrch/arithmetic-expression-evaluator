package com.arithmeticexpeval.exception;

/**
 * Thrown when a string representing the expression contains some unallowed characters
 * @author fab
 *
 */
public class InvalidArithmeticExpressionException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidArithmeticExpressionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
