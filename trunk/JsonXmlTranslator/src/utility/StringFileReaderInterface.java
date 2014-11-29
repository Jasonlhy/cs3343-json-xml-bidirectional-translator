package utility;

import java.io.*;

public interface StringFileReaderInterface {

	public String readWholeFile() throws IOException;
	
	public BufferedReader getFileReader() throws FileNotFoundException;

	
}
