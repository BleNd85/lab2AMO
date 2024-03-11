package org.example.lab2;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Лабораторна робота №2");
        stage.setWidth(400);
        stage.setHeight(500);
        Button button1 = new Button("ABOBA");
        button1.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "ABOBUSAMOGUS");
            alert.showAndWait();
        });
        Scene scene = new Scene(button1);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}