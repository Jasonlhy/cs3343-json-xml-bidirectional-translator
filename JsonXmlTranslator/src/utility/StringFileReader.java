package utility;

import java.io.*;
import java.nio.file.Files;

/**
 * Concrete class that implements StringFileReaderInterface for reading a file into a
 * string.
 * 
 * @author Jason
 */
public class StringFileReader implements StringFileReaderInterface {

	/** The file path for reading the file. */
	private String filename;

	/**
	 * Instantiates a new string file reader.
	 */
	private StringFileReader() {

	}

	/**
	 * Instantiates a new string file reader by specifying the file path
	 * 
	 * @param filename
	 *            the file path for reading the file
	 */
	public StringFileReader(String filename) {
		this();
		this.filename = filename;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utility.StringFileReaderInterface#getFileReader()
	 */
	public BufferedReader getFileReader() throws FileNotFoundException {
		InputStream in = new FileInputStream(filename);
		return new BufferedReader(new InputStreamReader(in));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utility.StringFileReaderInterface#readWholeFile()
	 */
	public String readWholeFile() throws IOException {
		String line;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = this.getFileReader();

		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		br.close();

		return sb.toString();
	}

}
