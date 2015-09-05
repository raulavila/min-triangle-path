package com.raulavila.triangle.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class NodePositiveImpl implements Node {
    long value;

    private Node previous;
    private Node next;

    private List<Node> childrenNodes = new ArrayList<Node>();

    private List<Long> minimalPath = new ArrayList<Long>();

    public NodePositiveImpl(long value) {
        if (value <= 0)
            throw new IllegalArgumentException("value must be positive");

        this.value = value;
        minimalPath.add(value);
    }

    @Override
    public long getValue() {
        return value;
    }

    @Override
    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public Node getPreviousInLevel() {
        return previous;
    }

    @Override
    public void setPreviousInLevel(Node previous) {
        if (previous == null)
            throw new NullPointerException("previous can't be null");

        this.previous = previous;
    }

    @Override
    public Node getNextInLevel() {
        return next;
    }

    @Override
    public void setNextInLevel(Node next) {
        if (next == null)
            throw new NullPointerException("next can't be null");

        this.next = next;
    }

    @Override
    public void addChildNode(Node node) {
        if (node == null)
            throw new NullPointerException("node can't be null");

        if (childrenNodes.size() == 2)
            throw new IllegalStateException("The node contains 2 children, it's not possible to add more");

        childrenNodes.add(node);
    }

    @Override
    public int getChildrenNumber() {
        return childrenNodes.size();
    }


    @Override
    public List<Node> getChildrenNodes() {
        return Collections.unmodifiableList(childrenNodes);
    }

    @Override
    public Node getChild(int index) {
        if (index < 0 || index > 1)
            throw new IllegalArgumentException("Index must be 0 or 1");

        try {
            return childrenNodes.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("There is no node with index %d", index));
        }
    }

    @Override
    public void removeChild(int index) {
        if (index < 0 || index > 1)
            throw new IllegalArgumentException("Index must be 0 or 1");

        try {
            childrenNodes.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("There is no node with index %d", index));
        }
    }


    @Override
    public boolean isLeaf() {
        return childrenNodes.isEmpty();
    }

    @Override
    public List<Long> getMinimalPath() {
        return Collections.unmodifiableList(minimalPath);
    }

    @Override
    public void addToMinimalPath(List<Long> path) {
        minimalPath.addAll(path);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
