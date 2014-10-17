package xml;
import component.Node;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.*;

import java.io.IOException;
import java.io.StringReader;

public class XmlToNode {
	private String xmlString;
	
	
	public XmlToNode(String xmlString)
	{
		this.xmlString = xmlString;

	}
	
	public Node Translate()
	{
		return null;
	}
	//Get the first match "<...>" pattern and cut the string as s1 = <...> and s2 = ...<...>... remain string
	public String divideToTwo(String xmlString)
	{
		return "";
	}
	public boolean mostLeftisNode(String xmlString)
	{
		return true;
	}
	
	public String getNodename(String nodeString)
	{
		return "";
	}
	
	public String getValue(String nodeString)
	{
		return "";
	}
	
}