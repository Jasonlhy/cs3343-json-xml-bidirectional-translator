package xml.testcase;

import static org.junit.Assert.*;
import xml.XMLParser;
import component.Node;

import org.junit.Test;

public class TestXmlToNode {

	@Test
	public void test() throws Exception {
		String testString = "<A><B>abc</B><C D=\"1\" E=\"2\" /></A>";
		XMLParser testTranslator = new XMLParser();
		Node testNode = testTranslator.Translate(testString);
		Node answerANode = new Node("A");
		Node answerBNode = new Node("B");
		answerBNode.setContent("abc");
		Node answerCNode = new Node("C");
		Node answerDNode = new Node("D");
		Node answerENode = new Node("E");
		answerDNode.setContent("1");
		answerENode.setContent("2");
		answerANode.addNode(answerBNode);
		answerANode.addNode(answerCNode);
		answerCNode.addNode(answerDNode);
		answerCNode.addNode(answerENode);
		assertEquals(answerANode, testNode);
	}
	
	@Test
	public void fullTest() throws Exception {
		String testString = "<A><B><D>Hello</D><H>Thomas</H></B><C>World</C><E F=\"Tommy\" G=\"Ken\" /></A>";
		Node answerANode = new Node("A");
		Node answerBNode = new Node("B");
		Node answerCNode = new Node("C", "World");
		Node answerDNode = new Node("D", "Hello");
		Node answerENode = new Node("E");
		Node answerFNode = new Node("F", "Tommy");
		Node answerGNode = new Node("G", "Ken");
		Node answerHNode = new Node("H", "Thomas");
		answerANode.addNode(answerBNode);
		answerANode.addNode(answerCNode);
		answerANode.addNode(answerENode);
		answerBNode.addNode(answerDNode);
		answerBNode.addNode(answerHNode);
		answerENode.addNode(answerFNode);
		answerENode.addNode(answerGNode);
		XMLParser test = new XMLParser();
		assertEquals(answerANode, test.Translate(testString));
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
	
	@Test
	public void TestWrongXml() throws Exception
	{
		String testString = "Error</Error>";
		XMLParser test = new XMLParser();
		//Exception e = new Exception("Wrong Format");
		assertEquals(null, test.Translate(testString));
		
	}
	
	@Test
	public void TestWrongXml2() throws Exception
	{
		String testString = "<A>B</C>";
		XMLParser test = new XMLParser();
		assertEquals(null, test.Translate(testString));
	}
	
	@Test
	public void TestWrongXml3() throws Exception
	{
		String testString = "</S>";
		XMLParser test = new XMLParser();
		assertEquals(null, test.Translate(testString));
	}
	
	

}
