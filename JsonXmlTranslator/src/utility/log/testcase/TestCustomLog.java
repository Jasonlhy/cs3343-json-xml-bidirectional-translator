package utility.log.testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import utility.log.CustomLog;

public class TestCustomLog {

	@Test
	public void testInstance() {
		try{
			CustomLog.getInstance();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testErrorMsg(){
		try{
			CustomLog.getInstance().error("This is an error message.");
		}catch(Exception ex){
			CustomLog.getInstance().error("This is an error exception.");
		}
	}
	
	@Test
	public void testWarnMsg(){
		try{
			CustomLog.getInstance().warn("This is an warn message.");
		}catch(Exception ex){
			CustomLog.getInstance().warn("This is an warn exception.");
		}
	}
	
	@Test
	public void testInfoMsg(){
		try{
			CustomLog.getInstance().info("This is an info message.");
		}catch(Exception ex){
			CustomLog.getInstance().info("This is an info exception.");
		}
	}
	
	@Test
	public void testDebugMsg(){
		try{
			CustomLog.getInstance().debug("This is an debug message.");
		}catch(Exception ex){
			CustomLog.getInstance().debug("This is an debug exception.");
		}
	}
	
	@Test
	public void testTraceMsg(){
		try{
			CustomLog.getInstance().trace("This is an trace message.");
		}catch(Exception ex){
			CustomLog.getInstance().trace("This is an trace exception.");
		}
	}

}
