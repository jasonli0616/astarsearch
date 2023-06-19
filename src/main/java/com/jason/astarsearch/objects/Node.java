package com.jason.astarsearch.objects;

import java.util.ArrayList;

public class Node {

    // Position
    private int x;
    private int y;

    private Node parent;

    // F = G + H
    public int f;
    public int g;
    public int h;

    public Node(int xIn, int yIn) {
        x = xIn;
        y = yIn;
    }

    private Node(int xIn, int yIn, Node parentIn) {
        this(xIn, yIn);
        parent = parentIn;
    }

    /**
     * Get adjacent nodes, including those unavailable.
     * The unavailable ones will be filtered out during the search.
     * @return children nodes
     */
    public ArrayList<Node> getChildrenNodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node(getX(), getY()-1, this));
//        nodes.add(new Node(getX()-1, getY()-1, this));
//        nodes.add(new Node(getX()+1, getY()-1, this));
        nodes.add(new Node(getX()-1, getY(), this));
        nodes.add(new Node(getX()+1, getY(), this));
//        nodes.add(new Node(getX()-1, getY()+1, this));
        nodes.add(new Node(getX(), getY()+1, this));
//        nodes.add(new Node(getX()+1, getY()+1, this));

        return nodes;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Node) {
            if (((Node) o).getX() == getX() && ((Node) o).getY() == getY()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", getX(), getY());
    }

}