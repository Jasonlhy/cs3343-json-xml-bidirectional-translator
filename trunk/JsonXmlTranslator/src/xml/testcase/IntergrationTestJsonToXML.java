package xml.testcase;

import static org.junit.Assert.*;
import json.JSONParser;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import component.Node;

import xml.NodeToXMLB;

public class IntergrationTestJsonToXML extends TestCase{

	NodeToXMLB n = new NodeToXMLB();
	
	@Test
	public void test() {
		String testString = "{\"student\": \"\"}";
		JSONParser parser = new JSONParser(testString);
		Node root = parser.parse();
		assertEquals("<root><student></student></root>", n.outputXMLFile(root));
	}

	@Test
	public void test2() {
		String testString = "{\"student\": {\"a\": {\"name\": \"Tommy\"}}}";
		JSONParser parser = new JSONParser(testString);
		Node root = parser.parse();
		assertEquals("<root><student><a><name>Tommy</name></a></student></root>", n.outputXMLFile(root));
	}
	
	@Test
	public void test3() {
		String testString = "{\"student\": {\"a\": {\"name\": \"Tommy\"},\"b\": {\"name\": \"Tummy}}}\n";
		JSONParser parser = new JSONParser(testString);
		Node root = parser.parse();
		assertEquals("<root><student><a><name>Tommy</name></a><b><name>Tummy</name></b></student></root>", n.outputXMLFile(root));
	}
	
	@Test
	public void test4() {
		String testString = "{\"student\": {\"name\": \"Tommy\"}}\n";
		JSONParser parser = new JSONParser(testString);
		Node root = parser.parse();
		assertEquals("<root><student><name>Tommy</name></student></root>", n.outputXMLFile(root));
	}
	
	@Test
	public void test5() {
		String testString = "{\"student\": {\"a\": {\"name\": \"Tommy\",\"weight\": \"200\"}}}\n";
		JSONParser parser = new JSONParser(testString);
		Node root = parser.parse();
		assertEquals("<root><student><a><name>Tommy</name><weight>200</weight></a></student></root>", n.outputXMLFile(root));
	}
}
