package json.testcase;

import static org.junit.Assert.*;
import json.JSONParseException;
import json.JSONParser;

import org.junit.Test;

import component.Node;

/**
 * Test JSONParser. Please avoid to FORMAT whole file, the JSON inside the
 * comments will look absolutely ugly and unreadable. Please select the thing
 * you want to format and click FORMAT in eclipse. (OR click FORMAT ELEMENT
 * which format the things around your cursor).
 * 
 * @author jason
 * @since 6-10-2014
 * 
 */
public class TestJSONParser {

	@Test
	public void testSimpleParse1NoSpace() {
		String expected = "root: { id : 19 }";
		JSONParser parser = new JSONParser("{\"id\":19}");
		Node root = parser.parse();
		assertEquals(expected, root.toString());
	}

	@Test
	public void testSimpleParse2NoSpace() {
		String expected = "root: { id : 19, home : fanling }";
		JSONParser parser = new JSONParser("{\"id\":19,\"home\":\"fanling\"}");
		Node root = parser.parse();

		assertEquals(expected, root.toString());
	}

	@Test
	public void testSimpleJSONObject1NoSpace() {
//		{  
//		   "id":19,
//		   "home":"fanling",
//		   "wife":{  
//		      "name":"hehe",
//		      "phonenumber":"61556960"
//		   }
//		}
		
		String expected = "root: { id : 19, home : fanling, wife: { name : hehe, phonenumber : 61556960 } }";
		JSONParser parser = new JSONParser(
				"{\"id\":19,\"home\":\"fanling\",\"wife\":{\"name\":\"hehe\",\"phonenumber\":\"61556960\"}}");
		Node root = parser.parse();
		assertEquals(expected, root.toString());
	}
	
	@Test
	public void testSimpleJSONObject2NoSpace() {
//		{  
//		   "id":19,
//		   "home":"fanling",
//		   "wife":{  
//		      "name":"hehe",
//		      "phonenumber":"61556960"
//		   },
//		   "uni":"city"
//		}
		
		String json ="{\"id\":19,\"home\":\"fanling\",\"wife\":{\"name\":\"hehe\",\"phonenumber\":\"61556960\"},\"uni\":\"city\"}"; 
		String expected = "root: { id : 19, home : fanling, wife: { name : hehe, phonenumber : 61556960 }, uni : city }";
		JSONParser parser = new JSONParser(
				json);
		Node root = parser.parse();
		assertEquals(expected, root.toString());
	}
	

	
	@Test
	public void testInvalidJSON() {
		String json = "";
		JSONParser parser = new JSONParser(json);
		try {
			parser.parse();
		} catch (JSONParseException ex){
			assertEquals("Empty String", ex.getMessage());
		}
	}
	
	@Test
	public void testMissingKey() {
		String json = "{id:12}";
		JSONParser parser = new JSONParser(json);
		try {
			parser.parse();
		} catch (JSONParseException ex){
			assertEquals("Expected key as string at 1", ex.getMessage());
		}
	}
	
	@Test
	public void testMissingOpenBracket() {
		String json = "\"id\":12}";
		JSONParser parser = new JSONParser(json);
		try {
			parser.parse();
		} catch (JSONParseException ex){
			assertEquals("Expected { at 0 but \" is found", ex.getMessage());
		}
	}
	
	@Test public void testJSONArraySimpleValue(){
		JSONParser parser = new JSONParser(" { \"jason\" : [2333,2234,122,true, {\"nestedKey\" : 22, \"nestedValue\" : 222 , \"doublenested\" : {\"omg\" : 23} }, [\"nested array value\", \"nest array value2\"]], \"age\" : \"it seems ok\"}");
		Node s= parser.parse();
		assertEquals("root: { jason: { null : 2333, null : 2234, null : 122, null : true, null: { nestedKey : 22, nestedValue : 222, doublenested: { omg : 23 } }, null: { null : nested array value, null : nest array value2 } }, age : it seems ok }", s.toString());
	}
}
