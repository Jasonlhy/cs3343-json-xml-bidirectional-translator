/*
 * This class is used for the Console interface controlling,
 * control the whole system flows.
 */
package component;

import java.io.IOException;
import java.util.*;

import json.JSONParser;
import utility.StringFileReader;
import utility.StringFileWriter;
import utility.CustomLog;
import xml.NodeToXMLB;
import xml.NodToXMLA;
import xml.XMLParser;
import json.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Console. 
 */
public class Console {
	private static final String UN_SUPPORT_FILE_TYPE = "false";
	private static final String XML_TO_JSON = "XMLtoJSON";
	private static final String JSON_TO_XML = "JSONtoXML";

	/** The transform option. */
	String transformOption="X";
	
	/** The transformed output. */
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
	public static String getTransformedOutput(){
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

			System.out.println("Please enter the xml file location");
			String xmlFileLocation = scanner.nextLine();
			System.out.println("Please enter the destination file location");
			String jsonFileLocation = scanner.nextLine();

			String xmlContent = getFileContent(xmlFileLocation);
			try {
				
				setFileOutput(jsonFileLocation,transformOptionXMLtoJSON(xmlContent));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (transformOption.toUpperCase().equals("J")|| transformOption.toUpperCase().equals("JSON")){

			System.out.println("Please enter the json file location");
			String jsonFileLocation = scanner.nextLine();
			System.out.println("Please enter the xml file location");
			String xmlFileLocation = scanner.nextLine(); 
			
			String jsonContent = getFileContent(jsonFileLocation);
			try {
				
				setFileOutput(xmlFileLocation,transformOptionJSONtoXML(jsonContent));
				
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
	 * @param path            the path
	 * @param content            the content
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void setFileOutput(String path,List content) throws IOException{
		StringFileWriter w;
		w = new StringFileWriter(path);
		w.WriteToFile(content);
	}
	
	/**
	 * Gets the file content.
	 * 
	 * @param path
	 *            input file path
	 * @return the file content
	 */
	public String getFileContent(String path){
		StringFileReader r;
		r = new StringFileReader(path);
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
	 * Check transform option by file content.
	 * 
	 * @param path
	 *            input file path
	 * @return the transform option
	 */
	public String checkTransformOptionByfile(String path){
		String content = getFileContent(path);
		CustomLog.getInstance().info("determine transform: " + path);
		char contentType = content.charAt(0);
		if(contentType=='{'){
			return JSON_TO_XML;
		}else if(contentType=='<'){
			return XML_TO_JSON;
		}else{
			return UN_SUPPORT_FILE_TYPE;
		}
	}

	/**
	 * Transform option (JSON to XML) handling.
	 *
	 * @param jsonContent            String json content
	 * @return the list				String output list
	 * @throws IOException             Signals that an I/O exception has occurred.
	 */
	public static List<String> transformOptionJSONtoXML(String jsonContent) throws IOException {
		Node root = null;
		List<String> result=new ArrayList<String>();
		
		try {
			JSONParser parser = new JSONParser(jsonContent);
			root = parser.parse();
			
			NodeToXMLB nodeToXML = new NodeToXMLB();
			transformedOutput = nodeToXML.outputXMLFile(root);
			
			System.out.print("Transform the JSON to XML successful.\n");
			
		
			result.add(nodeToXML.outputXMLFile(root));
		} catch (JSONParseException ex){
			System.out.println("Transform the JSON to XML failed.\n");
			System.out.println(ex.getMessage());
			CustomLog.getInstance().error(ex);
		}
		

		
		return result;
	}
	
	/**
	 * Transform option (XML to JSON) handling.
	 *
	 * @param xmlContent            String xml content
	 * @return the list				String output list
	 * @throws IOException             Signals that an I/O exception has occurred.
	 */
	public static List<String> transformOptionXMLtoJSON(String xmlContent) throws IOException {
		XMLParser xmlToNode = new XMLParser();
		Node root = xmlToNode.Translate(xmlContent);
		NodeToJSON toJson = new NodeToJSON();
		
		transformedOutput=toJson.toJSONString(root);
		
		System.out.print("Transform the XML to JSON successful.\n");
				
		List<String> output = new ArrayList<String>();
		output.add(toJson.toJSONString(root));
		
		return output;
	}
	
	/**
	 * Input arguments error.
	 */
	public static void inputArgumentsError(){
		System.out.print("Please input valid arguments:\n");
		System.out.print("1.	Do not input any argument.\n");
		System.out.print("2.	\\s [{JSON}|<XML>]\n");
		System.out.print("3.	\\f inputFilePath outputFilePath\n");
	}
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 *            1.	Do not input any argument.
	 *            2.	\s [{JSON}|<XML>]
	 *            3.	\f inputFilePath outputFilePat
	 */
	public static void main(String [] args){
		Console console = new Console();
		console.welcomeMessage();
		
		
		CustomLog.getInstance().info(("args length: " + args.length));
		if(args.length==0){
			console.transformOptionMessage();
			console.transformOption();
		}else if(args.length==2){
			String mode=args[0];
			String inputContent=args[1];
			String convertMode="";
			if(inputContent.replaceAll("\\s+","").charAt(0)=='{'){
				convertMode=JSON_TO_XML;
			}else if(inputContent.replaceAll("\\s+","").charAt(0)=='<'){
				convertMode=XML_TO_JSON;
			}
			
			
			if("\\S".equals(mode.toUpperCase())){
				if(convertMode==JSON_TO_XML){
					try {
						CustomLog.getInstance().info("input content for json parser: " + inputContent);
						console.transformOptionJSONtoXML(inputContent);
						System.out.print(console.getTransformedOutput());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(convertMode==XML_TO_JSON){
					try {
						console.transformOptionXMLtoJSON(inputContent);
						System.out.print(console.getTransformedOutput());
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
			
			CustomLog.getInstance().info("inputFileLocation: " + inputFileLocation);
			if("\\F".equals(mode.toUpperCase())){
				if(convertMode==JSON_TO_XML){
					try {
						console.setFileOutput(outputFileLocation, transformOptionJSONtoXML(console.getFileContent(inputFileLocation)));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(convertMode==XML_TO_JSON){
					try {
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
