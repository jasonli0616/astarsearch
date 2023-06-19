package com.jason.astarsearch;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class HomepageController {

    @FXML
    protected Spinner<Integer> widthInput;

    @FXML
    protected Spinner<Integer> heightInput;

    /**
     * Initialize the page.
     *
     * This will set SpinnerValueFactory for the width and height spinners.
     */
    @FXML
    protected void initialize() {
        widthInput.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 64, 10));
        heightInput.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 64, 10));
    }

    /**
     * Handle the "begin" button.
     *
     * This will get the width and height values from the spinners, and give it to the maze building controller.
     */
    @FXML
    protected void handleBeginButton() {
        int width = widthInput.getValue();
        int height = heightInput.getValue();

        // TODO: Give values to maze building controller
    }

}