package utility;

import java.io.*;

public interface TommyFileReader {
	
	
	public String readWholeFile() throws IOException;
	
	public BufferedReader getFileReader() throws FileNotFoundException;
	
	
	
	
}
