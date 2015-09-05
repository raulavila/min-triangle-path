package com.raulavila.triangle.model;

public class Nodes {

    public static Node createNode(long value) {
        return new NodePositiveImpl(value);
    }

    public static Node getLeftMostInLevel(Node node) {
        if (node == null)
            throw new NullPointerException("node can't be null");

        while (node.getPreviousInLevel() != null)
            node = node.getPreviousInLevel();

        return node;
    }

    private Nodes() {
        throw new AssertionError();
    }
}
