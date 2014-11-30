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
		
		result+=toJSONString(node, false, true, true, 1);
		
		//Global End Tag
		result+="\n}";
		
		return result;
	}
	
	private static String toJSONString(Node node, boolean isArray, boolean upperIsFirst, boolean isFirst, int level) {
		
		String result = "";
		
		if (upperIsFirst)
			result+="";
		else 
			result+=",\n" + createIndent(level);
		
		if (isArray)
			if (isFirst)
				result += "\"" + node.getTitle() + "\": [\n" + createIndent(level) + "{\n" + createIndent(level + 1);
			else
				result += "{\n" + createIndent(level + 1);
		else
			if (!node.hasChildNode())
				result += "\"" + node.getTitle() + "\": "; // "key": .....
			else
				result += "\"" + node.getTitle() + "\": {\n" + createIndent(level); // "key": {.....

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
							result += toJSONString(node.getChildNode(i),duplicate, false, (j==i+1), level + 1);
						}
					}
					
					//Close array tag for duplicate node (Array)
					if (duplicate)
						result += "\n" + createIndent(level + 1) + "]" ;
					else
						//Case 2: Non Duplicate Node (Single Node)
						result += toJSONString(node.getChildNode(i),false, (i==0),(i==0), level + 1);
				}
				
		}
		else
			result += "\"" + node.getContent() + "\"";

		if (node.hasChildNode())
			result +="\n" + createIndent(level)  + "}";
		else
			result+="";
		
		return result;
	}
	
	/**
	 * Create indentation space
	 * 
	 * @param level, start from 1
	 * @return space, number of space is same as number of level
	 */
	private static String createIndent(int level){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < level; i++){
			builder.append(" ");
		}
		
		return builder.toString();
	}
}
