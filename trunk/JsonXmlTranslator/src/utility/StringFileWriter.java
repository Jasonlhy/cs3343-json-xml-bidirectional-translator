package utility;

import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class StringFileWriter implements StringFileWriterInterface{

	
	String outPath ;
	
	public StringFileWriter(String filePath){
		outPath = filePath;
		
	}
	public StringFileWriter(){
		outPath = "./result.txt";
	}
	
	public void SetOutputPath(String filePath){
		this.outPath=filePath;
	}
	
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
