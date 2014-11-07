package xml.testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import xml.TAG;

public class TestTAG {

	@Test
	public void testValidOpenTag() {
		String test = "<open>";
		boolean result = TAG.OPEN.isValid(test);
		assertTrue(result);
	}
	
	@Test
	public void testInvalidOpenTag() {
		String test = "blablabla";
		boolean result = TAG.OPEN.isValid(test);
		assertFalse(result);
	}
	
	@Test
	public void testValidCloseTag() {
		String test = "</close>";
		boolean result = TAG.CLOSE.isValid(test);
		assertTrue(result);
	}
	
	@Test
	public void testInvalidCloseTag() {
		String test = "blablabla";
		boolean result = TAG.CLOSE.isValid(test);
		assertFalse(result);
	}
	
	@Test
	public void testValidShortTag() {
		String test = "<short/>";
		boolean result = TAG.SHORT.isValid(test);
		assertTrue(result);
	}
	
	@Test
	public void testInvalidShortTag() {
		String test = "blablabla";
		boolean result = TAG.SHORT.isValid(test);
		assertFalse(result);
	}
	
	@Test
	public void testContinuousTags(){
		String test = "<tag1><tag2><tag3>";
		boolean result = TAG.OPEN.isValid(test);
		assertTrue(result);
	}
	
	@Test
	public void testTagInsideTag(){
		String test = "<tag1<tag2>>";
		boolean result = TAG.OPEN.isValid(test);
		assertTrue(result);
	}

}
