/*
 * This class is used for the Console interface controlling.
 */
package console;

import java.io.IOException;
import java.util.*;

import json.JSONParser;
import component.Node;
import utility.io.FatTommyFileReader;
import utility.io.FatTommyFileWriter;
import xml.NodeToXml;
import xml.XMLWriter;
import xml.XmlToNode;
import json.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Console.
 */
public class Console {
	
	/** The transform option. */
	String transformOption="X";
	
	static String transformedOutput="";
	
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
	 * Gets the transformed output.
	 * 
	 * @return the transformed output
	 */
	public String getTransformedOutput(){
		return transformedOutput;
	}
	
	/**
	 * Transform option message.
	 */
	public void transformOptionMessage(){
		System.out.print("Please choose the file type for the translation.\n");
		System.out.print("XML - [X]\n");
		System.out.print("JSON - [J]\n");
	}
	
	/**
	 * Transform option handling.
	 */
	public void transformOption() {
		Scanner scanner = new Scanner(System.in);
		transformOption = scanner.nextLine();
		
		if(transformOption.toUpperCase().equals("X") || transformOption.toUpperCase().equals("XML")){
			//System.out.print("X");
			System.out.println("Please enter the xml file location");
			String xmlFileLocation = scanner.nextLine();
			System.out.println("Please enter the destination file location");
			String jsonFileLocation = scanner.nextLine(); // not used right now

			String xmlContent = getFileContent(xmlFileLocation);
			try {
				transformOptionXMLtoJSON(xmlContent);
				
				setFileOutput(jsonFileLocation,transformOptionXMLtoJSON(xmlContent));
				
				System.out.println("Transform the XML to JSON successful.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (transformOption.toUpperCase().equals("J")|| transformOption.toUpperCase().equals("JSON")){
			//System.out.print("J");
			System.out.println("Please enter the json file location");
			String jsonFileLocation = scanner.nextLine();
			System.out.println("Please enter the xml file location");
			String xmlFileLocation = scanner.nextLine(); // not used right now
			
			String jsonContent = getFileContent(jsonFileLocation);
			try {
				transformOptionJSONtoXML(jsonContent);
				
				setFileOutput(xmlFileLocation,transformOptionJSONtoXML(jsonContent));
				
				System.out.println("Transform the JSON to XML successful.");
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
	
	/**
	 * Sets the file output.
	 * 
	 * @param path
	 *            the path
	 * @param content
	 *            the content
	 * @throws IOException 
	 */
	public void setFileOutput(String path,List content) throws IOException{
		FatTommyFileWriter w;
		w = new FatTommyFileWriter(path);
		w.WriteToFile(content);
	}
	
	/**
	 * Gets the file content.
	 * 
	 * @param path
	 *            the path
	 * @return the file content
	 */
	public String getFileContent(String path){
		FatTommyFileReader r;
		r = new FatTommyFileReader(path);
		String content="";
		try {
			content = r.readWholeFile();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * Check transform option byfile.
	 * 
	 * @param path
	 *            the path
	 * @return the string
	 */
	public String checkTransformOptionByfile(String path){
		String content = getFileContent(path);
		char contentType = content.charAt(0);
		if(contentType=='{'){
			return "JSONtoXML";
		}else if(contentType=='<'){
			return "XMLtoJSON";
		}else{
			return "false";
		}
	}

	/**
	 * Transform option (JSON to XML) handling.
	 * 
	 * @param jsonContent
	 *            the json content
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static List<String> transformOptionJSONtoXML(String jsonContent) throws IOException {
		JSONParser parser = new JSONParser(jsonContent);
		Node root = parser.parse();
		//System.out.println("-------- root: -------" + root);
		NodeToXml nodeToXML = new NodeToXml();
		//nodeToXML.outputXMLFile(root);
		
		transformedOutput=nodeToXML.outputXMLFile(root);
		
		System.out.println("Transform the JSON to XML successful.");
		
		List<String> result=new ArrayList<String>();
		result.add(nodeToXML.outputXMLFile(root));
		
		return result;
		//return nodeToXML.outputXMLFile(root);
	}
	
	/**
	 * Transform option (XML to JSON) handling.
	 * 
	 * @param xmlContent
	 *            the xml content
	 * @return 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static List<String> transformOptionXMLtoJSON(String xmlContent) throws IOException {
		XmlToNode xmlToNode = new XmlToNode();
		Node root = xmlToNode.Translate(xmlContent);
		//System.out.println("root: " + root);
		NodeToJSON toJson = new NodeToJSON();
		
		transformedOutput=toJson.toJSONString(root);
		
		System.out.println("Transform the XML to JSON successful.");
				
		List<String> output = new ArrayList<String>();
		output.add(toJson.toJSONString(root));
		
		return output;
	}
	
	/**
	 * Input arguments error.
	 */
	public void inputArgumentsError(){
		System.out.println("Please input valid arguments:");
		System.out.println("1.	Do not input any argument.");
		System.out.println("2.	\\s [{JSON}|<XML>]");
		System.out.println("3.	\\f inputFilePath outputFilePath");
	}
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String [] args){
		Console console = new Console();
		console.welcomeMessage();
		
		if(args.length==0){
			console.transformOptionMessage();
			console.transformOption();
		}else if(args.length==2){
			String mode=args[0];
			String inputContent=args[1];
			String convertMode="";
			if(inputContent.replaceAll("\\s+","").charAt(0)=='{'){
				convertMode="JSONtoXML";
			}else if(inputContent.replaceAll("\\s+","").charAt(0)=='<'){
				convertMode="XMLtoJSON";
			}
			if("\\S".equals(mode.toUpperCase())){
				if(convertMode=="JSONtoXML"){
					try {
						console.transformOptionJSONtoXML(inputContent);
						System.out.println(console.getTransformedOutput());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(convertMode=="XMLtoJSON"){
					try {
						console.transformOptionXMLtoJSON(inputContent);
						System.out.println(console.getTransformedOutput());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					console.inputArgumentsError();
				}
			}else{
				console.inputArgumentsError();
			}
		}else if(args.length==3){
			String mode=args[0];
			String inputFileLocation=args[1];
			String outputFileLocation=args[2];
			String convertMode=console.checkTransformOptionByfile(inputFileLocation);
			if("\\F".equals(mode.toUpperCase())){
				if(convertMode=="JSONtoXML"){
					try {
						//console.transformOptionJSONtoXML(console.getFileContent(inputFileLocation));
						console.setFileOutput(outputFileLocation, transformOptionJSONtoXML(console.getFileContent(inputFileLocation)));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(convertMode=="XMLtoJSON"){
					try {
						//console.transformOptionXMLtoJSON(console.getFileContent(inputFileLocation));
						console.setFileOutput(outputFileLocation, transformOptionXMLtoJSON(console.getFileContent(inputFileLocation)));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					console.inputArgumentsError();
				}
			}else{
				console.inputArgumentsError();
			}
		}else{
			console.inputArgumentsError();
		}
	}
}
