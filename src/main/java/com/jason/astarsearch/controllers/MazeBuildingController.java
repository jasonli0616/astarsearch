package com.jason.astarsearch.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    protected void handleNextButton() {

        // Collect data
        switch (state) {
            case 0:
                break;

            case 1:
                break;

            case 2:
                break;
        }

        // Change state
        state++;

        if (state >= 0 && state <= 3) {
            fillMazeBuildingContainerWithCheckboxes();
        } else {
            // TODO: Move to next page
        }
    }

    /**
     * Fill the maze building container with checkboxes.
     * The state variable should be changed before calling this method.
     *
     * Add these checkboxes to the 2D array instance variable.
     */
    private void fillMazeBuildingContainerWithCheckboxes() {

        // Clear container
        mazeBuildingContainer.getChildren().clear();

        // Create HBox for each height layer
        for (int i = 0; i < width; i++) {
            HBox layer = new HBox();

            // Create checkbox for each width layer
            for (int j = 0; j < height; j++) {
                CheckBox checkBox = new CheckBox();
                checkBoxes[i][j] = checkBox; // insert to 2D array
                layer.getChildren().add(checkBox);
            }
            mazeBuildingContainer.getChildren().add(layer);
        }
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

}