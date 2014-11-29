package json;


/**
 * The Class JSONParseException when the JSONParser faces some parsing problem
 * 
 * @see JSONParser
 */
public class JSONParseException extends RuntimeException {
	
	/**
	 * Instantiates a new JSON parse exception.
	 *
	 * @param message the message
	 */
	public JSONParseException(String message){
		super(message);
	}
}
