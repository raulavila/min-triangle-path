package com.raulavila.triangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.raulavila.triangle.exceptions.IncorrectNumberOfElements;
import com.raulavila.triangle.exceptions.InvalidNumberException;
import com.raulavila.triangle.exceptions.NegativeNumberException;
import com.raulavila.triangle.utils.LineUtils;
import com.raulavila.triangle.utils.TriangleBuilder;
import com.raulavila.triangle.utils.TrianglePathFinder;

public class MinTrianglePath {
	
	public static void main (String [] args) {
		
		List<String> lines = readLinesFromStandardInput();

		if(lines.isEmpty()) {
			System.out.println("No input received!");
			System.exit(1);
		}
		
		String line;
		
		line = lines.get(0);
		
		List<Long> numbers;  
		numbers = checkAndParseNumberList(line, 1); 
		
		TriangleBuilder builder = TriangleBuilder.newInstance(numbers.get(0));
		
		int lineNumber = 2;
		for(Iterator<String> it = lines.listIterator(1); it.hasNext();lineNumber++) { //start with second line  
			
			line = it.next();
			
			numbers = checkAndParseNumberList(line, (lineNumber));   //we expect as many elements as lineNumber
			builder.addLevel(numbers);
		}		
		
		TrianglePathFinder pathFinder = TrianglePathFinder.newInstance(builder.getTriangleRoot());
		
		List<Long> minimalPath = pathFinder.getMinimalPath();
		Long minimalSum = pathFinder.getMinimalSum();
		
		
		System.out.println(String.format("%s = %d", 
										getSumExpression(minimalPath),
										minimalSum));
	}
	
	private static List<String> readLinesFromStandardInput() {
		List<String> lines = new ArrayList<String>();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String inputLine;
	 
			int lineNumber = 1;
			while((inputLine=br.readLine())!=null) {
				if(LineUtils.emptyLine(inputLine)) {
					System.out.println(String.format("Line %d is empty!", lineNumber));
					System.exit(1);
				}
				
				lines.add(inputLine);
				lineNumber++;
			}
			
			return lines;
		}
		catch(IOException io){
			System.out.println(String.format("Unexpected error reading from standard input: %s", io.getMessage()));
			System.exit(1);
			return null;  //never reached
		}	
	}
	
	private static List<Long> checkAndParseNumberList(String line, int lineNumber) {				
		try {
			List<Long> numbers;
			numbers = LineUtils.splitLine(line, lineNumber);
			return numbers;
		}
		catch(InvalidNumberException | NegativeNumberException |  IncorrectNumberOfElements e) {
			System.out.println(String.format("Error in line %d: %s", lineNumber, e.getMessage()));
			System.exit(1);
			return null;   //never reached
		}
	}
	
	private static String getSumExpression(List<Long> nodeList) {
		
		StringBuilder builder = new StringBuilder();
		
		for(Long value: nodeList) {
			builder.append(Long.valueOf(value));
			builder.append(" + ");
		}
		
		builder.delete(builder.length() - 3 , builder.length());
		
		return builder.toString();
	}	
	
	
	
	
}
