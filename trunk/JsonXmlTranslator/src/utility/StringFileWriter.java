package utility;

import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Concrete class that implements StringFileWriterInterface for writing a list of string into a file
 */
public class StringFileWriter implements StringFileWriterInterface{

	
	/** The file path of output file*/
	String outPath ;
	
	/**
	 * Instantiates a new string file writer by specifying the output file
	 *
	 * @param filePath the file path of the output file
	 */
	public StringFileWriter(String filePath){
		outPath = filePath;
		
	}
	
	/**
	 * Instantiates a new string file writer by specifying the default file to "./result.txt"
	 */
	public StringFileWriter(){
		outPath = "./result.txt";
	}
	
	/**
	 * Sets the output path.
	 *
	 * @param filePath the file path
	 */
	public void SetOutputPath(String filePath){
		this.outPath=filePath;
	}
	
	/* (non-Javadoc)
	 * @see utility.StringFileWriterInterface#WriteToFile(java.util.List)
	 */
	public void WriteToFile(List<String> input) throws IOException{
		
		if(input!=null){
			FileWriter output = new FileWriter(outPath);
			BufferedWriter writer = new BufferedWriter(output);
	 
			for(int i = 0 ; i <input.size();i++){
				writer.write(input.get(i));
				writer.newLine();
				
			}
			
			writer.close();
			

		}
		
	}
}
