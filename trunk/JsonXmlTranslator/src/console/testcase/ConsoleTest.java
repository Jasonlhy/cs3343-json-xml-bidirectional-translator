/*
 * This class is used for the testing of the Console interface controlling.
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
		assertEquals(outContent.toString(),"Please choose the file type for the translationĄG\nXML - [X]\nJSON - [J]\n");
	}
	
	/**
	 * Test transform option (xml) with input "X".
	 */
	@Test
	public void testTransformOptionXMLWithX() {
		String input = "X";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    Console c = new Console();
	    c.transformOption();
	    
	    assertEquals(outContent.toString(),"X");
	}
	
	/**
	 * Test transform option (xml) with input "XML".
	 */
	@Test
	public void testTransformOptionXMLWithXML() {
		String input = "XML";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    Console c = new Console();
	    c.transformOption();
	    
	    assertEquals(outContent.toString(),"X");
	}
	
	/**
	 * Test transform option (json) with input "J".
	 */
	@Test
	public void testTransformOptionJSONWithJ() {
		String input = "J";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    Console c = new Console();
	    c.transformOption();
	    
	    assertEquals(outContent.toString(),"J");
	}
	
	/**
	 * Test transform option (json) with input "JSON".
	 */
	@Test
	public void testTransformOptionJSONWithJSON() {
		String input = "JSON";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    Console c = new Console();
	    c.transformOption();
	    
	    assertEquals(outContent.toString(),"J");
	}
	
	/**
	 * Test transform option with invalid input.
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
