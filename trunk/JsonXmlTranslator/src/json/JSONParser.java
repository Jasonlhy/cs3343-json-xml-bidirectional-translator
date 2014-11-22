package json;

import java.io.IOException;
import java.io.StringReader;
import java.util.Stack;

import component.Node;

/**
 * Parse a JSON string into nodes. The fist node comes with a title: "root", holding a list of nodes that are title-content pair. 
 * 
 * Example 1, JSON Object is simple key-value pairs
 * {id:20} converts into two nodes => 
 * Node(title: "root")
 *   |
 *   |
 *   nodeList:
 *   [0] Node(title:"id", content:"20")
 *   
 *   
 * Example 2, JSON Object contains another JSON Object
 * {wife:{name:"jason"},age:20} converts into three nodes =>
 * Node(title: "root")
 *   |
 *   |
 *   nodeList:
 *   [0] Node(title:"wife")
 *       |
 *       |
 *       nodeList:
 *       [0] Node(title:"name", content:"jason") 
 *      
 *   [1] Node(title:"age",age:"20")
 *   
 * @author jason
 * @since 25-9-2014
 */
public class JSONParser {
	private String json;

	public JSONParser(String json) {
		this.json = json;
	}
	
	/**
	 * Parse the JSON string into node objects.
	 * 
	 * @return Node The root of the node
	 */
	public Node parse() {
		Stack<Node> nodeLevelStack = new Stack<Node>();

		char chars [] = json.toCharArray();
		String keyTemp = "";
		String valueTemp = "";
		boolean gettingKey = false;
		boolean gettingValue = false;

		// workingNode means the node you are working with to get the value
		// e.g. "id" : 19
		// when this scan though "id", the workingNode is NODE with title: "id"
		Node workingNode = null;
		for (char c : chars){
			if (c == '{' || c == '[') {
				if (workingNode == null) {
					Node rootNode = new Node("root");
					nodeLevelStack.add(rootNode);
				} else {
					nodeLevelStack.add(workingNode);
				}

				gettingKey = false;
				gettingValue = false;
			} else if (!gettingKey && !gettingValue) {
				if (c == '"') { // start getting key when you meet the first
								// quote
					gettingKey = true;
					keyTemp = "";
				} else if (c == ':') { // start getting value when you meet
										// :
					gettingValue = true;
					valueTemp = "";
				}
			} else if (gettingKey) {
				if (c == '"') { // end of getting value when you meet 2nd
								// quote
					workingNode = new Node(keyTemp);
					nodeLevelStack.peek().addNode(workingNode);
					gettingKey = false;
				} else {
					keyTemp += c;
				}
			} else if (gettingValue) {
				if (c == ',' || c == '}' || c == ']') {
					gettingValue = false;
					workingNode.setContent(valueTemp.trim());

					if (c == '}' || c == ']') {
						// keep the last level
						if (nodeLevelStack.size() > 1) {
							nodeLevelStack.pop();
						}
					}
				} else {
					// do not accept " as a value
					if (c != '"')
						valueTemp += c;
				}
			}
		}

		Node root = (nodeLevelStack.size() == 0) ? null : nodeLevelStack.get(0);
		return root;
	}
}
