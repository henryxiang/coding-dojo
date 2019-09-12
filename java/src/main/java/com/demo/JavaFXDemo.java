package com.demo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFXDemo extends Application {
    private class Node extends StackPane {
        public Node(String label, int x, int y) {
            Circle circle = new Circle(20, Color.PALEGREEN);
            circle.setStroke(Color.BLACK);
            Text text = new Text(label);
            this.getChildren().addAll(circle, text);
            this.setLayoutX(x);
            this.setLayoutY(y);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Java FX Demo");
        Group root = new Group();
        Node node1 = new Node("ABC", -100, -100);
        node1.setMaxSize(40, 40);
        Node node2 = new Node("DEF", 100, -100);
//        node1.setMaxSize(40, 40);
//        root.getChildren().addAll(node1);
        Scene scene = new Scene(node1, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
