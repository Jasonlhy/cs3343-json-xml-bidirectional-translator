package xml;

import java.util.*;

import component.Node;

public class NodeToXMLA {
	
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
	
	/* moved the testcase to a dedicated file lu~ */
	/* indentation required */
}
		