package com.raulavila.triangle.utils;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.raulavila.triangle.model.Node;

public class TrianglePathFinder {
	private final Node rootNode;
	
	private Long minimalSum;
	private List<Long> minimalPath;

	private TrianglePathFinder(Node rootNode) {
		this.rootNode = rootNode;
		minimalSum = Long.MAX_VALUE;
		minimalPath = null;
	}
	
	public static TrianglePathFinder newInstance(Node rootNode) {
		if(rootNode == null)
			throw new NullPointerException("rootNode can't be null");
		
		return new TrianglePathFinder(rootNode);
	}
	
	public List<Long> getMinimalPath() {
		if(minimalPath == null)
			calculateMinimalPath();
		
		return Collections.unmodifiableList(minimalPath);
	}
	
	public Long getMinimalSum() {
		if(minimalSum == Long.MAX_VALUE)
			calculateMinimalPath();
		
		return minimalSum;
	}

	private void calculateMinimalPath() {

		Stack<Node> firstNodesInLevel = getFirstInLevelStack();

		//Starting from the level previous to last, let's prune the tree...
		while(!firstNodesInLevel.isEmpty()) {
			Node firstInLevel = firstNodesInLevel.pop();
			
			Node node = firstInLevel;
			
			//Remove the max child
			while(node!= null) {
				removeMaxChild(node);
				node = node.getNextInLevel();
			}
			
			node = firstInLevel;
			
			//Now let's remove the child left, keeping the sum in the current node, along with the path
			while(node!= null) {
				Node child = node.getChild(0);
				
				long value = node.getValue();
				long childValue = child.getValue();
				
				node.setValue(value + childValue);
				
				node.addToMinimalPath(child.getMinimalPath());
				
				node.removeChild(0);  //prune the only child left, we've got everything we need in this node
				
				node = node.getNextInLevel();
			}
			
		}
		
		//We have finished, we've got all the info we need in the root node
		minimalPath = rootNode.getMinimalPath();
		minimalSum = rootNode.getValue();
		
	}
	
	//Get stack of left most nodes in every level except last one
	private Stack<Node> getFirstInLevelStack() {
		Stack<Node> firstNodesInLevel = new Stack<Node>();
		
		Node node = rootNode;
		
		while(!node.isLeaf()) {
			firstNodesInLevel.push(node);
			node = node.getChild(0);
		}
		
		return firstNodesInLevel;
	}
	
	//Assuming the children of this node are leafs, remove the node with the highest value (prune the tree)
	private void removeMaxChild(Node node) {
		Node left = node.getChild(0);
		Node right = node.getChild(1);
		
		if(left.getValue() < right.getValue())
			node.removeChild(1);   //remove right child
		else
			node.removeChild(0);	//remove left child
	}	
	
}
