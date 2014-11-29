package utility;

import java.util.List;
import java.io.IOException;

/**
 * Define a interface that write a list of string into a file
 * 
 */
public interface StringFileWriterInterface {

	/**
	 * Write to file by a list of string
	 * 
	 * @param input
	 *            The content to be written to the output file, each string element in the
	 *            List should be occupied by one line in the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void WriteToFile(List<String> input) throws IOException;

}
