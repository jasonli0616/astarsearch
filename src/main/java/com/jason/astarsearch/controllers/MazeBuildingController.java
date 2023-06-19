package com.jason.astarsearch.controllers;

import com.jason.astarsearch.AStarSearch;
import com.jason.astarsearch.App;
import com.jason.astarsearch.objects.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MazeBuildingController {

    /**
     * The text prompting the user what to do.
     *
     * E.g. "Select start", "Select end", etc.
     */
    @FXML
    protected Text actionText;

    /**
     * The container of to contain the HBoxes/checkboxes to create the maze.
     *
     * Inside this VBox, there will be a VHox per row, containing a checkbox per column.
     */
    @FXML
    protected VBox mazeBuildingContainer;

    /**
     * The state of this page.
     * 0 = select start point
     * 1 = select end point
     * 2 = select walls
     */
    private int state;

    // Width and height
    private static int width;
    private static int height;

    // 2D array of checkboxes
    private CheckBox[][] checkBoxes;

    private Node startNode;
    private Node endNode;
    private ArrayList<Node> walls;

    /**
     * Initialize the page.
     */
    @FXML
    protected void initialize() {
        state = 0;

        checkBoxes = new CheckBox[width][height];

        fillMazeBuildingContainerWithCheckboxes();
    }

    /**
     * Handle the next button.
     * This will either move to the next state on this page, or next page.
     */
    @FXML
    protected void handleNextButton() throws IOException {

        ArrayList<Node> selectedNodes = getCheckedBoxes();

        // Ensure correct amount of nodes selected
        if ((state == 0 || state == 1) && selectedNodes.size() != 1) {
            showCheckBoxFormatError("Please select exactly one checkbox.");
            return;
        }

        // Collect data
        switch (state) {
            // Get start node
            case 0 -> startNode = selectedNodes.get(0);

            // Get end node
            case 1 -> endNode = selectedNodes.get(0);

            // Get walls
            case 2 -> walls = selectedNodes;
        }

        // Change state
        state++;

        if (state >= 0 && state <= 2) {
            fillMazeBuildingContainerWithCheckboxes();
        } else {

            ArrayList<Node> wallsNoPerimeter = new ArrayList<>(walls);

            insertPerimeterToWalls();

            // Solve
            AStarSearch search = new AStarSearch(startNode, endNode, walls);
            ArrayList<Node> shortestPath = search.findClosestPath();
            Collections.reverse(shortestPath);

            if (shortestPath.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Impossible.").showAndWait();
                return;
            }

            // Set new controller
            SolvedPathController.setData(shortestPath, wallsNoPerimeter, width, height);
            App.setRoot("solvedpath");

        }
    }

    /**
     * Fill the maze building container with checkboxes.
     * The state variable should be changed before calling this method.
     *
     * Add these checkboxes to the 2D array instance variable.
     */
    private void fillMazeBuildingContainerWithCheckboxes() {

        // Change action text
        switch (state) {
            case 0 -> actionText.setText("Select start node");
            case 1 -> actionText.setText("Select end node");
            case 2 -> actionText.setText("Create walls");
        }

        // Clear container
        mazeBuildingContainer.getChildren().clear();

        // Create HBox for each height layer
        for (int i = 0; i < height; i++) {
            HBox layer = new HBox();

            // Create checkbox for each width layer
            for (int j = 0; j < width; j++) {

                CheckBox checkBox = new CheckBox();
                checkBoxes[j][i] = checkBox; // insert to 2D array

                // Disable prohibited nodes
                Node node = new Node(j, i);
                if (getProhibitedNodes().contains(node))
                    checkBox.setDisable(true);

                layer.getChildren().add(checkBox);
            }
            mazeBuildingContainer.getChildren().add(layer);
        }
    }

    /**
     * Get all the prohibited nodes, that are already used as the start, end, or walls.
     * @return prohibited nodes
     */
    private ArrayList<Node> getProhibitedNodes() {
        ArrayList<Node> nodes = new ArrayList<>();

        if (startNode != null)
            nodes.add(startNode);

        if (endNode != null)
            nodes.add(endNode);

        if (walls != null)
            nodes.addAll(walls);

        return nodes;
    }

    /**
     * Get all the checked nodes.
     * @return checked nodes
     */
    private ArrayList<Node> getCheckedBoxes() {
        ArrayList<Node> checked = new ArrayList<>();

        // Loop through x and y
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                // If checked, create node and add to list
                CheckBox checkBox = checkBoxes[i][j];
                if (checkBox.isSelected()) {
                    Node node = new Node(i, j);
                    checked.add(node);
                }
            }
        }

        return checked;
    }

    /**
     * Insert perimeter into walls list.
     */
    private void insertPerimeterToWalls() {
        ArrayList<Node> perimeter = new ArrayList<>();

        perimeter.add(new Node(-1, -1));
        for (int i = 0; i <= width; i++) {
            perimeter.add(new Node(i, -1));
            perimeter.add(new Node(i, height));
        }
        for (int i = 0; i <= height; i++) {
            perimeter.add(new Node(-1, i));
            perimeter.add(new Node(width, i));
        }
        walls.addAll(perimeter);
    }

    /**
     * Set the static width and height.
     * This should be called in another controller, before moving to this controller.
     *
     * @param setWidth new width
     * @param setHeight new height
     */
    protected static void setWidthAndHeight(int setWidth, int setHeight) {
        width = setWidth;
        height = setHeight;
    }

    private void showCheckBoxFormatError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR, error);
        alert.setHeaderText("Checkbox format error");
        alert.showAndWait();
    }

    @FXML
    protected void handleBackButton() throws IOException {
        App.setRoot("homepage");
    }

}