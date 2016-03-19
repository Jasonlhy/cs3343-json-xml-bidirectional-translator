package json;

import java.io.IOException;
import java.io.StringReader;
import java.util.Stack;

import component.Node;

/**
 * Parse a JSON string into nodes. The fist node comes with a title: "root",
 * holding a list of nodes that are title-content pair. <br>
 * <br>
 * 
 * <pre>
 * Example 1, JSON Object is simple key-value pairs
 * {id:20} converts into two nodes => 
 * Node(title: "root")
 *   |
 *   |
 *   nodeList:
 *   [0] Node(title:"id", content:"20")
 * </pre>
 * 
 * 
 * <pre>
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
 * </pre>
 * 
 * @author jason
 * @since 25-9-2014
 */
public class JSONParser {
	private String json;

	public JSONParser(String json) {
		this.json = json;
	}

	public static void main(String[] args) {
		JSONValidator validator = new JSONValidator(" { } ");
		validator.parse();

		validator = new JSONValidator(" { \"jason\" : 123 } ");
		validator.parse();

		validator = new JSONValidator(" { \"jason\" : 123,\"age\" : 22.94, \"weight\" : -99 } ");
		validator.parse();
		
		validator = new JSONValidator(" { \"jason\" : [2333,2234,122,true, {\"nestedKey\" : \"nestedValue\" }], \"age\" : \"it seems ok\"}");
		validator.parse();

		System.out.println("No error");
	}

