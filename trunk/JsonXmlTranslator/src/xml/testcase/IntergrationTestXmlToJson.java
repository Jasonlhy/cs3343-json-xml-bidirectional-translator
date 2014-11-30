package xml.testcase;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import xml.XMLParser;
import component.Node;
import json.NodeToJSON;

public class IntergrationTestXmlToJson extends TestCase{
	
	@Test
	public void test1()
	{
		String testString = "<student><name>Tommy</name></student>";
		XMLParser test = new XMLParser();
		try {
			assertEquals("{\n\"student\": {\n\"name\": \"Tommy\"\n}\n}",NodeToJSON.toJSONString(test.Translate(testString)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2()
	{
		String testString = "<student></student>";
		XMLParser test = new XMLParser();
		try {
			assertEquals("{\n\"student\": \"\"\n}",NodeToJSON.toJSONString(test.Translate(testString)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3()
	{
		String testString = "<student>Tommy</student>";
		XMLParser test = new XMLParser();
		try {
			assertEquals("{\n\"student\": \"Tommy\"\n}",NodeToJSON.toJSONString(test.Translate(testString)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test4()
	{
		String testString = "<student><a><name>Tommy</name></a></student>";
		XMLParser test = new XMLParser();
		try {
			assertEquals("{\n\"student\": {\n\"a\": {\n\"name\": \"Tommy\"\n}\n}\n}",NodeToJSON.toJSONString(test.Translate(testString)));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	@Test
	public void test5()
	{
		String testString = "<student><a><name>Tommy</name></a><b><name>Tummy</name></u></student>";
		XMLParser test = new XMLParser();
		try {
			assertEquals("{\n\"student\": {\n\"a\": {\n\"name\": \"Tommy\"\n},\n\"b\": {\n\"name\": \"Tummy\"\n}\n}\n}",NodeToJSON.toJSONString(test.Translate(testString)));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	@Test
	public void test6()
	{
		String testString = "<student><a><name>Tommy</name><weight>200</weight></a></student>";
		XMLParser test = new XMLParser();
		try {
			assertEquals("{\n\"student\": {\n\"a\": {\n\"name\": \"Tommy\",\n\"weight\": \"200\"\n}\n}\n}",NodeToJSON.toJSONString(test.Translate(testString)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
