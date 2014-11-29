package xml.testcase;

import static org.junit.Assert.*;
import json.JSONParser;
import junit.framework.TestCase;
import xml.NodeToXMLB;
import component.Node;

import org.junit.Before;
import org.junit.Test;

public class testNodeToXml extends TestCase{
	NodeToXMLB n = new NodeToXMLB();
	
	@Test
	public void test() {
		Node root = new Node("root");
		assertEquals("<root></root>", n.outputXMLFile(root));
	}
	
	@Test
	public void test2() {
		Node root = new Node("hi");
		root.setContent("auntie");
		assertEquals("<hi>auntie</hi>",n.outputXMLFile(root));
	}
	
	@Test
	public void test3() {
		Node root = new Node("hi");
		root.setContent("auntie");
		Node root2 = new Node("test");
		root2.addNode(root);
		assertEquals("<test><hi>auntie</hi></test>",n.outputXMLFile(root2));
	}

}
