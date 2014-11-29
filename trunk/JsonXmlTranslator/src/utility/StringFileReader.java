package utility;

import java.io.*;
import java.nio.file.Files;


public class StringFileReader implements StringFileReaderInterface{
	
	private String filename;
	
	
	private StringFileReader(){
		
	}
	
	public StringFileReader(String filename){
		this();
		this.filename = filename;
	}
	
	public BufferedReader getFileReader() throws FileNotFoundException{
		InputStream in  = new FileInputStream(filename);
		return  new BufferedReader(new InputStreamReader(in));
		
	}
	
	public String readWholeFile() throws IOException{
		String line ;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = this.getFileReader();
		
		while((line=br.readLine())!=null){
			sb.append(line);
			
		}
		
		return sb.toString();
	}

}
