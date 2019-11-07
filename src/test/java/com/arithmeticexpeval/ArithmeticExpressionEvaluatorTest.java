package com.arithmeticexpeval;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.arithmeticexpeval.evaluation.ArithmeticExpressionStringEvaluator;
import com.arithmeticexpeval.exception.InvalidArithmeticExpressionException;
import com.arithmeticexpeval.operation.Addition;
import com.arithmeticexpeval.operation.Division;
import com.arithmeticexpeval.operation.Multiplication;
import com.arithmeticexpeval.operation.PrioritizedArithmeticOperation;
import com.arithmeticexpeval.operation.Subtraction;

public class ArithmeticExpressionEvaluatorTest {
	private Set<PrioritizedArithmeticOperation> supportedOperations;
	private ArithmeticExpressionStringEvaluator evaluator;
	
	@BeforeEach
	public void setup() {
		supportedOperations = new HashSet<PrioritizedArithmeticOperation>();
		supportedOperations.add(new Addition());
		supportedOperations.add(new Subtraction());
		supportedOperations.add(new Multiplication());
		supportedOperations.add(new Division());
		
		evaluator = new ArithmeticExpressionStringEvaluator(supportedOperations);
	}
	
	@Test
	public void checkResult() throws InvalidArithmeticExpressionException {
		assertTrue(evaluator.evaluate("(1+2)*(3+(4*5))") == 69);
		assertTrue(evaluator.evaluate("10*(1+2)*(3+(4*5))+1") == 691);
		assertTrue(evaluator.evaluate("(1+2)*(3+(4*5))+(10/2)") == 74);
		assertTrue(evaluator.evaluate("(1+2)*(3+(4*5))*(5-5)") == 0);
		assertTrue(evaluator.evaluate("0") == 0);
	}
	
	@Test
	public void checkError() {
		assertThrows(InvalidArithmeticExpressionException.class, () -> { evaluator.evaluate("(1+X)*(3+(4*5))"); });
	}

}