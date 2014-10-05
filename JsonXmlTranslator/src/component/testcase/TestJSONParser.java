package component.testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import component.JSONParser;
import component.Node;

public class TestJSONParser {

	@Test
	public void testSimpleParse1NoSpace() {
		String expected = "base: { id : 19 }";
		JSONParser parser = new JSONParser("{\"id\":19}");
		Node root = parser.parse();
		assertEquals(expected, root.toString());
	}

	@Test
	public void testSimpleParse2NoSpace() {
		String expected = "base: { id : 19, home : \"fanling\" }";
		JSONParser parser = new JSONParser("{\"id\":19,\"home\":\"fanling\"}");
		Node root = parser.parse();
		assertEquals(expected, root.toString());
	}

	@Test
	public void testSimpleJSONObjectNoSpace() {
		// TODO failed right now
		String expected = "base: { id : 19, home : \"fanling\", wife: { name : \"hehe\", phonenumber : \"61556960\" } }";
		JSONParser parser = new JSONParser(
				"{\"id\":19,\"home\":\"fanling\",\"wife\":{\"name\":\"hehe\",\"phonenumber\":\"61556960\"}");
		Node root = parser.parse();
		assertEquals(expected, root.toString());
	}
	
	@Test
	public void testSimpleJSONArrayNoSpace() {
//		 {  
//		   "id":19,
//		   "home":"fanling",
//		   "wife":[  
//		      {  
//		         "name":"hehe",
//		         "phonenumber":"61556960"
//		      },
//		      {  
//		         "name":"ricky",
//		         "phonenumber":"99999"
//		      }
//		   ]
//		}
		 
		// TODO failed right now
		String json = "{\"id\":19,\"home\":\"fanling\",\"wife\":[{\"name\":\"hehe\",\"phonenumber\":\"61556960\"},{\"name\":\"ricky\",\"phonenumber\":\"99999\"}]}";
		String expected = "base: { id : 19, home : \"fanling\", wife: { name : \"hehe\", phonenumber : \"61556960\" }, wife: { name : \"ricky\", phonenumber : \"99999\" } }";
		JSONParser parser = new JSONParser(json);
		System.out.println(json);
		Node root = parser.parse();
		assertEquals(expected, root.toString());
	}
}
