package xml;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import component.Node;

public class XMLParser {
	Stack<String> follower = new Stack<String>();
	
	
	public Node Translate(String xmlString) throws Exception
	{
		Node result = null;
		try{
		if(!mostLeftisNode(xmlString.toString()))
		{
			throw new Exception("Format Exception");
		}
		StringBuilder xml = new StringBuilder();
		xml.append(xmlString);
		Node newNode = new Node();
		result = TranslateToNode(xml, newNode);
		return result;
		}
		catch (Exception e)
		{
			System.out.println("Wrong XML Format");
			//throw e;
			return null;
		}
		
		
		//return result;
	}
	
	public Node TranslateToNode(StringBuilder xmlString, Node currentNode) throws Exception
	{
		if(!mostLeftisNode(xmlString.toString()))
		{
			//currentNode.setContent(getValue(xmlString.toString()));
			return null;
		}
		//The most left string is node. Therefore, we divide the string to node string and other xml string.
		//divide string into two
		String nodeString;
		int nodeEnd = NodeStartAndEnd(xmlString.toString())[1];
		//System.err.println(nodeEnd);
		nodeString = xmlString.substring(0,nodeEnd);
		if(TAG.CLOSE.isValid(nodeString))
		{
			if(currentNode.getTitle() == null)
				return null;
			return currentNode;
			
		}
		//remainder = xmlString.substring(nodeEnd+1);
		xmlString.delete(0, nodeEnd);
		//System.err.println(xmlString);
		currentNode.setTitle(getNodename(nodeString));
		follower.push(currentNode.getTitle());
		//System.err.println(follower.peek());
		while(true)
		{
			if(TAG.OPEN.isValid(nodeString)) //OPEN Node confirmed. 
			{
				Node newNode = new Node();
				Node childNode = TranslateToNode(xmlString, newNode);
				if(childNode == null)
				{
					currentNode.setContent(getValue(xmlString));
				}
				else
				{
					currentNode.addNode(childNode);
				}
				nodeEnd = NodeStartAndEnd(xmlString.toString())[1];
				nodeString = xmlString.substring(0, nodeEnd);
				if(TAG.CLOSE.isValid(nodeString))
				{
					
					if(follower.peek().equals(currentNode.getTitle()))
					{
						if(!getNodename(nodeString).replace("/", "").equals(currentNode.getTitle()))
						{
							throw new Exception("Wrong Format");
						}
						//System.out.println("Finish One Node");
						follower.pop();
						xmlString.delete(0, nodeEnd);
						break;
					}
				}
			}
			else if(TAG.SHORT.isValid(nodeString))
			{
				currentNode.addNode(getShortNameAndAttribute(nodeString));
				xmlString.delete(0, nodeEnd);
				break;
			}
			else
			{
				throw new Exception();
			}
			
			//}
		}
		return currentNode;
	}
	//Get the first match "<...>" pattern and cut the string as s1 = <...> and s2 = ...<...>... remain string
	/*public String divideToTwo(String xmlString)
	{
		return "";
	}*/
	public boolean mostLeftisNode(String xmlString)
	{
		String regex = "<[^<>]+>";
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
		String result = xmlString.substring(0, matchStart);
		xmlString.delete(0, matchStart);
		return result;
	}
	
	public int[] NodeStartAndEnd(String xmlString)
	{
		String regex = "<[^<>]+>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(xmlString);
		int[] match = new int[2];
		matcher.find();
		match[0] = matcher.start(0);
		match[1] = matcher.end(0);
		return match;
		
	}
	
	public Node getShortNameAndAttribute(String nodeString)
	{
		Node shortNode = new Node();
		int index = nodeString.indexOf(' ');
		shortNode.setTitle(nodeString.substring(1, index));
		nodeString = nodeString.substring(index+1, nodeString.length()-1);
		while(true)
		{
			index = nodeString.indexOf(' ');
			String attriString;
			if(index != -1)
				attriString = nodeString.substring(0, index);
			else
			{
				index = nodeString.indexOf('/');
				attriString = nodeString.substring(0, index);
			}
			nodeString = nodeString.substring(index+1, nodeString.length());
			if(nodeString.equals(""))
				break;
			Node attributeNode = getAttribute(attriString);
			shortNode.addNode(attributeNode);
		}
		return shortNode;
	}
	
	public Node getAttribute(String attriString)
	{
		Node attribute = new Node();
		int index = attriString.indexOf('=');
		//System.err.println(index);
		attribute.setTitle(attriString.substring(0, index));
		attriString = attriString.substring(index+2, attriString.length());
		index = attriString.indexOf('"');
		attribute.setContent(attriString.substring(0, index));
		return attribute;
	}
	
	
	
}