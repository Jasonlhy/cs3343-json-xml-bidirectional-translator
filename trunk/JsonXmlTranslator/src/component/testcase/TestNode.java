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
		System.out.println(n.getTitle());
	}
	
	@Test
	public void testGetContent() {
		n = new Node ("haha", "hehe");
		System.out.println(n.getContent());
	}	
	
	@Test
	public void testChildNodeANDHasChildNodeFunction() {
		n = new Node ("haha", "hehe");
		
		Node childNode1 = new Node("child1", "child1Content");
		System.out.println("Before insert childNode hasChildNode() return " + n.hasChildNode());
		
		n.addNode(childNode1);
		System.out.println("After insert childNode hasChildNode() return " + n.hasChildNode());
		
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
		
		n = new Node("titleHAHA",list);
		
		System.out.print("loop child: ");
		for (int i=0;i<n.getChildNodeLength();i++)
			System.out.print(n.getChildNode(i).getContent() + " ");
		
		System.out.println();
		
		System.out.print("Search childa: ");
		
		Node[] listt = n.getChildNode("childa");
		for (int i=0;i<listt.length;i++)
			System.out.print(listt[i].getContent() + " ");
		
		System.out.println();
	}

}
