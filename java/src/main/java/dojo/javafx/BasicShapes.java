package dojo.javafx;

import com.sun.javafx.css.Size;
import com.sun.javafx.css.SizeUnits;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class BasicShapes extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Basic Shapes");
        primaryStage.show();

        addCircle(root);
        addText(root);
        addTextBox(root);
    }

    private void addCircle(Pane pane) {
        double x = 50, y = 50, r = 40;
        Circle circle = new Circle(x, y, r, Color.PALEGREEN);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        pane.getChildren().add(circle);
    }

    private void addText(Pane pane) {
        double x = 50, y = 50;
        Text text = new Text("Eat");
        text.setFont(Font.font(40));

        text.applyCss(); // call applyCss() to get layout bounds
        double height = text.getLayoutBounds().getHeight();
        double width = text.getLayoutBounds().getWidth();

        // adjust x-y coordinates
        text.setX(x - 0.5*width);
        text.setY(y + 0.3*height);

        pane.getChildren().add(text);
    }

    private void addTextBox(Pane pane) {
        Group group = new Group();
        double x = 100, y = 100;
        Text text = new Text(x, y, "Apple");
        text.setFont(Font.font(40));
        text.applyCss();
        double height = text.getLayoutBounds().getHeight();
        double width = text.getLayoutBounds().getWidth();

//        Rectangle rectangle = new Rectangle(x, y, width, height);
        Rectangle rectangle = new Rectangle(x, y - 0.75*height, width, height);
        rectangle.setStroke(Color.RED);
        rectangle.setStrokeWidth(1);
        rectangle.setFill(Color.TRANSPARENT);

        group.getChildren().addAll(rectangle, text);
        pane.getChildren().add(group);
    }
}
