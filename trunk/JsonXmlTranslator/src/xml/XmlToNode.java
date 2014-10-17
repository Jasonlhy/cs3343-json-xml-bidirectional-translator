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
	
	public boolean mostLeftisNode(String xmlString)
	{
		return true;
	}
	
	public String getNodename(String xmlnode)
	{
		return "";
	}
	
	public String getValue(String xmlString)
	{
		return "";
	}
	
}