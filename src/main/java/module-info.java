module com.jason.astarsearch {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jason.astarsearch to javafx.fxml;
    exports com.jason.astarsearch;
    exports com.jason.astarsearch.controllers;
    opens com.jason.astarsearch.controllers to javafx.fxml;
}