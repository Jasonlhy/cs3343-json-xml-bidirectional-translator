package json.testcase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import json.NodeToJSON;

import org.junit.Test;

import component.*;

public class TestNodeToJSON {

	@Test
	public void testNodeToJSON() {
		Node a = new Node("Name", "Ricky");
		Node b = new Node("Sat.", "Doctor");
		Node c = new Node("Position", "Prof.");
		Node e = new Node("Dept", "CS");
		
		Node[] arr = {a,b,c,e};
		
		Node person = new Node("Person", arr);

		
		Node f = new Node("Name", "CityU");
		Node g = new Node("Address", "KLT");
		
		Node[] arr2 = {f,g};
		Node companyInfoNode = new Node("Info",arr2);
		
		
		Node[] all = {companyInfoNode,person,person,person};
		Node company = new Node("Company",all);
		
		System.out.println(NodeToJSON.toJSONString(company));
	}

}
