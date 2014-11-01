package utility.io;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class FatTommyFileWriter implements TommyFileWriter{

	
	String outPath ;
	
	public FatTommyFileWriter(String filePath){
		outPath = filePath;
		
	}
	public FatTommyFileWriter(){
		outPath = "./result.txt";
	}
	
	public void SetOutputPath(String filePath){
		this.outPath=filePath;
	}
	
	public void WriteToFile(List input) throws IOException{
		
		if(input!=null){
			FileWriter output = new FileWriter(outPath);
			BufferedWriter writer = new BufferedWriter(output);
	 
			for(int i = 0 ; i <input.getSize().height;i++){
				writer.write(input.getItem(i));
				writer.newLine();
				
			}
			
			writer.close();
			

		}
		
	}
}
