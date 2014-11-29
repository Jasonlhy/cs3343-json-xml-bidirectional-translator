/**
 * 
 */
package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * A log used for the project
 * 
 * @author Thomas
 *
 */
public class CustomLog {
	private enum LogLevel { ERROR, WARN, INFO, DEBUG, TRACE };
	private File logFile;
	private FileWriter fw;
	private LogLevel currLevel;
	private BufferedWriter bufferedWriter;
	
	private static CustomLog instance = null;
	protected CustomLog(){
		Properties prop = new Properties();
		InputStream input = null;
		try {
			 
			input = new FileInputStream("src/properties/log.properties");
	 
			// load a properties file
			prop.load(input);
			
			// get log level
			currLevel = string2LogLevel((String) prop.get("log.level"));
			
			// create directory
			File directory = new File((String) prop.get("log.location"));
			if(!directory.exists())
				directory.mkdir();
			
			// create file
			logFile = new File(directory + "/" + (String) prop.get("log.filename"));
			if(!logFile.exists())
				logFile.createNewFile();
			
			// prepare FileWriter
			fw = new FileWriter(logFile, true);
			bufferedWriter = new BufferedWriter(fw);
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Get the instance of Log class
	 * 
	 * @return CustomLog singleton class 
	 */
	public static CustomLog getInstance(){
		if(instance == null)
			instance = new CustomLog();
		return instance;
	}
	
	private boolean isCurrLogLevel(LogLevel toBeCompare){
		return (toBeCompare.compareTo(this.currLevel) <= 0);
	}
	
	private LogLevel string2LogLevel(String level){
		if(level.equals(LogLevel.TRACE.toString()))
			return LogLevel.TRACE;
		else if(level.equals(LogLevel.DEBUG.toString()))
			return LogLevel.DEBUG;
		else if(level.equals(LogLevel.INFO.toString()))
			return LogLevel.INFO;
		else if(level.equals(LogLevel.WARN.toString()))
			return LogLevel.WARN;
		else if(level.equals(LogLevel.ERROR.toString()))
			return LogLevel.ERROR;
		return null;
	}
	
	private String getTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.S");
		//get current date time with Date()
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private void write2File(String record) throws IOException{
		bufferedWriter.write(record);
		bufferedWriter.newLine();
	}
	
	private void genericLog(LogLevel logLevel, String s){
		if(!isCurrLogLevel(logLevel))
			return;
		
		String record = concateLogLevelName(logLevel.toString()) + " " + 
						getTime() + " " + 
						"[" + getPreviousClassName() + "] " +
						s;
		System.out.println(record);
		try{
			write2File(record);
		}catch(Exception ex){
			
		}
	}
	
	private String concateLogLevelName(String levelName){
		
		if(levelName.length() < 5){
			String space = "";
			for(int x = 0; x < (5-levelName.length()); x++)
				space += " ";
			return levelName + space;
		}
		else if(levelName.length() > 5){
			return levelName.substring(0, 4);
		}
		else
			return levelName;
	}
	
	private String getPreviousClassName(){
		String className = null;
		int stackTraceLevel = 0;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		
		for(int x = 1; x < ste.length; x++){
			String eachClassName = ste[x].getClassName();
			if(!eachClassName.equals(this.getClass().getName())){
				className = eachClassName;
				break;
			}
		}
		
		return className;
	}
	
	/**
	 * Close the log file
	 * 
	 */
	public void closeFile(){
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Announce a log with error log level
	 * 
	 * @param s the error message to be announced
	 */
	public void error(String s){
		genericLog(LogLevel.ERROR, s);
	}
	
	/**
	 * Announce a log with error log level
	 * 
	 * @param ex the exception to be announced
	 */
	public void error(Exception ex){
		genericLog(LogLevel.ERROR, ex.getMessage());
	}
	
	/**
	 * Announce a log with warn log level
	 * 
	 * @param s the error message to be announced
	 */
	public void warn(String s){
		genericLog(LogLevel.WARN, s);
	}
	
	/**
	 * Announce a log with warn log level
	 * 
	 * @param ex the exception to be announced
	 */
	public void warn(Exception ex){
		genericLog(LogLevel.WARN, ex.getMessage());
	}
	
	/**
	 * Announce a log with info log level
	 * 
	 * @param s the error message to be announced
	 */
	public void info(String s){
		genericLog(LogLevel.INFO, s);
	}
	
	/**
	 * Announce a log with info log level
	 * 
	 * @param ex the exception to be announced
	 */
	public void info(Exception ex){
		genericLog(LogLevel.INFO, ex.getMessage());
	}
	
	/**
	 * Announce a log with debug log level
	 * @param s the error message to be announced
	 */
	public void debug(String s){
		genericLog(LogLevel.DEBUG, s);
	}
	
	/**
	 * Announce a log with debug log level
	 * 
	 * @param ex the exception to be announced
	 */
	public void debug(Exception ex){
		genericLog(LogLevel.DEBUG, ex.getMessage());
	}
	
	/**
	 * Announce a log with trace log level
	 * 
	 * @param s the error message to be announced
	 */
	public void trace(String s){
		genericLog(LogLevel.TRACE, s);
	}
	
	/**
	 * Announce a log with trace log level
	 * 
	 * @param ex the exception to be announced
	 */
	public void trace(Exception ex){
		genericLog(LogLevel.TRACE, ex.getMessage());
	}
	
}
