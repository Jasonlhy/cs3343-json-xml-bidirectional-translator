package console;

import java.util.Scanner;

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
		transformOption = scanner.next();
		if(transformOption.equals("X") || transformOption.equals("XML"))
			System.out.print("X");
		else if (transformOption.equals("J")|| transformOption.equals("JSON"))
			System.out.print("J");
		else{
			System.out.print("Please choose the translation option either XML or JSON !");
			transformOption=null;
		}
	}
}
