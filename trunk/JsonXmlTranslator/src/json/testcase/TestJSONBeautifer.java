package json.testcase;

import static org.junit.Assert.assertEquals;
import json.JSONBeautifier;
import json.JSONParser;

import org.junit.Test;

import component.Node;

public class TestJSONBeautifer {
	@Test
	public void testSimpleJSONString1() {
		JSONBeautifier b = new JSONBeautifier();
		String input = "{ id : 19 }";
		String expected = "{\n id : 19\n}";
		String result = null;
		result = b.beautifier(input, false);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testSimpleJSONStringWithEndLine() {
		JSONBeautifier b = new JSONBeautifier();
		String input = "{   \nid : 19 }";
		String expected = "{\n id : 19\n}";
		String result = null;
		result = b.beautifier(input, false);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testSimpleJSONString2WithEndLine() {
		JSONBeautifier b = new JSONBeautifier();
		String input = "{ id : 19, home : fanling, wife: { name : hehe, phonenumber : 61556960 }, uni : city }";
		String expected = "{\n id : 19,\n home : fanling,\n wife:{\n  name : hehe,\n  phonenumber : 61556960\n },\n uni : city\n}";
		String result = null;
		result = b.beautifier(input, false);
		//System.out.println("wife: {".trim());
		assertEquals(expected, result);
	}
}
