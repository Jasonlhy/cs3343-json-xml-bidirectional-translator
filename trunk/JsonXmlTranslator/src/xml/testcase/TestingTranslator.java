package xml.testcase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xml.XMLParser;
import component.Node;

public class TestingTranslator {
	String testString = "<A></A>";
	XMLParser xtranslator = new XMLParser();

	public static void main(String [] args){
		String xmlString = "<abcd>jjj";
		String regex = "<[^<>]+>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(xmlString);
		System.out.println(matcher.find());
		System.out.println(matcher.start());
		System.out.println(matcher.end());
		System.out.println(xmlString.substring(matcher.start(), matcher.end()));
	}
}