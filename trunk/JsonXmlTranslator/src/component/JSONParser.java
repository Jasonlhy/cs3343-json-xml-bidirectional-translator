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

		// workingNode means the node you are working with to get the value
		// e.g. "id" : 19
		// when this scan though "id", the workingNode is NODE with title: "id"
		Node workingNode = null;
		do {
			try {
				asiicode = reader.read();
				c = (char) asiicode;
				// System.out.println(c);

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
}
