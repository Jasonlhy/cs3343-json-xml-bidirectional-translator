package console;

import java.io.IOException;
import java.util.Scanner;

import component.JSONParser;
import component.Node;
import component.XMLWriter;
import utility.io.FatTommyFileReader;
import xml.NodeToXml;
import xml.XmlToNode;

public class Console {
	String transformOption;
	public Console(){
		
	}
	
	public void welcomeMessage(){
		System.out.println("*** Welcome to the XML & JSON Translator ***");
		System.out.println();
	}
	
	public String getTransformOption(){
		return transformOption;
	}
	
	public void transformOptionMessage(){
		System.out.println("Please choose the file type for the translation¡G");
		System.out.println("XML - [X]");
		System.out.println("JSON - [J]");
	}
	
	public void transformOption() {
		Scanner scanner = new Scanner(System.in);
		transformOption = scanner.nextLine();
		
		
		if(transformOption.equals("X") || transformOption.equals("XML")){
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
		}
		else if (transformOption.equals("J")|| transformOption.equals("JSON")){
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
