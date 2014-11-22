package component;

import java.util.*;

/**
 * This node is a class for storing DOM object. This is the base class for
 * representing Node structure in order to translate DOM to another format
 * 
 * <p>
 * Node can store another child node or just a content string. There is a list
 * and a content string inside this class, The other one is deleted once it is
 * being set.
 * <p>
 * 
 * We can use hasChildNode() to check whatever the node is storing String
 * content or a list of child node.
 * 
 * @author Chan Chi Hang (52641937) (Give me a call if you don't understand :-))
 */
public class Node{
	private String title;
	private String content;
	private List<Node> nodeList;
	private Node nextNode = null;

	/**
	 * Create a new empty node
	 * 
	 * @param title
	 *            Key name of the Node
	 */
	public Node(String title) {
		this.title = title;
	}

	/**
	 * Create a new Node with filling list of Node
	 * 
	 * @param title
	 *            Key name of the Node
	 * @param list
	 *            List of all the child Node
	 */
	public Node(String title, List<Node> list) {
		this.title = title;
		
		for (int i=0;i<list.size()-1;i++)
			list.get(i).nextNode = list.get(i+1);
		
		this.nodeList = list;
	}

	public Node(String title, Node[] list) {
		this.title = title;
		this.nodeList = new ArrayList<Node>();
		for (int i=0;i<list.length;i++) {
			if (i>0)
				list[i].nextNode = nodeList.get(nodeList.size()-1);
			this.nodeList.add(list[i]);
		}
		
	}
	
	/**
	 * Create a new Node with filling just one child Node
	 * 
	 * @param title
	 *            Key name of the Node
	 * @param node
	 *            A child Node binding with this node
	 */
	public Node(String title, Node node) {
		this.title = title;
		nodeList = new ArrayList<Node>();
		nodeList.add(node);
	}

	/**
	 * Create a new Node with filling string content
	 * 
	 * @param title
	 *            Key name of the Node
	 * @param content
	 *            String content
	 */
	public Node(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public Node() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return title key name of the Node
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get the reference of next Node <b>May return null</b>
	 * @return Node
	 */
	public Node getNextNode() {
		return nextNode;
	}
	
	/**
	 * Get the Content string of the Node
	 * <p>
	 * 
	 * <b>Note:</b> null is returned if the node is storing child node as
	 * content.<br>
	 * 
	 * Please use hasChildNode() to check what type of content the node is
	 * storing
	 * 
	 * @return Content string of the node
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Get all the child nodes of this Node as an List
	 * <p>
	 * 
	 * <b>Note:</b> null is returned if the node is storing content string as
	 * content.<br>
	 * 
	 * Please use hasChildNode() to check what type of content the node is
	 * storing
	 * 
	 * @return List of all the childNode of this Node
	 */
	public List<Node> getChildNodes() {
		return nodeList;
	}

	/**
	 * Get a specific child node of this node
	 * <p>
	 * 
	 * <b>Note:</b> null is returned if the node is storing content string as
	 * content. <br>
	 * Null is also returned if the index is out of bound.
	 * <p>
	 * 
	 * Please use hasChildNode() / getChildNodeLength() to prevent null
	 * 
	 * @param index
	 *            Zero-based index of the child node list
	 * @return A node
	 */
	public Node getChildNode(int index) {
		Node node = ((nodeList == null) || (index >= nodeList.size())) ? null
				: nodeList.get(index);

		return node;
	}

	/**
	 * Get the length of the child node list.
	 * <p>
	 * 
	 * <b>Note:</b> 0 may not mean the child list is empty. When the Node is
	 * storing content string instead of chidl node, 0 may present.
	 * <p>
	 * Please use hasChildNode() to solve the above issue.
	 * 
	 * @return The number of how many child node inside this node
	 */
	public int getChildNodeLength() {
		int length = (nodeList == null) ? 0 : nodeList.size();

		return length;
	}

	/**
	 * Search the Child Node of this node using the title key of the child node
	 * and return all the child node which match the key
	 * 
	 * @param title
	 *            key of the child node
	 * @return An array with all the node with the same title
	 */
	public Node[] getChildNode(String title) {
		List<Node> result = new ArrayList<Node>();
		for (Node n : nodeList)
			if (n.getTitle().equals(title))
				result.add(n);

		return result.toArray(new Node[result.size()]);
	}

	/**
	 * 
	 * @param key
	 * @return 
	 */
	public List<Node> search(String key) {
		return this.searchRecursively(this,key, new ArrayList<Node>());
	}
	
	private List<Node> searchRecursively(Node n,String key, List<Node> result) {
		
		if (n.title.equals(key))
			result.add(n);
		
		for (int i=0;i<n.getChildNodeLength();i++)
			result = n.getChildNode(i).searchRecursively(n.getChildNode(i), key, result);
		
		return result;
	}
	
	
	/**
	 * set title of this node
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set content string of this Node
	 * <p>
	 * <b>Note: </b> Either Content String or Child Node Storing type is allow.
	 * When this method is called. The content string of this node (if contain)
	 * will be deleted.
	 * 
	 * @param list
	 *            An List of all the child node
	 */
	public void replaceNodes(List<Node> list) {
		nodeList = list;
		content = null;
	}

	/**
	 * Add a new node to the child node list <b>Note: </b> Either Content String
	 * or Child Node Storing type is allow. When this method is called. The
	 * content string of this node (if contain) will be deleted.
	 * 
	 * @param node
	 */
	public void addNode(Node node) {
		if (nodeList == null)
			nodeList = new ArrayList<Node>();

		if (nodeList.size()>1)
			nodeList.get(nodeList.size()-1).nextNode = node;
		nodeList.add(node);
		content = null;
	}

	/**
	 * Set content string of this Node
	 * <p>
	 * <b>Note: </b> Either Content String or Child Node Storing type is allow.
	 * When this method is called. The child node list of this node (if contain)
	 * will be deleted.
	 * 
	 * @param content
	 *            Content String
	 */
	public void setContent(String content) {
		this.content = content;
		nodeList = null;
	}

	/**
	 * Can tell if the storing type of this node is using Content String or
	 * child node.
	 * <p>
	 * The node class can only store either Content String or Child Node. Use
	 * this function to see which type of storage this node is using
	 * 
	 * @return boolean of havingCHildNode
	 */
	public boolean hasChildNode() {
		return nodeList != null;
	}

	/**
	 * Show the node for with key-value so that we can test output node from the
	 * parser, e.g. root: { id : 19 }, This DOES NOT equals to the JSON
	 * notation.
	 * 
	 * @author Jason
	 * @since 6-10-2014
	 */
	@Override
	public String toString() {
		String s = "";
		if (hasChildNode()) {
			for (int i = 0; i < nodeList.size(); i++) {
				s += (i == 0) ? nodeList.get(i) : ", " + nodeList.get(i);
			}
			s = title + ": { " + s + " }";
		} else {
			s = title + " : " + content;
		}

		return s;
	}

	
	@Override
	/**
	 * Override euqlas method to check equals of different object. (Same object but diff reference)
	 * @return boolean of if it is equals
	 */
	public boolean equals(Object obj) {
		boolean sameReference = super.equals(obj);
		if (sameReference){
			return true;
		}
		
		// same toString but diff obj
		if (obj instanceof Node){
			Node node = (Node) obj;
			if (node != null){
				return this.toString().equals(node.toString());
			}
		}
			
		return false;
	}
	
	
}
