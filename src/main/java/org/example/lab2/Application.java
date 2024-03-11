package org.example.lab2;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;


public class Application extends javafx.application.Application {
    private int[] numbers;

    @Override
    public void start(Stage stage) {
        FlowPane root = new FlowPane();
        stage.setTitle("Лабораторна робота №2");
        stage.setWidth(300);
        stage.setHeight(150);
        Label myName = new Label("ІО-22 Удод Владислав \nВаріант №25");
        Label timeResult = new Label("Час сортування: ");
        Button sortButton = new Button("Сортувати");
        Button chooseFileButton = new Button("Обрати файл");
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(chooseFileButton, sortButton);
        VBox labelBox = new VBox(10);
        labelBox.getChildren().addAll(myName, timeResult);
        String currentDirectory = System.getProperty("user.dir");
        FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("Text Files", "*.txt");
        ;
        chooseFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Обрати масив для сортування");
            fileChooser.getExtensionFilters().addAll(ex1);
            fileChooser.setInitialDirectory(new File(currentDirectory));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    String line;
                    String[] tokens;
                    line = reader.readLine();
                    tokens = line.split(", ");
                    numbers = new int[tokens.length];

                    for (int i = 0; i < tokens.length; i++) {
                        numbers[i] = Integer.parseInt(tokens[i]);
                    }
                } catch (IOException ignored) {
                }
            }
        });
        sortButton.setOnAction(e -> {
            System.out.println(Arrays.toString(numbers));
            long startTime = System.nanoTime();
            BoseNelsonSort.bozoNelsonSort(numbers, 0, numbers.length - 1);
            long endTime = System.nanoTime();
            double elapsedTimeInSeconds = (double) (endTime - startTime) / 1000000000;
            timeResult.setText("Час сортування: " + String.format("%.6f", elapsedTimeInSeconds) + " с");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("sortedArray.txt"))) {
                for (int i = 0; i < numbers.length; i++) {
                    writer.write(Integer.toString(numbers[i]));

                    if (i < numbers.length - 1) {
                        writer.write(", ");
                    }
                }
            } catch (IOException ignore) {
            }
        });

        buttonBox.setPadding(new Insets(5, 0, 10, 5));
        labelBox.setPadding(new Insets(0, 0, 10, 5));
        root.getChildren().addAll(labelBox, buttonBox);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}