package utility;

import java.io.*;

/**
 * Define interface for reading a file into a string.
 * 
 * @author Jason
 */
public interface StringFileReaderInterface {

	/**
	 * Read whole file.
	 * 
	 * @return the string containing all content in the file wihtout linebreak
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String readWholeFile() throws IOException;

	/**
	 * Gets the file reader.
	 * 
	 * @return the file reader for reading the file
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public BufferedReader getFileReader() throws FileNotFoundException;

}
