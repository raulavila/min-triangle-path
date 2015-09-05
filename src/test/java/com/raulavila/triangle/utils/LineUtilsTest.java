package com.raulavila.triangle.utils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;

import org.junit.Test;

import com.raulavila.triangle.exceptions.IncorrectNumberOfElements;
import com.raulavila.triangle.exceptions.InvalidNumberException;
import com.raulavila.triangle.exceptions.NegativeNumberException;

public class LineUtilsTest {

	@Test
	public void testEmptyLine() {
		String line = "";
		
		assertTrue("Line should be empty", LineUtils.emptyLine(line));
	}
	
	@Test
	public void testFullLine() {
		String line = "Full line";
		
		assertFalse("Line should contain characters", LineUtils.emptyLine(line));
	}
	
	@Test
	public void testSplitCorrectLines() throws Exception{
		
		String line ="10";
		
		assertTrue("Line with one number should be parsed to a one elmement list", LineUtils.splitLine(line, 1).equals(Arrays.asList(10L)));
		
		line ="10 20 30";
		
		assertTrue("Line with one number should be parsed to a three elmement list", LineUtils.splitLine(line, 3).equals(Arrays.asList(10L, 20L, 30L)));
	}
	
	@Test(expected = NegativeNumberException.class)
	public void testSplitLineWithNegativeNumber() throws Exception {
		String line ="10 -20 30";
		
		LineUtils.splitLine(line, 3);
	}
	
	
	@Test(expected = InvalidNumberException.class)
	public void testSplitLineWithInvalidNumber() throws Exception {
		String line ="10 20b 30";
		
		LineUtils.splitLine(line, 3);
	}
	
	
	@Test(expected = IncorrectNumberOfElements.class)
	public void testSplitLineWithIncorrectNumberOfElements() throws Exception {
		String line ="10 20 30 50";
		
		LineUtils.splitLine(line, 3);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSplitInvalidCallNullLine() throws Exception {
		LineUtils.splitLine(null, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSplitInvalidCallEmptyLine() throws Exception {
		LineUtils.splitLine("", 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSplitInvalidCallNegativeNumberOfTokens() throws Exception {
		LineUtils.splitLine("20 30 40", -1);
	}
	
}
