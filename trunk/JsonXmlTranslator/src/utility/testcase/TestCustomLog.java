package utility.testcase;

import static org.junit.Assert.*;

import org.junit.Test;
import utility.CustomLog;

public class TestCustomLog {

	@Test
	public void testInstance() {
		try{
			CustomLog.getInstance();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			CustomLog.getInstance().closeFile();
		}
	}
	
	@Test
	public void testErrorMsg(){
		try{
			CustomLog.getInstance().error("This is an error message.");
		}catch(Exception ex){
			CustomLog.getInstance().error("This is an error exception.");
		}finally{
			CustomLog.getInstance().closeFile();
		}
	}
	
	@Test
	public void testWarnMsg(){
		try{
			CustomLog.getInstance().warn("This is an warn message.");
		}catch(Exception ex){
			CustomLog.getInstance().warn("This is an warn exception.");
		}finally{
			CustomLog.getInstance().closeFile();
		}
	}
	
	@Test
	public void testInfoMsg(){
		try{
			CustomLog.getInstance().info("This is an info message.");
		}catch(Exception ex){
			CustomLog.getInstance().info("This is an info exception.");
		}finally{
			CustomLog.getInstance().closeFile();
		}
	}
	
	@Test
	public void testDebugMsg(){
		try{
			CustomLog.getInstance().debug("This is an debug message.");
		}catch(Exception ex){
			CustomLog.getInstance().debug("This is an debug exception.");
		}finally{
			CustomLog.getInstance().closeFile();
		}
	}
	
	@Test
	public void testTraceMsg(){
		try{
			CustomLog.getInstance().trace("This is an trace message.");
			
		}catch(Exception ex){
			CustomLog.getInstance().trace("This is an trace exception.");
		}finally{
			CustomLog.getInstance().closeFile();
		}
	}
	
	@Test
	public void testWrite10Msgs(){
		try{
			CustomLog.getInstance().trace("Trace");
			CustomLog.getInstance().debug("Debug");
			CustomLog.getInstance().info("Info");
			CustomLog.getInstance().warn("Warn");
			CustomLog.getInstance().error("Error");
			CustomLog.getInstance().closeFile();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
