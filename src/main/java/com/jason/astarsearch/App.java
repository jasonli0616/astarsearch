package com.jason.astarsearch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage newStage) throws IOException {
        stage = newStage;

        scene = new Scene(loadFXML("homepage"), 600, 400);
        stage.setTitle("A* Search");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String root) throws IOException {
        scene.setRoot(loadFXML(root));
    }

    private static Parent loadFXML(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(path + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}