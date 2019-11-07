package com.arithmeticexpeval.operation;

/**
 * This class represents arithmetic operations that have to respect a strict order: 
 * (e.g. multiplication and division have to be executed before addition and subtraction).
 * Priority attribute expresses the priority level (the lower it is the higher priority the operation has).
 * Simplification: level 1 for multiplication and division, level 2 for addition and subtraction, there isn't any level intended for 
 * exponentiation and root extraction
 * 
 * @author fab
 *
 */
public abstract class PrioritizedArithmeticOperation implements BinaryArithmeticOperation, Comparable<PrioritizedArithmeticOperation> {
	protected char symbol;
	protected int priority;
	
	public PrioritizedArithmeticOperation(char symbol, int priority) {
		super();
		this.symbol = symbol;
		this.priority = priority;
	}

	public char getSymbol() {
		return symbol;
	}
	
	@Override
	public int compareTo(PrioritizedArithmeticOperation o) {
		return new Integer(o.priority).compareTo(priority);
	}

}
