package component;

import java.io.IOException;
import java.io.StringReader;
import java.util.Stack;

/**
 * Parse a json into nodes
 * 
 * @author jason
 * @since 25-9-2014
 */
public class JSONParser {
	private String json;

	public JSONParser(String json) {
		this.json = json;
	}

	public Node parse() {
		int asiicode = -1;
		char c;
		Stack<Node> nodeLevelStack = new Stack<Node>();

		StringReader reader = new StringReader(json);

		String keyTemp = "";
		String valueTemp = "";
		boolean gettingKey = false;
		boolean gettingValue = false;

//		Node currentLevelNode = new Node("root");
//		Node workingNode = currentLevelNode;
		Node currentLevelNode = null;
		Node workingNode = null;
		do {
			try {
				asiicode = reader.read();
				c = (char) asiicode;
				//System.out.println(c);

				if (c == '{' || c == '[') {
					Node baseNode = new Node("base");
					
					if (currentLevelNode != null)
						nodeLevelStack.add(currentLevelNode);
					if (workingNode != null)
						workingNode.addNode(baseNode);
					
					nodeLevelStack.add(baseNode);


					// readjust the base level
					currentLevelNode = baseNode;
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
							if (nodeLevelStack.size() > 1) // keep the last level
								nodeLevelStack.pop();
						}
					} else {
						valueTemp += c;
					}
				} 

			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (asiicode != -1);

		Node root = (nodeLevelStack.size() == 0) ? null : nodeLevelStack.get(0);
		return root;
	}

	public static void main(String[] args) {
		// seem ok with the first 2
		JSONParser parser = new JSONParser("{\"id\":19}");

		Node root = parser.parse();
		System.out.println(root);

		JSONParser parser2 = new JSONParser("{\"id\":19,\"home\":\"fanling\"}");
		Node root2 = parser2.parse();
		System.out.println(root2);
		
		// 3 hv problem negationable
		JSONParser parser3 = new JSONParser("{\"id\":19,\"home\":\"fanling\",\"wife\":{\"name\":\"hehe\",\"phonenumber\":\"61556960\"}");
		Node root3 = parser3.parse();
		System.out.println(root3);
		
		// 4 failed =.=
		JSONParser parser4 = new JSONParser("{\"id\":19,\"home\":\"fanling\",\"wife\":[{\"name\":\"hehe\",\"phonenumber\":\"61556960\"},{\"name\":\"ricky\",\"phonenumber\":\"99999\"}]");
		Node root4 = parser4.parse();
		System.out.println(root4);
	}
}
