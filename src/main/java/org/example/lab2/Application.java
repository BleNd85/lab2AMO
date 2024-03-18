package org.example.lab2;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


public class Application extends javafx.application.Application {
    private int[] numbers;

    @Override
    public void start(Stage stage) {
        Label myName = new Label("ІО-22 Удод Владислав \nВаріант №25");
        Label timeResult = new Label("Час сортування: ");
        Button sortButton = new Button("Сортувати");
        Button chooseFileButton = new Button("Обрати файл");
        Button generateRandom = new Button("Зегерувати випадковий масив");
        TextField randomElements = new TextField();
        randomElements.setPromptText("Кількість елементів масиву");
        randomElements.setFocusTraversable(false);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(chooseFileButton, sortButton);
        VBox labelBox = new VBox(10);
        labelBox.getChildren().addAll(myName, timeResult);
        HBox random = new HBox(10);
        random.getChildren().addAll(randomElements, generateRandom);


        String currentDirectory = System.getProperty("user.dir");
        FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("Text Files", "*.txt");

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
            long startTime = System.nanoTime();
            BoseNelsonSort.boseNelsonSort(numbers, 0, numbers.length - 1);
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
        ArrayGenerator arrayGenerator = new ArrayGenerator();
        generateRandom.setOnAction(e -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("randomArray.txt"))) {
                int n = Integer.parseInt(randomElements.getText());
                int[] array = arrayGenerator.generateArray(n);
                for (int i = 0; i < array.length; i++) {
                    writer.write(Integer.toString(array[i]));
                    if (i < array.length - 1) {
                        writer.write(", ");
                    }
                }

            } catch (NumberFormatException | IOException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка вводу");
                alert.setHeaderText("Помилковий формат вводу");
                alert.setContentText("Будь ласка, введіть ціле число.");
                alert.showAndWait();
            }
        });

        FlowPane root = new FlowPane();
        stage.setTitle("Лабораторна робота №2");
        stage.setWidth(400);
        stage.setHeight(200);
        buttonBox.setPadding(new Insets(5, 1000, 10, 5));
        labelBox.setPadding(new Insets(0, 1000, 10, 5));
        random.setPadding(new Insets(10, 0, 10, 5));
        root.getChildren().addAll(labelBox, buttonBox, random);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}