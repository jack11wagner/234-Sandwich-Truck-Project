package com.example.sandwichtruckproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Map extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MapController.class.getResource("map-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("-Truck Map-");
        stage.setScene(scene);
        stage.show();
    }

    public static void main() {
        launch();
    }
}
