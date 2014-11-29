package json;

import utility.CustomLog;
import component.Node;

/**
 * Beautifier a JSON output with indentation. It can either beautifier a JSON using
 * JSON parser or beautifier a JSON as a string . If JSONParser occurs some
 * JSONParseException, the beautifier will try to beautifier the JSON as a
 * string.
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
			try {
				Node node = parser.parse();
				outputString = beautifierUseNode(node, jsonString);
			} catch (JSONParseException ex) {
				CustomLog.getInstance().error(ex);
				System.out
						.println("Failed to parse using node, try to beautifer using string only");
				outputString = beautifierUseString(jsonString);
			}
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
	 * character one by one. <br>
	 * 
	 * It faces { then append { into current line, jump to next line with next level intent.<br>
	 * It faces } then write } into next line with previous intent<br>
	 * It faces , then append , into current line, jump to the next line with current intent<br>
 	 * else, just append to the current line.
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
				intentedString += c + "\n" + createIntent(intentLevel);
				eachLine = "";
			} else if (c == '}') {
				intentedString += eachLine.trim();
				intentLevel -= 1;
				intentedString += "\n" + createIntent(intentLevel) + c;
				eachLine = "";
			} else if (c == ',') {
				intentedString += eachLine.trim();
				intentedString += c + "\n" + createIntent(intentLevel);
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

}
