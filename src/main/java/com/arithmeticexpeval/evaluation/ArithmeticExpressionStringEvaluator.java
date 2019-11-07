package com.arithmeticexpeval.evaluation;

import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.arithmeticexpeval.exception.InvalidArithmeticExpressionException;
import com.arithmeticexpeval.operation.Division;
import com.arithmeticexpeval.operation.PrioritizedArithmeticOperation;
import com.arithmeticexpeval.operation.Subtraction;

/**
 * Class that wraps the logic of arithmetic evaluation starting from a string that representing the expression
 * @author fab
 *
 */
public class ArithmeticExpressionStringEvaluator {
	private static final char OPEN_CIRCLE_PARENTHESIS = '(';
	private static final char CLOSED_CIRCLE_PARENTHESIS = ')';
	
	// Key: symbol (e.g. '+', '-', '*', '/'), value: prioritized arithmetic operation object
	Map<Character, PrioritizedArithmeticOperation> supportedOperations;
		
	/**
	 * 
	 * @param supportedOperations operation type set that can be performed is configurable when initializing the evaluator
	 */
	public ArithmeticExpressionStringEvaluator(Set<PrioritizedArithmeticOperation> supportedOperations) {
		super();
		this.supportedOperations = supportedOperations.stream().collect(Collectors.toMap(PrioritizedArithmeticOperation::getSymbol, Function.identity()));
	}

	/**
	 * Priority check
	 * @param op1 First operator
	 * @param op2 Second operator
	 * @return true if the first operations has an higher priority that the second one, respecting the following rules:
	 * - actual operations ('+', '-', '*', '/') before parenthesis
	 * - rely on comparable between two prioritized arithmetic operations 
	 */
	private boolean hasHigherPriority(char op1, char op2) {
		if(op1 == OPEN_CIRCLE_PARENTHESIS || op1 == CLOSED_CIRCLE_PARENTHESIS)
			return false;
		return 
			supportedOperations.get(op1).compareTo(supportedOperations.get(op2)) > 0;
	}
	
	/**
	 * Actual calculation based on operation's own implementation of calculate method
	 * @param op Operation type
	 * @param v1 Operand 1
	 * @param v2 Operand 2
	 * @param values Stack of values to evaluate that must be updated
	 */
	private void calculate(PrioritizedArithmeticOperation op, int v1, int v2, Stack<Integer> values) {
		if(op instanceof Subtraction || op instanceof Division)
			values.push(op.calculate(v2, v1));
		else
			values.push(op.calculate(v1, v2));
	}
	
	/**
	 * Evaluation logic
	 * @param exp String representing the expression
	 * @return Expression result
	 * @throws InvalidArithmeticExpressionException thrown when expression cannot be evaluated
	 */
	public int evaluate(String exp) throws InvalidArithmeticExpressionException {
		char[] tokens = exp.toCharArray(); 
		
		// List of operators that must be executed implemented as a stack in order to respect the evaluation order
    	Stack<Character> operators  = new Stack<Character>();
    	// List of values that act as operands implemented as a stack in order to respect the evaluation order
        Stack<Integer> values = new Stack<Integer>();

        // Iterate over each character of the expression string
        for (int i = 0; i < tokens.length; i++)
        {
        	// Current char
        	char token = tokens[i];
        	
        	// '(' encountered: treat it as an operator and push it to the stack 
        	if(token == OPEN_CIRCLE_PARENTHESIS)
        	{
        		operators.push(token);
        	}
        	// ')' encountered: an inner expression is complete and it can be evaluated
        	else if(token == CLOSED_CIRCLE_PARENTHESIS)
        	{
        		// Iterate over pushed operators until the respective open circle parenthesis is not found
        		while (operators.peek() != '(') 
        		{
        			// Execute inner operations and remove them from the stack in order that they won't be evaluated more than once
        			PrioritizedArithmeticOperation op = supportedOperations.get(operators.pop());
        			int val1 = values.pop();
        			int val2 = values.pop();
        			calculate(op, val1, val2, values);
        		}       
        		// Remove parenthesis from the stack in order that it won't be evaluated more than once
        		operators.pop();
        	}
        	// Supported operand encountered
        	else if(supportedOperations.keySet().contains(token))
        	{
        		// check whether there are operators with higher priority to evaluate before in the stack 
        		while(!operators.isEmpty() && hasHigherPriority(operators.peek(), token)) 
        		{
        			PrioritizedArithmeticOperation op = supportedOperations.get(operators.pop());
        			int val1 = values.pop();
        			int val2 = values.pop();
        			calculate(op, val1, val2, values);
        		}
        		
        		// Push the operator to the stack for evaluation
        		operators.push(token);
        	}
        	// Operand encountered: push to values stack
        	else if(Character.isDigit(token)) 
        	{
        		// Operand can be a number made up of more than one digit!
        		StringBuffer sbuf = new StringBuffer();
        		sbuf.append(token);
        		while (i + 1 < tokens.length && Character.isDigit(tokens[i + 1])) 
        		{
                    sbuf.append(tokens[i + 1]);
                    i++;
        		}
        		
                values.push(Integer.parseInt(sbuf.toString())); 
        	}
        	// Not allowed character encountered: throw exception
        	else
        		throw new InvalidArithmeticExpressionException(String.format("Invalid arithmetic expression: character '%s' not allowed", token));
        }
        
        // Process each operators remained in the stack that haven't had any chance to be evaluated instantly 
        while (!operators.empty()) 
        {
        	PrioritizedArithmeticOperation op = supportedOperations.get(operators.pop());
			int val1 = values.pop();
			int val2 = values.pop();
			calculate(op, val1, val2, values);
        }
            
        // Result is the last value to be popped from values stack
        return values.pop();
	}
}
