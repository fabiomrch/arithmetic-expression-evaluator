package com.arithmeticexpeval.operation;

/**
 * Addition is a prioritized operation that implements its own binary arithmetic calculation
 * @author fab
 *
 */
public final class Addition extends PrioritizedArithmeticOperation implements BinaryArithmeticOperation {
	private static final char SYMBOL = '+';
	private static final int PRIORITY = 2;
	
	public Addition() {
		super(SYMBOL, PRIORITY);
	}

	@Override
	public int calculate(int a, int b) {
		return a + b;
	}
}
