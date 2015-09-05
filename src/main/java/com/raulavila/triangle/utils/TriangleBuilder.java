package com.raulavila.triangle.utils;

import com.raulavila.triangle.model.Node;
import com.raulavila.triangle.model.Nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TriangleBuilder {

    private final Node root;

    private Node currentNodeParent;

    private int lastLevelLength;

    private TriangleBuilder(long rootNumber) {
        root = Nodes.createNode(rootNumber);
        currentNodeParent = root;
        lastLevelLength = 1;
    }

    public static TriangleBuilder newInstance(long rootNumber) {
        return new TriangleBuilder(rootNumber);
    }

    public Node getTriangleRoot() {
        return root;
    }

    public void addLevel(List<Long> numbers) {

        if (numbers == null)
            throw new NullPointerException("numbers list can't be null");

        if (numbers.isEmpty())
            throw new IllegalArgumentException("numbers list can't be empty");

        if (numbers.size() != lastLevelLength + 1)
            throw new IllegalArgumentException(
                    String.format("length of number list must be %d (length of previous level + 1)", lastLevelLength));

        if (currentNodeParent == null)
            throw new IllegalStateException("The builder is in an inconsistent state (i.e. root not set...)");


        Node nodePrevious = Nodes.createNode(numbers.get(0));
        currentNodeParent.addChildNode(nodePrevious);

        for (Iterator<Long> it = numbers.listIterator(1); it.hasNext(); ) {  //start in the second element

            Node node = Nodes.createNode(it.next());

            nodePrevious.setNextInLevel(node);
            node.setPreviousInLevel(nodePrevious);

            currentNodeParent.addChildNode(node);

            if (currentNodeParent.getChildrenNumber() == 2) {
                currentNodeParent = currentNodeParent.getNextInLevel();

                if (currentNodeParent != null)   //if nodeParent == null we have reached the end of the level
                    currentNodeParent.addChildNode(node);
            }

            nodePrevious = node;
        }

        //Before processing next level, turn currentNodeParent into the first node of the level we've just processed
        currentNodeParent = Nodes.getLeftMostInLevel(nodePrevious);
        lastLevelLength = numbers.size();
    }

    //Developed mainly for tests purposes
    public List<List<Long>> getTriangleAsLists() {
        List<List<Long>> resultList = new ArrayList<List<Long>>();

        Node node = root;

        resultList.add(Arrays.asList(root.getValue()));

        while (node.getChildrenNumber() != 0) {
            node = node.getChildrenNodes().get(0);

            List<Long> level = new ArrayList<Long>();

            Node nodeLevelVisitor = node;

            do {
                level.add(nodeLevelVisitor.getValue());
                nodeLevelVisitor = nodeLevelVisitor.getNextInLevel();
            } while (nodeLevelVisitor != null);

            resultList.add(level);
        }

        return resultList;
    }

}
