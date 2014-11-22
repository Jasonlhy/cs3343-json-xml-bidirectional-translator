package component.testcase;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import component.JSONParser;
import component.Node;
import component.XMLWriter;

public class TestXMLWriter {

	@Test
	public void test_1() {
		JSONParser parserA = new JSONParser("{\"id\":19}");
		Node rootA = parserA.parse();
		XMLWriter writerA = new XMLWriter();
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
		XMLWriter writerB = new XMLWriter();
		List<String> resultant = writerB.writeXML(root);
		String resultantStr = "";
		for (String s : resultant) {
			resultantStr += s;
		}
		assertEquals(resultantStr, "<root><id>19</id><home>fanling</home><wife><name>hehe</name>"
								 + "<phonenumber>61556960</phonenumber></wife></root>");
	}

}
