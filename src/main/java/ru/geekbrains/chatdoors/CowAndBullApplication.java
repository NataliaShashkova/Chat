package ru.geekbrains.chatdoors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CowAndBullApplication extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CowAndBullApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 350);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}