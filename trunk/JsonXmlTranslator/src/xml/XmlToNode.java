package xml;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import component.Node;

public class XmlToNode {
	Stack<String> follower = new Stack<String>();
	
	
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
		if(!mostLeftisNode(xmlString.toString()))
		{
			//System.out.println("May be String value");
			return null;
			//exception
		}
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
						//System.out.println("Finish One Node");
						follower.pop();
						xmlString.delete(0, nodeEnd);
						break;
					}
				}
				//xmlString.delete(0, nodeEnd);
				//System.err.println("one round: "+xmlString);
			}
		}
		return currentNode;
	}
	//Get the first match "<...>" pattern and cut the string as s1 = <...> and s2 = ...<...>... remain string
	public String divideToTwo(String xmlString)
	{
		return "";
	}
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
	
}