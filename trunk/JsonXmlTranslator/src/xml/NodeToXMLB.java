package xml;

import java.util.List;
import component.Node;

public class NodeToXMLB {
	
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
		else{
			if(currentNode.getContent()!=null)
			outputString += currentNode.getContent();
		}
		outputString += "</" + currentNode.getTitle() + ">";
		return outputString;
	}
	
	public String outputXMLFile(Node rootNode){		
		return TurnNodeIntoXml(rootNode);
	}
}
