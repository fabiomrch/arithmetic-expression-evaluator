package com.arithmeticexpeval.operation;

/**
 * Subtraction is a prioritized operation that implements its own binary arithmetic calculation
 * @author fab
 *
 */
public final class Subtraction extends PrioritizedArithmeticOperation implements BinaryArithmeticOperation {
	private static final char SYMBOL = '-';
	private static final int PRIORITY = 2;
	
	public Subtraction() {
		super(SYMBOL, PRIORITY);
	}

	@Override
	public int calculate(int a, int b) {
		return a - b;
	}
}
