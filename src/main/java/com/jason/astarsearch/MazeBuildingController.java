package com.jason.astarsearch;

import javafx.fxml.FXML;
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
     * The container of to contain the VBoxes/checkboxes to create the maze.
     *
     * Inside this VBox, there will be a VBox per row, containing a checkbox per column.
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

    /**
     * Initialize the page.
     */
    @FXML
    protected void initialize() {

    }

    /**
     * Handle the next button.
     * This will either move to the next state on this page, or next page.
     */
    @FXML
    protected void handleNextButton() {

    }

}