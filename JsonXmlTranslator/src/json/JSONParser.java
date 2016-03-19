package json;

import component.Node;
/**
 * Parse a JSON string into nodes. The fist node comes with a title: "root",
 * holding a list of nodes that are title-content(key-value) pair. <br>
 * <br>
 * 
 * This is implemented as naive recursive descent using the grammar posted on json.org. 
 * The parsing is using top down approach, unexpected syntax will be detected as throws JSONParseException in runtime.<br>
 * 
 * NOTE:<br>
 * Node has title only : use as base node parent for holding key-value nodes or array elements<br>
 * Node has both title and value: use as holding key-value in JSON Object<br>
 * Node has null title (I mean null reference not "null") : It's an array element.<br>  
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
 * <pre>
 * Example 3, JSON Object contains JSONArray
 * {wife:{name:"jason"},age:[20, 30]} converts into three nodes =>
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
 *   [1] Node(title:"age")
 *   	|
 *   	|
 *		nodeList:
 *	  	[0] Node(title: null, content: "20")
 *		[1] Node(title: null, content: "30")
 * </pre>
 * 
 * @author jason
 * @since 25-9-2014
 * @version 2.0
 */
 public class JSONParser {
	private String jsonString;
	private int idx = -1; // next scan character index

	public JSONParser(String jsonString) {
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

	/**
	 * Parse the JSON string into node objects.
	 * 
	 * @return Node The root of the node
	 * @throws JSONParseException
	 *             when the parsing encountered error at runtime
	 */
	public Node parse() {
		if (isEnd()){
			throw new JSONParseException("Empty String");
		}
		
		Node jsonObject = object(new Node("root"));
		if (!isEnd()){
			throw new JSONParseException("The string is longer than enough to be a valid JSON");
		} else {
			return jsonObject;
		}
	}

	public Node object(Node parentNode) {
		ws();
		next('{');
		ws();
		if (peek() == '}') {
			next('}');
		} else {
			members(parentNode);
			ws();
			next('}');
		}
		
		return parentNode;
	}

	public void members(Node parentNode) {
		ws();
		pair(parentNode);
		ws();
		
		
		if (isEnd()){
			throw new JSONParseException("Unexpected ending of JSON Object, may be missing } ");
		}
		
		if (peek() == ',') {
			next(',');
			members(parentNode);
		}
	}

	public void pair(Node parentNode) {
		ws();
		String key = string();
		System.out.println("Key in json object: " + key);
		ws();
		next(':');
		ws();
		Object value = value(new Node(key));
		if (value instanceof Node){
			parentNode.addNode((Node)value);
		} else {
			parentNode.addNode(new Node(key, (String) value));
		}
		
		System.out.println("Value in json object: " + value);
	}

	public Object value(Node parentNode) {
		ws();
		
		if (isEnd()){
			throw new JSONParseException("Expected a JSON value at " + idx);
		}
		
		char ch = peek();
		String returnStr = null;
		
		switch (ch){
		case '{' : 
			return object(parentNode);
		case '[' :
			return array(parentNode);
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

	/** TODO */
	public Node array(Node parentNode) {
		ws();
		next('[');
		ws();
		
		if (peek() == ']'){
			next(']'); // empty array;
		} else {
			elements(parentNode);
			ws();
			next(']');
		}
		
		return parentNode;
	}

	/** TODO */
	public void elements(Node parentNode) {
		ws();
		
		Object value = null;
		if (peek() == '[' || peek() == '{'){
			value = value(new Node(null)); // dummpy
		} else {
			value = value(parentNode);
		}
		
		if (value instanceof Node){
			parentNode.addNode((Node)value);
		} else {
			parentNode.addNode(new Node(null, (String)value));
		}
		
		System.out.println("value of array elements: " + value);
		ws();
		if (peek() == ','){
			next(',');
			elements(parentNode);
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
			return Integer.parseInt(numberString) + "";
		} catch (NumberFormatException ex){
			try {
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
			next();
		}

		while (!isEnd() && scanning) {
			c = peek();

			if (c == '"') {
				scanning = false;
				next();
			} else {
				str += c;
				next();
			}
		}
		
		if (str == null){
			throw new JSONParseException("Expected key as string at " + idx);
		}

		return str;
	}

	
}
