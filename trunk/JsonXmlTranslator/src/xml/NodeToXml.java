package xml;

import java.util.List;
import component.Node;

public class NodeToXml {
	
	public String TurnNodeIntoXml(Node rootNode) {
		return this.TurnNodeIntoXml(rootNode,  new String());
	}
	
	private String TurnNodeIntoXml(Node currentNode, String s) {
		String outputString = s;
		outputString += "<" + currentNode.getTitle() + ">";
		if(currentNode.hasChildNode()) {
			List<Node> nodeList = currentNode.getChildNodes();
			for	(Node n:nodeList)
				outputString = TurnNodeIntoXml(n,outputString);
		}
		else
			outputString += currentNode.getContent();
		outputString += "</" + currentNode.getTitle() + ">";
		return outputString;
	}
	
	public void outputXMLFile(Node rootNode){		
		System.out.println(TurnNodeIntoXml(rootNode));
	}
}
