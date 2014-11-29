package xml;

/**
 * Beautifier a XML string with propoer indentation
 * @author Chan Chi Hang
 *
 */
public class XMLBeautifier {
	public String beautifier(String input) {
		int intentLevel = -1;
		String output = "";
		String eachLine ="";
		boolean close = false;
	
		
		for (int i=0;i<input.length();i++) {
			char c1 = input.charAt(i);
			char c2= '\0';
			
			if (i+1<input.length()) 
				c2 = input.charAt(i+1);

			if (c1=='<') {
				output+="\n";
				if (c2=='/'){
					output+=createIntent(intentLevel);
					intentLevel--;
				}
				else{
					intentLevel++;
					output+=createIntent(intentLevel);
				}
				
				output+=c1;
				while (true) {
					char inner = input.charAt(i+1);
					output+=inner;
					i++;
					if (inner=='>')
						break;
				}
					
			}
			else
				output+=c1;
			
		}
		return output;
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
			s += "   ";
		}

		return s;
	}

	
}
