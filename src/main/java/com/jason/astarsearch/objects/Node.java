package com.jason.astarsearch.objects;

public class Node {

    // Position
    private int x;
    private int y;

    // F = G + H
    public int f;
    public int g;
    public int h;

    public Node(int xIn, int yIn) {
        x = xIn;
        y = yIn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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