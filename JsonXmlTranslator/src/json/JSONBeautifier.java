package json;

import component.Node;

import utility.log.CustomLog;

/**
 * Beautifier a JSON output with indentation
 * 
 * 
 * 
 * @author Jason
 * @since 2014-11-22
 * 
 */
public class JSONBeautifier {

	/**
	 * Beautifier a JSON output with indentation
	 * 
	 * @param jsonString
	 *            JsonString to be beautifier
	 * @param converToNode
	 *            Use JSONParser and use nodeTOJSON
	 * @return a JSONString with indentation
	 */
	public String beautifier(String jsonString, boolean converToNode) {
		String outputString = "";
		if (converToNode) {
			JSONParser parser = new JSONParser(jsonString);
			Node node = parser.parse();
			outputString = beautifierUseNode(node, jsonString);
		} else {
			outputString = beautifierUseString(jsonString);
		}

		return outputString;
	}

	/**
	 * Beautifier a JSON output with indentation use nodeTOJSON
	 * 
	 * @param node
	 *            Root
	 * @param jsonString
	 *            JsonString to be beautifier
	 * @return a JSONString with indentation
	 */
	public String beautifierUseNode(Node node, String jsonString) {
		return NodeToJSON.toJSONString(node);
	}

	/**
	 * Beautifier a JSON output with indentation with string only, this read the
	 * character one by one
	 * 
	 * @param jsonString
	 *            JsonString to be beautifier
	 * @return a JSONString with indentation
	 */
	public String beautifierUseString(String jsonString) {
		
		int intentLevel = 0;
		String intentedString = "";
		String eachLine = "";
		for (int idx = 0; idx < jsonString.length(); idx++) {
			char c = jsonString.charAt(idx);
			if (c == '{') {
				intentedString += eachLine.trim();
				intentLevel += 1;
				intentedString += getNextLineCharacter(c, intentLevel);
				eachLine = "";
			} else if (c == '}') {
				intentedString += eachLine.trim();
				intentLevel -= 1;
				intentedString += getNextLineCharacter(c, intentLevel);
				eachLine = "";
			} else if (c == ',') {
				intentedString += eachLine.trim();
				intentedString += getNextLineCharacter(c, intentLevel);
				eachLine = "";
			} else {
				// Skip line break, tab... etc
				if (isPrintableCharacter(c))
					eachLine += c;
			}
		}

		return intentedString;
	}

	/**
	 * Test whether it is printable character
	 * 
	 * @param c
	 *            The characeter
	 * @return true if it is a printable character
	 */
	public boolean isPrintableCharacter(char c) {
		int asiiNumber = (int) c;

		return (asiiNumber >= 32);
	}

	/**
	 * Get character following end line character and next line intent
	 * 
	 * @param theChar
	 *            The character
	 * @param intentLevel
	 *            the intent level
	 * @return a beautiful string
	 */
	public String getNextLineCharacter(char theChar, int intentLevel) {
		String outputString = "";
		// for open bracket and common:
		// print the char at current level
		// then next char is at next level with intent
		//
		// for close bracket:
		// print the end line character
		// print the char at next level with intent
		//
		// for normal character:
		// just print the char
		if (theChar == '{' || theChar == ',') {
			outputString += theChar;
			outputString += "\n";
			outputString += createIntent(intentLevel);
		} else if (theChar == '}') {
			outputString += "\n";
			outputString += createIntent(intentLevel);
			outputString += theChar;
		} else {
			outputString += theChar;
		}

		return outputString;
	}

	/**
	 * Create intent
	 * 
	 * @param intentLevel
	 *            Intent level
	 * @return How many space as same as the intent level
	 */
	private String createIntent(int intentLevel) {
		String s = "";
		for (int i = 0; i < intentLevel; i++) {
			s += " ";
		}

		return s;
	}

	public static void main(String[] args) {
		JSONBeautifier b = new JSONBeautifier();
		String r;
		// r = b.beautifierUseString("{ \r\n id : 19, home : fanling }");
		r = b.beautifierUseString("{\r\nid: 你好}");
		System.out.println(r);

		// r = b.endLineCharacter('\r');
		// r =
		// b.beautifier("{ id : 19, home : fanling, wife: { name : hehe, phonenumber : 61556960 }, uni : city }");
		// System.out.println(r);
	}

}
