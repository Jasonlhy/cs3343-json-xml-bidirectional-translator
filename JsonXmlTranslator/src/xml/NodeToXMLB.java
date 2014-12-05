package xml;

import java.util.List;
import component.Node;

public class NodeToXMLB {
	
	public String TurnNodeIntoXml(Node rootNode) {
		return this.TurnNodeIntoXml(rootNode,  new String(), 0);
	}
	
	private String TurnNodeIntoXml(Node currentNode, String s, int index) {
		String outputString = s;
		if(index!=0){
			outputString +="\r\n";
			for(int a=0;a<index;a++)
				outputString += "\t";
		}
		outputString += "<" + currentNode.getTitle() + ">";
		if(currentNode.hasChildNode()) {
			List<Node> nodeList = currentNode.getChildNodes();
			for	(Node n:nodeList)
				outputString = TurnNodeIntoXml(n,outputString,index+1);
			outputString += "\r\n";
			for(int a=0;a<index;a++)
				outputString += "\t";
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
