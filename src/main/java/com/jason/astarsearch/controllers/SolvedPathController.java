package com.jason.astarsearch.controllers;

import com.jason.astarsearch.objects.Node;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SolvedPathController {

    @FXML
    protected VBox showPathContainer;

    private CheckBox[][] checkBoxes;

    private static ArrayList<Node> solvedPath;
    private static ArrayList<Node> walls;
    private static int width;
    private static int height;

    @FXML
    protected void initialize() {

        checkBoxes = new CheckBox[width][height];

        // Clear container
        showPathContainer.getChildren().clear();

        // Create HBox for each height layer
        for (int i = 0; i < height; i++) {
            HBox layer = new HBox();

            // Create checkbox for each width layer
            for (int j = 0; j < width; j++) {

                CheckBox checkBox = new CheckBox();
                checkBoxes[j][i] = checkBox;

                layer.getChildren().add(checkBox);
            }
            showPathContainer.getChildren().add(layer);
        }

        for (Node node : solvedPath) {
            int x = node.getX();
            int y = node.getY();

            checkBoxes[x][y].setSelected(true);
            checkBoxes[x][y].setDisable(true);
        }

        System.out.println(walls);
        for (Node node : walls) {
            int x = node.getX();
            int y = node.getY();

            if (x >= 0 && x < width && y >= 0 && y < height) {
                checkBoxes[x][y].setDisable(true);
            }
        }

    }

    public static void setData(ArrayList<Node> solvedPathIn, ArrayList<Node> wallsIn, int widthIn, int heightIn) {
        solvedPath = solvedPathIn;
        walls = wallsIn;
        width = widthIn;
        height = heightIn;
    }

}