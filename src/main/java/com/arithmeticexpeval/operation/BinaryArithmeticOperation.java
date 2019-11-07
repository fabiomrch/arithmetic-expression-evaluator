package com.arithmeticexpeval.operation;

/**
 * Every binary arithmetic operation should override the calculate method of this interface
 * Simplification: operations with integers only are allowed
 * @author fab
 *
 */
public interface BinaryArithmeticOperation {
	int calculate(int a, int b);
}
