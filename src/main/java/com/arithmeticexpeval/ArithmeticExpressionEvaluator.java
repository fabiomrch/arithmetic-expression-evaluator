package com.arithmeticexpeval;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.arithmeticexpeval.evaluation.ArithmeticExpressionStringEvaluator;
import com.arithmeticexpeval.operation.Addition;
import com.arithmeticexpeval.operation.Division;
import com.arithmeticexpeval.operation.Multiplication;
import com.arithmeticexpeval.operation.PrioritizedArithmeticOperation;
import com.arithmeticexpeval.operation.Subtraction;

public class ArithmeticExpressionEvaluator {

	public static void main(String[] args) {	
		Scanner scan = new Scanner(System.in);
		
		Set<PrioritizedArithmeticOperation> supportedOperations = new HashSet<PrioritizedArithmeticOperation>();
		supportedOperations.add(new Addition());
		supportedOperations.add(new Subtraction());
		supportedOperations.add(new Multiplication());
		supportedOperations.add(new Division());
		
		ArithmeticExpressionStringEvaluator evaluator = new ArithmeticExpressionStringEvaluator(supportedOperations);
		
		String quit = "";
		while(!quit.equals("q")) {
			System.out.print("Expression to evaluate: ");
			String exp = scan.next();
			try {
				System.out.println("Result: " + evaluator.evaluate(exp));
				System.out.print("Press 'q' to quit, anything else to continue: ");
				quit = scan.next();
			} catch(Exception e) {
				e.printStackTrace();
			} 
		}

		scan.close();
	}

}
