package com.raulavila.triangle.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class NodeTest {

	@Test
	public void testNodeCreation() {
		Node node = Nodes.createNode(5);
		assertNotNull(node);
		assertTrue(node.isLeaf());
		assertEquals(node.getValue(), 5);
		
		node.setValue(10);
		assertEquals(node.getValue(), 10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIncorrectNodeCreation() {
		Node node = Nodes.createNode(-5);
	}
	
	@Test
	public void testNodesLevelCreation() {
		Node node1 = Nodes.createNode(1);
		Node node2 = Nodes.createNode(2);
		Node node3 = Nodes.createNode(3);
		
		assertNull(node1.getNextInLevel());
		assertNull(node1.getPreviousInLevel());
		assertNull(node2.getNextInLevel());
		assertNull(node2.getPreviousInLevel());
		assertNull(node3.getNextInLevel());
		assertNull(node3.getPreviousInLevel());
		
		//node1 <-> node2 <-> node3
		
		node1.setNextInLevel(node2);
		node2.setNextInLevel(node3);
		
		node2.setPreviousInLevel(node1);
		node3.setPreviousInLevel(node2);
		
		assertEquals(node2.getPreviousInLevel(), node1);
		assertEquals(node3.getPreviousInLevel(), node2);
		
		assertEquals(node1.getNextInLevel(), node2);
		assertEquals(node2.getNextInLevel(), node3);
		
		assertEquals(Nodes.getLeftMostInLevel(node3), node1);
	}
	
	@Test
	public void testNodesChildrenCreation() {
		Node node1 = Nodes.createNode(1);
		Node node2 = Nodes.createNode(2);
		Node node3 = Nodes.createNode(3);
		
		assertEquals(node1.getChildrenNumber(), 0);
		
		node1.addChildNode(node2);
		node1.addChildNode(node3);
		
		assertEquals(node1.getChildrenNumber(), 2);
		assertEquals(node1.getChildrenNodes(), Arrays.asList(node2, node3));
		
		node1.removeChild(1);
		assertEquals(node1.getChildrenNumber(), 1);
		assertEquals(node1.getChildrenNodes(), Arrays.asList(node2));
		
		assertEquals(node1.getChild(0), node2);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testNodesIncorrectChildrenCreation() {
		Node node1 = Nodes.createNode(1);
		Node node2 = Nodes.createNode(2);
		Node node3 = Nodes.createNode(3);
		Node node4 = Nodes.createNode(3);
		
		node1.addChildNode(node2);
		node1.addChildNode(node3);
		node1.addChildNode(node4);
	}
	
	@Test
	public void testPath() {
		Node node = Nodes.createNode(1);
		node.addToMinimalPath(Arrays.asList(2L,3L));
		
		assertEquals(node.getMinimalPath(), Arrays.asList(1L,2L,3L));
	
	}

	
}
