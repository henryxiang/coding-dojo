package dojo.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NumberInCircle extends Application {
    private static final int CIRCLE_DIAMETER = 30;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("My Canvas");
        primaryStage.setScene(scene);
        primaryStage.show();

        Canvas cvs = new Canvas();
        cvs.setWidth(300);
        cvs.setHeight(300);
        cvs.setLayoutX(0);
        cvs.setLayoutY(0);
        root.getChildren().add(cvs);

        GraphicsContext gc = cvs.getGraphicsContext2D();
        double x = (cvs.getWidth() - CIRCLE_DIAMETER)/2;
        double y = (cvs.getHeight() - CIRCLE_DIAMETER)/2;
        double r = CIRCLE_DIAMETER / 2.0;

//        gc.strokeLine(50+r, 50+r, 70+r, 105+r);
//        gc.strokeLine(x+r, y+r, 70+r, 105+r);

        gc.drawImage(createCircledNumber(7), x, y); // draw circle in the center of canvas
        gc.drawImage(createCircledNumber(35), 50, 50);
        gc.drawImage(createCircledNumber(75), 70, 105);

        double[] ep = getLineEndPoints(x, y, 70, 105, r);
        gc.strokeLine(ep[0], ep[1], ep[2], ep[3]);
        ep = getLineEndPoints(50, 50, 70, 105, r);
        gc.strokeLine(ep[0], ep[1], ep[2], ep[3]);
    }

    private WritableImage createCircledNumber(int number) {
        int strokWidth = 3;
        //createCircledNumber() method always returns 26px X 26px sized image
        StackPane sPane = new StackPane();
        sPane.setPrefSize(CIRCLE_DIAMETER, CIRCLE_DIAMETER);

        Circle c = new Circle(CIRCLE_DIAMETER/2.0);
        c.setStroke(Color.BLACK);
        c.setFill(Color.WHITE);
        c.setStrokeWidth(strokWidth);
        sPane.getChildren().add(c);

        Text txtNum = new Text(number+"");
        sPane.getChildren().add(txtNum);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return sPane.snapshot(parameters, null);
    }

    private double[] getLineEndPoints(double x1, double y1, double x2, double y2, double r) {
        double[] endPoints = new double[4];
        double x = Math.abs(x1 - x2);
        double y = Math.abs(y1 - y2);
        double dist = Math.sqrt(x*x + y*y);
        double dx = (x*r/dist);
        double dy = (y*r/dist);
        endPoints[0] = x1 - (x1 - x2)/x*dx + r;
        endPoints[1] = y1 - (y1 - y2)/y*dy + r;
        endPoints[2] = x2 - (x2 - x1)/x*dx + r;
        endPoints[3] = y2 - (y2 - y1)/y*dy + r;
        return endPoints;
    }
}
