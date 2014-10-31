/*
 * This class is used for the testing of the Console interface control.
 */
package console.testcase;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import console.Console;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsoleTest.
 */
public class ConsoleTest {
	
	/** The out content. */
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	/**
	 * Test welcome message.
	 */
	@Test
	public void testWelcomeMessage() {
		Console c = new Console();
		System.setOut(new PrintStream(outContent));
		c.welcomeMessage();
		assertEquals(outContent.toString(),"*** Welcome to the XML & JSON Translator ***\n\n");
	}
	
	/**
	 * Test to get the default transform option.
	 */
	@Test
	public void testDefaultGetTransformOption() {
		Console c = new Console();
		System.setOut(new PrintStream(outContent));
		assertEquals(c.getTransformOption(),"X");
	}
	
	/**
	 * Test transform option message.
	 */
	@Test
	public void testTransformOptionMessage() {
		Console c = new Console();
		System.setOut(new PrintStream(outContent));
		c.transformOptionMessage();
		assertEquals(outContent.toString(),"Please choose the file type for the translation¡G\nXML - [X]\nJSON - [J]\n");
	}
	
	/**
	 * Test transform option xml.
	 */
	@Test
	public void testTransformOptionXML() {
		String input = "X";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    Console c = new Console();
	    c.transformOption();
	    
	    assertEquals(outContent.toString(),"X");
	}
	
	/**
	 * Test transform option json.
	 */
	@Test
	public void testTransformOptionJSON() {
		String input = "J";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    Console c = new Console();
	    c.transformOption();
	    
	    assertEquals(outContent.toString(),"J");
	}
	
	/**
	 * Test transform option invalid.
	 */
	@Test
	public void testTransformOptionInvalid() {
		String input = "XJ";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    Console c = new Console();
	    c.transformOption();
	    
	    assertEquals(outContent.toString(),"Please choose the translation option either XML or JSON !");
	}
}
