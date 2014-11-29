package xml;

import static org.junit.Assert.*;

import java.util.List;

import json.JSONParser;

import org.junit.Test;

import component.Node;

public class TestXMLWriter {

	@Test
	public void test_1() {
		JSONParser parserA = new JSONParser("{\"id\":19}");
		Node rootA = parserA.parse();
		NodToXMLA writerA = new NodToXMLA();
		List<String> resultant = writerA.writeXML(rootA);
		String resultantStr = "";
		for (String s : resultant) {
			resultantStr += s;
		}
		assertEquals(resultantStr, "<root><id>19</id></root>");
	}
	
	@Test 
	public void test_2() {
		JSONParser parser = new JSONParser(
				"{\"id\":19,\"home\":\"fanling\",\"wife\":{\"name\":\"hehe\",\"phonenumber\":\"61556960\"}");
		Node root = parser.parse();
		NodToXMLA writerB = new NodToXMLA();
		List<String> resultant = writerB.writeXML(root);
		String resultantStr = "";
		for (String s : resultant) {
			resultantStr += s;
		}
		assertEquals(resultantStr, "<root><id>19</id><home>fanling</home><wife><name>hehe</name>"
								 + "<phonenumber>61556960</phonenumber></wife></root>");
	}

}
