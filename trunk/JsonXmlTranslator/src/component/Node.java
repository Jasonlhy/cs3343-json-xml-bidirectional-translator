package component;
import java.util.ArrayList;

/**
 * This node is a class for storing DOM object.
 * This is the base class for representing Node structure in order
 * to translate DOM to another format
 * 
 * <p>
 * Node can store another child node or just a content string.
 * There is a list and a content string  inside this class, 
 * The other one is deleted once it is being set. <p>
 * 
 * We can use hasChildNode() to check whatever the node is storing 
 * String content or a list of child node.
 * 
 * @author Chan Chi Hang (52641937)
 * (Give me a call if you don't understand :-))
 */
public class Node {
	private String _title;
	private String _content;
	private ArrayList<Node> _nodeList;
	private boolean hasChildNode = false;
	
	/**
	 * Create a new empty node
	 * @param title Key name of the Node
	 */
	public Node(String title) {
		_title = title;
	}
	
	/**
	 * Create a new Node with filling list of Node
	 * @param title Key name of the Node
	 * @param list ArrayList of all the child Node
	 */
	public Node(String title, ArrayList<Node> list) {
		_title = title;
		_nodeList = list;
		hasChildNode = true;
	}
	
	/**
	 * Create a new Node with filling just one child Node
	 * @param title Key name of the Node
	 * @param node A child Node binding with this node
	 */
	public Node(String title, Node node) {
		_title = title;
		_nodeList = new ArrayList<Node>();
		_nodeList.add(node);
		hasChildNode = true;
	}
	
	/**
	 * Create a new Node with filling string content
	 * @param title Key name of the Node
	 * @param content String content
	 */
	public Node(String title, String content) {
		_title = title;
		_content = content;
		hasChildNode = false;
	}
	
	/**
	 * @return title key name of the Node
	 */
	public String getTitle() {
		return _title;
	}
	
	/**
	 * Get the Content string of the Node <p>
	 * 
	 * <b>Note:</b> null is returned if the node is storing child node as content.<br>
	 * 
	 * Please use hasChildNode() to check what type of content the node is storing
	 * @return Content string of the node
	 */
	public String getContent() {
		return _content;
	}
	
	/**
	 * Get all the child nodes of this Node as an ArrayList<p>
	 * 
	 * <b>Note:</b> null is returned if the node is storing content string as content.<br>
	 * 
	 * Please use hasChildNode() to check what type of content the node is storing
	 * @return ArrayList of all the childNode of this Node
	 */	
	public ArrayList<Node> getChildNodes() {
		return _nodeList;
	}
	
	/**
	 * Get a specific child node of this node<p>
	 * 
	 * <b>Note:</b> null is returned if the node is storing content string as content. <br>
	 * Null is also returned if the index is out of bound. <p>
	 * 
	 * Please use hasChildNode() / getChildNodeLength() to prevent null
	 * @param index Zero-based index of the child node list
	 * @return A node
	 */
	public Node getChildNode(int index) {
		if (_nodeList==null || _nodeList.size()<index+1)
			return null;
		else
			return _nodeList.get(index);
	}
	
	/**
	 * Get the length of the child node list. <p>
	 * 
	 * <b>Note:</b> 0 may not mean the child list is empty. 
	 * When the Node is storing content string instead of chidl node, 
	 * 0 may present. <p>
	 * Please use hasChildNode() to solve the above issue.
	 * @return The number of how many child node inside this node
	 */
	public int getChildNodeLength() {
		if (_nodeList!=null)
			return _nodeList.size();
		return 0; 
	}
	
	/**
	 * Search the Child Node of this node using the 
	 * title key of the child node and return all the child node which match the key
	 * 
	 * @param title key of the child node
	 * @return An array with all the node with the same title 
	 */
	public Node[] getChildNode(String title) {
		ArrayList<Node> result = new ArrayList<Node>();
		for (Node n:_nodeList)
			if (n.getTitle().equals(title))
				result.add(n);
		
		return result.toArray(new Node[result.size()]);
	}
	
	/**
	 * set title of this node
	 * @param title
	 */
	public void setTitle(String title) {
		_title = title;
	}
	
	/**
	 * Set content string of this Node <p>
	 * <b>Note: </b> Either Content String or Child Node Storing type is
	 * allow. When this method is called. The content string of this node
	 *  (if contain) will be deleted.
	 * @param list An Arraylist of all the child node
	 */
	public void replaceNodes(ArrayList<Node> list) {
		_nodeList = list;
		_content = null;
		hasChildNode = true;
	}
	
	/**
	 * Add a new node to the child node list
	 * <b>Note: </b> Either Content String or Child Node Storing type is
	 * allow. When this method is called. The content string of this node
	 *  (if contain) will be deleted. 
	 * @param node
	 */
	public void addNode(Node node) {
		if (_nodeList==null)
			_nodeList = new ArrayList<Node>();
		_nodeList.add(node);
		_content = null;
		hasChildNode = true;
	}
	
	/**
	 * Set content string of this Node <p>
	 * <b>Note: </b> Either Content String or Child Node Storing type is
	 * allow. When this method is called. The child node list of this node 
	 * (if contain) will be deleted.
	 * @param content Content String
	 */
	public void setContent(String content) {
		_content = content;
		_nodeList = null;
		hasChildNode = false;
	}
	
	/**
	 * Can tell if the storing type of this node is using Content String or 
	 * child node.
	 * <p>
	 * The node class can only store either Content String or Child Node.
	 * Use this function to see which type of storage this node is using 
	 * 
	 * @return boolean of havingCHildNode
	 */
	public boolean hasChildNode() {
		return hasChildNode;
	}
}