	/**
	 * Parse the JSON string into node objects.
	 * 
	 * @return Node The root of the node
	 * @throws JSONParseException
	 *             when the parsing encountered error at runtime
	 */
	public Node parse() {
		Stack<Node> nodeLevelStack = new Stack<Node>();

		char chars[] = json.toCharArray();
		String keyTemp = "";
		String valueTemp = "";
		boolean gettingKey = false;
		boolean gettingValue = false;

		// workingNode means the node you are working with to get the value
		// e.g. "id" : 19
		// when this scan though "id", the workingNode is NODE with title: "id"
		Node workingNode = null;
		for (int idx = 0; idx < json.length(); idx++) {
			char c = json.charAt(idx);

			if (c == '{') {
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
					if (nodeLevelStack.size() == 0) {
						throw new JSONParseException("Missing open bracket for double quote at " + idx);
					}

					workingNode = new Node(keyTemp);
					nodeLevelStack.peek().addNode(workingNode);
					gettingKey = false;
				} else {
					keyTemp += c;
				}
			} else if (gettingValue) {
				if (c == ',' || c == '}') {
					gettingValue = false;
					if (workingNode == null) {
						throw new JSONParseException("Missing key for value around " + idx);
					}
					workingNode.setContent(valueTemp.trim());

					if (c == '}') {

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

		if (nodeLevelStack.size() == 0) {
			throw new JSONParseException("Invalid JSON");
		}

		Node root = nodeLevelStack.get(0);
		return root;
	}
}

class JSONValidator {
	private String jsonString;
	private int idx = -1; // next scan character index

	public JSONValidator(String jsonString) {
		this.jsonString = jsonString;
		this.idx = 0;
	}

	public char peek() {
		return jsonString.charAt(idx);
	}

	/**
	 * Increment the index and check the character it is the expected character
	 * or not
	 * 
	 * @param expectedChar
	 */
	public void next(char expectedChar) {
		if (peek() != expectedChar) {
			// run time exceptions
			throw new JSONParseException("Expected " + expectedChar + " at " + idx + " but " + peek() + " is found");
		}

		idx++;
	}

	/**
	 * Increment the index without checking the validity of the syntax
	 */
	public void next() {
		idx++;
	}

	public void parse() {
		object();
	}

	public void object() {
		ws();
		next('{');
		ws();
		if (peek() == '}') {
			System.out.println("Empty JSON Object");
		} else {
			members();
		}

		ws();
		next('}');
	}

	public void members() {
		ws();
		pair();
		ws();
		
		
		if (isEnd()){
			throw new JSONParseException("Unexpected ending of JSON Object, may be missing } ");
		}
		
		if (peek() == ',') {
			next(',');
			members();
		}
	}

	public void pair() {
		ws();
		String key = string();
		System.out.println("Key in json object: " + key);
		ws();
		next(':');
		ws();
		String value = value();
		System.out.println("Value in json object: " + value);
	}

	public String value() {
		ws();
		
		if (isEnd()){
			throw new JSONParseException("Expected a JSON value at " + idx);
		}
		
		char ch = peek();
		String returnStr = "";
		
		switch (ch){
		case '{' : 
			object();
			break;
		case '[' :
			array();
			break;
		case '"' :
			return string();
		case '-':
			return number();
		default:
			returnStr = (Character.isDigit(ch)) ? number() : word() + ""; 
		}
		
		return returnStr;
	}
	
	public String word() {
		char ch = peek();
		switch (ch) {
		case 't':
			next('t');
			next('r');
			next('u');
			next('e');
			return "true";
		case 'f':
			next('f');
			next('a');
			next('l');
			next('s');
			next('e');
			return "false";
		case 'n':
			next('n');
			next('u');
			next('l');
			next('l');
			return "null";
		default:
			throw new JSONParseException("Unknowned token " + ch + " at " + idx);
		}

	}

	public void array() {
		ws();
		next('[');
		ws();
		
		if (peek() == ']'){
			next(']'); // empty array;
		} else {
			elements();
		}
		
		ws();
		next(']');
	}

	public void elements() {
		ws();
		String value = value();
		System.out.println("value of array elements: " + value);
		ws();
		if (peek() == ','){
			next(',');
			elements();
		}
	}

	public boolean isEnd() {
		return idx >= jsonString.length();
	}

	/** skip white Space */
	public void ws() {
		while (!isEnd()) {
			if (Character.isWhitespace(peek())) {
				idx++;
			} else {
				break;
			}
		}
	}

	public String number() {
		ws();
		int initIdx = idx;
		
		String numberString = "";
		if (peek() == '-'){
			next('-');
			numberString += '-';
		}
		
		// digit before .
		while (!isEnd()) {
			char c = peek();
			if (Character.isDigit(c)) {
				numberString += c;
				idx++;
			} else {
				break;
			}
		}
		
		if (isEnd()){
			throw new JSONParseException("Unexpected number at the end");
		}
		
		// digit after .
		if (peek() == '.'){
			next('.');
			numberString += '.';
			
			while (!isEnd()) {
				char c = peek();
				if (Character.isDigit(c)) {
					numberString += c;
					idx++;
				} else {
					break;
				}
			}
		}

		// integer or double
		try {
			System.out.println("Parse as Int for " + numberString);
			return Integer.parseInt(numberString) + "";
		} catch (NumberFormatException ex){
			try {
				System.out.println("Parse as double for " + numberString);
				return Double.parseDouble(numberString) + "";
			} catch (NumberFormatException ex2){
				throw new JSONParseException("Expected number at " + initIdx + " but it is not found");
			}
		}
		
	}

	public int nextInt() {
		ws();

		String intStr = "";
		while (!isEnd()) {
			char c = peek();
			if (Character.isDigit(c)) {
				intStr += c;
				idx++;
			} else {
				break;
			}
		}

		return Integer.parseInt(intStr);
	}

	/* next string without "" */
	public String string() {
		ws();

		String str = null;
		boolean scanning = false;

		char c = peek();
		if (c == '"') {
			scanning = true;
			str = "";
			idx++;
		}

		while (!isEnd() && scanning) {
			c = peek();

			if (c == '"') {
				scanning = false;
				idx++;
			} else {
				str += c;
				idx++;
			}
		}

		return str;
	}

	
}
