package json;

import java.util.ArrayList;

import component.Node;

/**
 * Output JSON String from Node recursively 
 * @author Chan Chi Hang
 *
 */
public class NodeToJSON {
	
	public static String toJSONString(Node node) {
		String result = "";
		
		//Global Start Tag
		result+="{\n";
		
		result+=toJSONString(node, false, true, true);
		
		//Global End Tag
		result+="\n}";
		
		return result;
	}
	
	private static String toJSONString(Node node, boolean isArray, boolean upperIsFirst, boolean isFirst) {
		
		String result = "";
		
		if (upperIsFirst)
			result+="";
		else 
			result+=",\n";
		
		if (isArray && isFirst) //"key": [ {......
			result += "\"" + node.getTitle() + "\": [\n{\n";
		else if (isArray && !isFirst)
			result += "{\n";
		else if (!node.hasChildNode())
			result += "\"" + node.getTitle() + "\": "; // "key": {.....
		else
			result += "\"" + node.getTitle() + "\": {\n"; // "key": {.....

		//Determine if there is any child node
		if (node.hasChildNode()) {
			ArrayList<String> loaded = new ArrayList<String>();
			
			//Loop though all nodes
			for (int i=0;i<node.getChildNodeLength();i++)
				//Skip node that has been parsed
				if (!loaded.contains(node.getChildNode(i).getTitle())) {
					loaded.add(node.getChildNode(i).getTitle());
					
					//Loop to Check if there is any node with same node name -> json array
					boolean duplicate = false;
					for (int j=i+1;j<node.getChildNodeLength();j++) {
						//Case 1: Duplicate Node detected
						if (node.getChildNode(i).getTitle().equals(node.getChildNode(j).getTitle())) {
							duplicate = true;
							result += toJSONString(node.getChildNode(i),duplicate, (i==0), (j==i+1));
						}
					}
					
					//Close array tag for duplicate node (Array)
					if (duplicate)
						result+="\n]";
					else
						//Case 2: Non Duplicate Node (Single Node)
						result += toJSONString(node.getChildNode(i),false, (i==0),(i==0));
				}
				else
					continue;

		}
		else
			result += "\"" + node.getContent() + "\"";

		if (node.hasChildNode())
			result+="\n}";
		else
			result+="";
		
		return result;
	}
}
