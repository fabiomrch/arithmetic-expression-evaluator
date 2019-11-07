package com.arithmeticexpeval.operation;

/**
 * Division is a prioritized operation that implements its own binary arithmetic calculation
 * @author fab
 *
 */
public final class Division extends PrioritizedArithmeticOperation implements BinaryArithmeticOperation {
	private static final char SYMBOL = '/';
	private static final int PRIORITY = 1;
	
	public Division() {
		super(SYMBOL, PRIORITY);
	}
	
	@Override
	public int calculate(int a, int b) {
		if(b == 0)
			throw new UnsupportedOperationException("Division by zero");
			
		return a / b;
	}
}
