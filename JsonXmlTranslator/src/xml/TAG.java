package xml;

import java.util.regex.Pattern;

/**
 * enum class that declare xml tag patterns
 * @author Thomas
 *
 */
public enum TAG { 
	OPEN("<(?!/)[^<>]+(?<!/)>"), CLOSE("</[^<>]+>"), SHORT("<[^<>]+/>");
	private String regex;

	private TAG(String regex){
		this.regex = regex;
	}
	
	/**
	 * Return if the input str is a valid tag (OPEN, CLOSE or SHORT)
	 * 
	 * @param str input to test
	 * @return true if it is valid, false if it is invalid
	 */
	public boolean isValid(String str){
		return Pattern.compile(this.regex).matcher(str).matches();
	}
}

