package com.raulavila.triangle.model;

import java.util.List;

public interface Node {

    long getValue();

    void setValue(long value);

    Node getPreviousInLevel();

    void setPreviousInLevel(Node previous);

    Node getNextInLevel();

    void setNextInLevel(Node next);

    void addChildNode(Node node);

    int getChildrenNumber();

    List<Node> getChildrenNodes();

    Node getChild(int index);

    void removeChild(int index);

    boolean isLeaf();

    List<Long> getMinimalPath();

    void addToMinimalPath(List<Long> path);

}


