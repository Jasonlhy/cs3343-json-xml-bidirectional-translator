package component;

import utility.log.CustomLog;

public class Translator {
	public static void main(String args[]){
		System.out.println("Hello everyone, I am the translator.");
		System.out.println("Hello, Jason testing");
		System.out.println("Hello, Winson testing");
		System.out.println("Hello, Chihang testing");
		System.out.println("Hello, Thomas testing");
		
		//Closing log file
		CustomLog.getInstance().closeFile();
	}
}
