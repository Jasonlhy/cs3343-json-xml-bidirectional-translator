package component;

import java.util.*;

public class XMLWriter {
	
	private Stack<String> elementStack;
	private List<String> resultant;
	
	private void convert(Node root) {
		if (root.hasChildNode()) {
			elementStack.push(root.getTitle());
			resultant.add("<"+root.getTitle()+">");
			List<Node> child = root.getChildNodes();
			for (int i = 0; i < child.size(); i++) {
				this.convert(child.get(i));	
				if (i == child.size() - 1) {
					resultant.add("</"+elementStack.pop()+">");
				}
			}
		} else {
			elementStack.push(root.getTitle());
			resultant.add("<"+root.getTitle()+">" + root.getContent() + "</"+elementStack.pop()+">");
		}
	}
	
	public List<String> writeXML(Node root) {
		elementStack = new Stack<String>();
		resultant = new ArrayList<String>();
		this.convert(root);
		return resultant;
	}
	
	public static void main(String[] args) {
		
		//Test scenario A
		//originally adapted from TC1 of JSONParser.java
		JSONParser parserA = new JSONParser("{\"id\":19}");
		Node rootA = parserA.parse();
		System.out.println(rootA);
		XMLWriter writerA = new XMLWriter();
		for (String line : writerA.writeXML(rootA))
			System.out.println(line);
		
		//Test scenario B
		//originally adapted from TC2 of JSONParser.java
		JSONParser parserB = new JSONParser("{\"id\":19,\"home\":\"fanling\"}");
		Node rootB = parserB.parse();
		System.out.println(rootB);
		XMLWriter writerB = new XMLWriter();
		for (String line : writerB.writeXML(rootB))
			System.out.println(line);
		
	}
	
}
