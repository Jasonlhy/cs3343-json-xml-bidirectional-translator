/*
 * This class is used for the testing of the Console interface controlling.
 */
package console.testcase;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

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
		assertEquals(outContent.toString(),"Please choose the file type for the translation.\nXML - [X]\nJSON - [J]\n");
	}
	
	/**
	 * Test transform option (xml) with input "X".
	 */
	@Test
	public void testTransformOptionXMLWithX() {
		class ConsoleJson extends Console{
			public void transformOption() {
				Scanner scanner = new Scanner(System.in);
				String transformOption = scanner.nextLine();
				if(transformOption.toUpperCase().equals("X") || transformOption.toUpperCase().equals("XML")){
					System.out.print("X");
				}else if (transformOption.toUpperCase().equals("J")|| transformOption.toUpperCase().equals("JSON")){
					System.out.print("J");
				}else{
					System.out.print("Please choose the translation option either XML or JSON !");
				}
			}
		}
		
		String input = "X";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    ConsoleJson cj=new ConsoleJson();
		cj.transformOption();
	    
	    assertEquals(outContent.toString(),"X");
	}
	
	/**
	 * Test transform option (xml) with input "XML".
	 */
	@Test
	public void testTransformOptionXMLWithXML() {
		class ConsoleJson extends Console{
			public void transformOption() {
				Scanner scanner = new Scanner(System.in);
				String transformOption = scanner.nextLine();
				if(transformOption.toUpperCase().equals("X") || transformOption.toUpperCase().equals("XML")){
					System.out.print("X");
				}else if (transformOption.toUpperCase().equals("J")|| transformOption.toUpperCase().equals("JSON")){
					System.out.print("J");
				}else{
					System.out.print("Please choose the translation option either XML or JSON !");
				}
			}
		}
		
		String input = "XML";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    ConsoleJson cj=new ConsoleJson();
		cj.transformOption();
	    
	    assertEquals(outContent.toString(),"X");
	}
	
	/**
	 * Test transform option (json) with input "J".
	 */
	@Test
	public void testTransformOptionJSONWithJ() {
		class ConsoleJson extends Console{
			public void transformOption() {
				Scanner scanner = new Scanner(System.in);
				String transformOption = scanner.nextLine();
				if(transformOption.toUpperCase().equals("X") || transformOption.toUpperCase().equals("XML")){
					System.out.print("X");
				}else if (transformOption.toUpperCase().equals("J")|| transformOption.toUpperCase().equals("JSON")){
					System.out.print("J");
				}else{
					System.out.print("Please choose the translation option either XML or JSON !");
				}
			}
		}
		
		String input = "J";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    ConsoleJson cj=new ConsoleJson();
		cj.transformOption();
	    
	    assertEquals(outContent.toString(),"J");
	}
	
	/**
	 * Test transform option (json) with input "JSON".
	 */
	@Test
	public void testTransformOptionJSONWithJSON() {
		class ConsoleJson extends Console{
			public void transformOption() {
				Scanner scanner = new Scanner(System.in);
				String transformOption = scanner.nextLine();
				if(transformOption.toUpperCase().equals("X") || transformOption.toUpperCase().equals("XML")){
					System.out.print("X");
				}else if (transformOption.toUpperCase().equals("J")|| transformOption.toUpperCase().equals("JSON")){
					System.out.print("J");
				}else{
					System.out.print("Please choose the translation option either XML or JSON !");
				}
			}
		}
		
		String input = "JSON";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    
	    System.setOut(new PrintStream(outContent));
	    
	    ConsoleJson cj=new ConsoleJson();
		cj.transformOption();
	    
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
