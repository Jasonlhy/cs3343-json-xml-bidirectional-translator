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
		assertEquals(n.getTitle(), "hihi");
	}
	
	@Test
	public void testGetContent() {
		n = new Node ("haha", "hehe");
		assertEquals(n.getContent(), "hehe");
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
		assertEquals(n.getChildNodeLength(), 0);
		
		Node childNode1 = new Node("child1", "child1Content");
		n.addNode(childNode1);
		assertEquals(n.getChildNodeLength(), 1);
	}
	
	@Test
	public void testGetChildNode(){
		n = new Node ("haha", "hehe");
		assertNull(n.getChildNode(2));
		
		Node childNode1 = new Node("child1", "child1Content");
		n.addNode(childNode1);
		
		assertNull(n.getChildNode(2));
		
		Node firstChild = n.getChildNode(0);
		assertEquals(firstChild.getTitle(), "child1");
		assertEquals(firstChild.getContent(), "child1Content");
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
		assertEquals(n.getChildNodeLength(), 3);
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
		assertEquals(n.getChildNode(0).getContent(), "child1Content1");
		assertEquals(n.getChildNode(1).getContent(), "child1Content2");
		assertEquals(n.getChildNode(2).getContent(), "child1Content3");
		assertEquals(n.getChildNode(3).getContent(), "child1Content4");
		
		// search childa
		Node[] listt = n.getChildNode("childa");
		assertEquals(listt[0].getContent(), "child1Content3");
		assertEquals(listt[1].getContent(), "child1Content4");
	}

}
