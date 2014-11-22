package xml.testcase;

import static org.junit.Assert.*;
import xml.XmlToNode;
import component.Node;

import org.junit.Test;

public class TestXmlToNode {

	@Test
	public void test() {
		String testString = "<A><B><D></D></B><C><l>y</l></C></A>";
		XmlToNode testTranslator = new XmlToNode();
		Node testNode = testTranslator.Translate(testString);
		Node answerANode = new Node("A");
		Node answerBNode = new Node("B");
		answerBNode.setContent("abc");
		answerANode.addNode(answerBNode);
		assertEquals(answerANode, testNode);
	}
	
	@Test
	public void testMostLeftisNode()
	{
		String testString = "<A>abc</A>";
		XmlToNode test = new XmlToNode();
		assertTrue(test.mostLeftisNode(testString));
	}
	@Test
	public void testFailMostLeftisNode()
	{
		String testString = "abc<A>";
		XmlToNode test = new XmlToNode();
		assertFalse(test.mostLeftisNode(testString));
	}
	@Test
	public void TestTrueMostLeftisNode()
	{
		String testString = "<A>abc</A>";
		XmlToNode test = new XmlToNode();
		assertTrue(test.mostLeftisNode(testString));
	}
	@Test
	public void TestGetNodeName()
	{
		String testString = "<A>";
		XmlToNode test = new XmlToNode();
		assertEquals("A", test.getNodename(testString));
	}
	
	@Test
	public void TesGetValue()
	{
		StringBuilder testString = new StringBuilder();
		testString.append("abc</A>");
		XmlToNode test= new XmlToNode();
		assertEquals("abc", test.getValue(testString));
	}
	
	@Test
	public void TestNodeStartAndEnd()
	{
		String testString = "<testing>testing1</testing>";
		XmlToNode test = new XmlToNode();
		int[] result = test.NodeStartAndEnd(testString);
		assertEquals(0, result[0]);
		assertEquals(9, result[1]);
	}

}
