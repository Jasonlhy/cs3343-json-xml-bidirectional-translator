package component.testcase;

import static org.junit.Assert.*;
import org.junit.Test;
import component.*;
import java.util.*;

public class TestNode {
	
	Node n;
	
	@Test
	public void testGetTitle() {
		n = new Node ("hihi");
		assertEquals("hihi", n.getTitle());
	}
	
	@Test
	public void testGetContent() {
		n = new Node ("haha", "hehe");
		assertEquals("hehe", n.getContent());
	}	
	
	@Test
	public void testChildNodeANDHasChildNodeFunction() {
		n = new Node ("haha", "hehe");
		
		Node childNode1 = new Node("child1", "child1Content");
		assertFalse(n.hasChildNode());
		
		n.addNode(childNode1);
		assertTrue(n.hasChildNode());
		
	}
	
	@Test
	public void testChildNodeLength() {
		n = new Node ("haha", "hehe");
		assertEquals(0, n.getChildNodeLength());
		
		Node childNode1 = new Node("child1", "child1Content");
		n.addNode(childNode1);
		assertEquals(1, n.getChildNodeLength());
	}
	
	@Test
	public void testGetChildNode(){
		n = new Node ("haha", "hehe");
		assertNull(n.getChildNode(2));
		
		Node childNode1 = new Node("child1", "child1Content");
		n.addNode(childNode1);
		
		assertNull(n.getChildNode(2));
		
		Node firstChild = n.getChildNode(0);
		assertEquals("child1", firstChild.getTitle());
		assertEquals("child1Content", firstChild.getContent());
	}
	
	@Test
	public void testReplaceNodes(){
		List<Node> childList1 = new ArrayList<Node>();
		childList1.add(new Node("child1", "child1Content1"));
		childList1.add(new Node("child2", "child1Content2"));
		
		List<Node> childList2 = new ArrayList<Node>();
		childList2.add(new Node("child1", "child1Content1"));
		childList2.add(new Node("child2", "child1Content2"));
		childList2.add(new Node("child3", "child1Content3"));
		
		Node n = new Node("root", childList1);
		n.replaceNodes(childList2);
		assertEquals(3, n.getChildNodeLength());
	}
	
	@Test
	public void testChildNodeLengthANDreturnBackNode() {
		Node childNode1 = new Node("child1", "child1Content1");
		Node childNode2 = new Node("child2", "child1Content2");
		Node childNode3 = new Node("childa", "child1Content3");
		Node childNode4 = new Node("childa", "child1Content4");
		
		ArrayList<Node> list = new ArrayList<Node>();
		list.add(childNode1);
		list.add(childNode2);
		list.add(childNode3);
		list.add(childNode4);
		
		n = new Node("titleHAHA", list);
		
		// loop child
		assertEquals("child1Content1", n.getChildNode(0).getContent());
		assertEquals("child1Content2", n.getChildNode(1).getContent());
		assertEquals("child1Content3", n.getChildNode(2).getContent());
		assertEquals("child1Content4", n.getChildNode(3).getContent());
		
		// search childa
		Node[] listt = n.getChildNode("childa");
		assertEquals("child1Content3", listt[0].getContent());
		assertEquals("child1Content4", listt[1].getContent());
	}
	
	@Test
	public void testToString1() {
		Node root = new Node("root");
		root.addNode(new Node("id", "1"));
		
		assertEquals("root: { id : 1 }", root.toString());
	}
	
	public void testToString2() {
		Node root = new Node("root");
		root.addNode(new Node("id", "1"));
		root.addNode(new Node("name", "\"jason\""));
		
		assertEquals("root: { id : 1, name : \"jason\" }", root.toString());
	}

}
