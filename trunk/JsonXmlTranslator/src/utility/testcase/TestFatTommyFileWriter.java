package utility.testcase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utility.StringFileReader;
import utility.StringFileWriter;

import org.junit.Test;



public class TestFatTommyFileWriter {


	@Test
	public void testFileWriter() throws IOException{
		List<String> text = new ArrayList<String>();
		text.add("Jason");
		text.add("Tommy");
		
		StringFileWriter writer = new StringFileWriter("");
		writer.SetOutputPath("./text_file_writer.txt");
		writer.WriteToFile(text);
	}
	
	

}
