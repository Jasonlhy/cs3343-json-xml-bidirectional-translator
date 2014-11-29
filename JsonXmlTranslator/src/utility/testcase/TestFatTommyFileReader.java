package utility.testcase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import utility.FatTommyFileReader;

import org.junit.Test;



public class TestFatTommyFileReader {


	@Test
	public void testReadStudent() throws IOException{
		FatTommyFileReader r = new FatTommyFileReader("./XmlFile/Student.txt");
		assertEquals("<student></student>",r.readWholeFile());
	}
	
	
	@Test
	public void testInvalidStudent() throws IOException{
		FatTommyFileReader r = new FatTommyFileReader("./XmlFile/InvalidStudent.txt");
		assertEquals("<student>",r.readWholeFile());
	}
	
	
	@Test
	public void testStudentWithName() throws IOException{
		FatTommyFileReader r = new FatTommyFileReader("./XmlFile/StudentWithName.txt");
		assertEquals("<student><name>Tommy</name></student>",r.readWholeFile());
	}

}
