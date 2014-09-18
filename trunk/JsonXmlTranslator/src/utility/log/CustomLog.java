/**
 * 
 */
package utility.log;

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
 * @author Thomas
 *
 */
public class CustomLog {
	private enum LogLevel { ERROR, WARN, INFO, DEBUG, TRACE };
	private File logFile;
	private FileWriter fw;
	private LogLevel currLevel;
	
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
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(record);
		bw.close();
	}
	
	private void genericLog(LogLevel logLevel, String s){
		if(!isCurrLogLevel(logLevel))
			return;
		String record = logLevel.toString() + " " + getTime() + " " + s;
		System.out.println(record);
		try{
			write2File(record);
		}catch(Exception ex){
			
		}
	}
	
	public void error(String s){
		genericLog(LogLevel.ERROR, s);
	}
	
	public void error(Exception ex){
		genericLog(LogLevel.ERROR, ex.getMessage());
	}
	
	public void warn(String s){
		genericLog(LogLevel.WARN, s);
	}
	
	public void warn(Exception ex){
		genericLog(LogLevel.WARN, ex.getMessage());
	}
	
	public void info(String s){
		genericLog(LogLevel.INFO, s);
	}
	
	public void info(Exception ex){
		genericLog(LogLevel.INFO, ex.getMessage());
	}
	
	public void debug(String s){
		genericLog(LogLevel.DEBUG, s);
	}
	
	public void debug(Exception ex){
		genericLog(LogLevel.DEBUG, ex.getMessage());
	}
	
	public void trace(String s){
		genericLog(LogLevel.TRACE, s);
	}
	
	public void trace(Exception ex){
		genericLog(LogLevel.TRACE, ex.getMessage());
	}
	
}
