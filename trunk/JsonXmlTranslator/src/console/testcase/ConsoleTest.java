package console.testcase;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import console.Console;

public class ConsoleTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Test
	public void testWelcomeMessage() {
		Console c = new Console();
		c.welcomeMessage();
	}
	
	@Test
	public void testTransformOptionXML() {
		String input = "X";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    Console c = new Console();
	    c.transformOption();
	    
	    assertEquals(outContent.toString(),"X");
	}
	
	@Test
	public void testTransformOptionJSON() {
		String input = "J";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    Console c = new Console();
	    c.transformOption();
	    
	    assertEquals(outContent.toString(),"J");
	}
	
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
