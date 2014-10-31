/*
 * This class is used for the Console interface control.
 */
package console;

import java.io.IOException;
import java.util.Scanner;

import component.JSONParser;
import component.Node;
import component.XMLWriter;
import utility.io.FatTommyFileReader;
import xml.NodeToXml;
import xml.XmlToNode;

// TODO: Auto-generated Javadoc
/**
 * The Class Console.
 */
public class Console {
	
	/** The transform option. */
	String transformOption="X";
	
	/**
	 * Instantiates a new console.
	 */
	public Console(){
		
	}
	
	/**
	 * Welcome message.
	 */
	public void welcomeMessage(){
		System.out.print("*** Welcome to the XML & JSON Translator ***\n");
		System.out.print("\n");
	}
	
	/**
	 * Gets the transform option.
	 * 
	 * @return the transform option
	 */
	public String getTransformOption(){
		return transformOption;
	}
	
	/**
	 * Transform option message.
	 */
	public void transformOptionMessage(){
		System.out.print("Please choose the file type for the translation¡G\n");
		System.out.print("XML - [X]\n");
		System.out.print("JSON - [J]\n");
	}
	
	/**
	 * Transform option.
	 */
	public void transformOption() {
		Scanner scanner = new Scanner(System.in);
		transformOption = scanner.nextLine();
		
		if(transformOption.toUpperCase().equals("X") || transformOption.toUpperCase().equals("XML")){
			//System.out.print("X");
			FatTommyFileReader r;
			System.out.println("Please enter the xml file location");
			String xmlFileLocation = scanner.nextLine();
			System.out.println("Please enter the destination file location");
			String jsonFileLocation = scanner.nextLine(); // not used right now
			
			try {
				r = new FatTommyFileReader(xmlFileLocation);
				String xmlContent = r.readWholeFile();
				System.out.println("xml content: "+ xmlContent);
				XmlToNode xmlToNode = new XmlToNode();
				Node root = xmlToNode.Translate(xmlContent);
				System.out.println("root: " + root);
				XMLWriter xmlWriter = new XMLWriter();
				xmlWriter.writeXML(root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (transformOption.toUpperCase().equals("J")|| transformOption.toUpperCase().equals("JSON")){
			//System.out.print("J");
			FatTommyFileReader r;
			System.out.println("Please enter the json file location");
			String jsonFileLocation = scanner.nextLine();
			System.out.println("Please enter the xml file location");
			String xmlFileLocation = scanner.nextLine(); // not used right now
			
			try {
				r = new FatTommyFileReader(jsonFileLocation);
				String jsonContent = r.readWholeFile();
				//System.out.println("json content: "+ jsonContent);
				JSONParser parser = new JSONParser(jsonContent);
				Node root = parser.parse();
				//System.out.println("-------- root: -------" + root);
				NodeToXml nodeToXML = new NodeToXml();
				nodeToXML.outputXMLFile(root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.print("Please choose the translation option either XML or JSON !");
			transformOption=null;
		}
		scanner.close();
	}
	
	public static void main(String [] args){
		Console console = new Console();
		console.welcomeMessage();
		console.transformOptionMessage();
		console.transformOption();
	}
}
