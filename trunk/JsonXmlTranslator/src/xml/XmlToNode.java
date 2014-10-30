package xml;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import component.Node;

public class XmlToNode {
	
	
	public Node Translate(String xmlString)
	{
		StringBuilder xml = new StringBuilder();
		xml.append(xmlString);
		if(!mostLeftisNode(xml.toString()))
			return null;
		Node newNode = new Node();
		return TranslateToNode(xml, newNode);
	}
	
	public Node TranslateToNode(StringBuilder xmlString, Node currentNode)
	{
		if(!mostLeftisNode(xmlString.toString()))
		{
			//currentNode.setContent(getValue(xmlString.toString()));
			return null;
		}
		//The most left string is node. Therefore, we divide the string to node string and other xml string.
		//divide string into two
		String nodeString;
		
		while(true)
		{
			int nodeEnd = NodeStartAndEnd(xmlString.toString())[1];
			nodeString = xmlString.substring(0,nodeEnd+1);
			//remainder = xmlString.substring(nodeEnd+1);
			xmlString.delete(0, nodeEnd+1);
			if(TAG.OPEN.isValid(nodeString)) //OPEN Node confirmed. 
			{
				Node newNode = new Node();
				currentNode.setTitle(getNodename(nodeString));
				Node childNode = TranslateToNode(xmlString, newNode);
				if(childNode == null)
				{
					currentNode.setContent(getValue(xmlString));
				}
				else
				{
					currentNode.addNode(childNode);
				}
				if(!mostLeftisNode(xmlString.toString()))
				{
					System.out.println("Fail, invalid format");
					//exception
				}
				nodeEnd = NodeStartAndEnd(xmlString.toString())[1];
				nodeString = xmlString.substring(0, nodeEnd+1);
				if(!TAG.CLOSE.isValid(nodeString))
				{
					System.out.println("Fail, invaild format");
					//exception
				}
				xmlString.delete(0, nodeEnd+1);
			}
			if(TAG.CLOSE.isValid(nodeString))
			{
				return currentNode;
				
			}
		}
	}
	//Get the first match "<...>" pattern and cut the string as s1 = <...> and s2 = ...<...>... remain string
	public String divideToTwo(String xmlString)
	{
		return "";
	}
	public boolean mostLeftisNode(String xmlString)
	{
		String regex = "<?\\w*>?.*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(xmlString);
		if(matcher.find() && matcher.start(0) == 0)
			return true;
		return false;
	}
	
	public String getNodename(String nodeString)
	{
		return nodeString.substring(1, nodeString.length()-1);
	}
	
	public String getValue(StringBuilder xmlString)
	{
		/*String regex = "<?\\w*>?.*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(nodeString);
		int matchStart = matcher.start(0);*/
		int matchStart = NodeStartAndEnd(xmlString.toString())[0];
		String result = xmlString.substring(0, matchStart+1);
		xmlString.delete(0, matchStart+1);
		return result;
	}
	
	public int[] NodeStartAndEnd(String xmlString)
	{
		String regex = "<?\\w*>?.*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(xmlString);
		int[] match = new int[2];
		match[0] = matcher.start(0);
		match[1] = matcher.end(0);
		return match;
		
	}
	
}