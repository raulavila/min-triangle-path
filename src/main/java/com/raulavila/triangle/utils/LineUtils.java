package com.raulavila.triangle.utils;

import com.raulavila.triangle.exceptions.IncorrectNumberOfElements;
import com.raulavila.triangle.exceptions.InvalidNumberException;
import com.raulavila.triangle.exceptions.NegativeNumberException;

import java.util.ArrayList;
import java.util.List;

public class LineUtils {

    public static boolean emptyLine(String line) {
        return line == null || line.trim().length() == 0;
    }

    public static List<Long> splitLine(String line, int expectedTokens)
                                                    throws InvalidNumberException,
                                                    IncorrectNumberOfElements,
                                                    NegativeNumberException {
        
        if (line == null)
            throw new NullPointerException("line parameter can't be empty");

        if (line.isEmpty())
            throw new IllegalArgumentException("line must be a non-empty String");

        if (expectedTokens <= 0)
            throw new IllegalArgumentException("expectedTokens must be a positive number");


        String[] tokens = line.split("\\s{1,}");  //separation of any number of whitespaces between numbers

        List<Long> listNumbers = new ArrayList<Long>();

        int position = 0;

        for (String token : tokens) {
            position++;
            listNumbers.add(parseAndValidateNumber(token, position));
        }

        if (listNumbers.size() != expectedTokens)
            throw new IncorrectNumberOfElements(
                    String.format("Incorrect number of elements! Expected: %d", expectedTokens));

        return listNumbers;

    }

    private static long parseAndValidateNumber(String token, int position)
                                throws NegativeNumberException, InvalidNumberException {
        try {
            long number = Long.valueOf(token);

            if (number < 0)
                throw new NegativeNumberException(
                        String.format("The element in position #%d (%d) is a negative number",
                                position, number));

            return number;
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(
                    String.format("The element in position #%d (%s) is not valid",
                            position, token));
        }
    }


    private LineUtils() {
        throw new AssertionError();
    }
}
