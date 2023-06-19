package com.jason.astarsearch;

import com.jason.astarsearch.objects.Node;

import java.util.ArrayList;

public class AStarSearch {

    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;

    private Node currentNode;

    private Node startNode;
    private Node endNode;
    private ArrayList<Node> walls;

    /**
     * Create a new instance of the A* search algorithm object.
     *
     * @param startNodeIn start node
     * @param endNodeIn end node
     * @param wallsIn walls
     */
    public AStarSearch(Node startNodeIn, Node endNodeIn, ArrayList<Node> wallsIn) {
        startNode = startNodeIn;
        endNode = endNodeIn;
        walls = wallsIn;

        currentNode = startNodeIn;

        openList = new ArrayList<>();
        closedList = wallsIn;
    }

    public ArrayList<Node> findClosestPath() {
        ArrayList<Node> path = new ArrayList<>();

        openList.add(startNode);
        setStartNodeFGH();

        while (openList.size() > 0) {

            currentNode = openList.get(0);

            // Move current node from open to closed
            openList.remove(currentNode);
            closedList.add(currentNode);

            // If current node
            if (currentNode.equals(endNode)) {
                while (currentNode != null) {
                    path.add(currentNode);
                    currentNode = currentNode.getParent();
                }
                return path;
            }

            // Set current node to children
            for (Node child : getChildren(currentNode)) {
                child.g = currentNode.g + 1;
                child.h = (int) (Math.pow(child.getX(), 2) + Math.pow(child.getY(), 2));
                child.f = child.g + child.h;

                if (!openList.contains(child)) {
                    openList.add(child);
                }
            }

        }
        return path;
    }

    private void setStartNodeFGH() {
        int x = endNode.getX() - startNode.getX();
        int y = endNode.getY() - startNode.getY();

        startNode.g = 0;
        startNode.h = (int) (Math.pow(x, 2) + Math.pow(y, 2));
        startNode.f = startNode.g + startNode.h;
    }

    private ArrayList<Node> getChildren(Node parent) {
        ArrayList<Node> children = parent.getChildrenNodes();
        children.removeAll(closedList);
        return children;
    }
}