package xml.testcase;

import static org.junit.Assert.*;

import java.util.List;

import json.JSONParser;

import org.junit.Test;

import xml.NodeToXMLA;
import component.Node;

public class TestXMLWriter {

	@Test
	public void test_1() {
		JSONParser parserA = new JSONParser("{\"id\":19}");
		Node rootA = parserA.parse();
		NodeToXMLA writerA = new NodeToXMLA();
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
		NodeToXMLA writerB = new NodeToXMLA();
		List<String> resultant = writerB.writeXML(root);
		String resultantStr = "";
		for (String s : resultant) {
			resultantStr += s;
		}
		assertEquals(resultantStr, "<root><id>19</id><home>fanling</home><wife><name>hehe</name>"
								 + "<phonenumber>61556960</phonenumber></wife></root>");
	}

}
