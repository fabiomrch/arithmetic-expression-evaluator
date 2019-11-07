package com.arithmeticexpeval.operation;

/**
 * Multiplication is a prioritized operation that implements its own binary arithmetic calculation
 * @author fab
 *
 */
public final class Multiplication extends PrioritizedArithmeticOperation implements BinaryArithmeticOperation {
	private static final char SYMBOL = '*';
	private static final int PRIORITY = 1;
	
	public Multiplication() {
		super(SYMBOL, PRIORITY);
	}
	
	@Override
	public int calculate(int a, int b) {
		return a * b;
	}

}
