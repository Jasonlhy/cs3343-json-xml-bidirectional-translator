package xml.testcase;

import static org.junit.Assert.*;
import xml.XMLParser;
import component.Node;

import org.junit.Test;

public class TestXmlToNode {

	@Test
	public void test() {
		String testString = "<A><B>abc</B></A>";
		XMLParser testTranslator = new XMLParser();
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
		XMLParser test = new XMLParser();
		assertTrue(test.mostLeftisNode(testString));
	}
	@Test
	public void testFailMostLeftisNode()
	{
		String testString = "abc<A>";
		XMLParser test = new XMLParser();
		assertFalse(test.mostLeftisNode(testString));
	}
	@Test
	public void TestTrueMostLeftisNode()
	{
		String testString = "<A>abc</A>";
		XMLParser test = new XMLParser();
		assertTrue(test.mostLeftisNode(testString));
	}
	@Test
	public void TestGetNodeName()
	{
		String testString = "<A>";
		XMLParser test = new XMLParser();
		assertEquals("A", test.getNodename(testString));
	}
	
	@Test
	public void TesGetValue()
	{
		StringBuilder testString = new StringBuilder();
		testString.append("abc</A>");
		XMLParser test= new XMLParser();
		assertEquals("abc", test.getValue(testString));
	}
	
	@Test
	public void TestNodeStartAndEnd()
	{
		String testString = "<testing>testing1</testing>";
		XMLParser test = new XMLParser();
		int[] result = test.NodeStartAndEnd(testString);
		assertEquals(0, result[0]);
		assertEquals(9, result[1]);
	}

}
