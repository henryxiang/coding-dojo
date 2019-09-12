package dojo.javafx;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BasicTransition extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("JavaFX Basic Transition");
        primaryStage.setScene(scene);
        primaryStage.show();

        typeText(root);
    }

    private void typeText(Pane pane) {
        final String content = "It was the best of times,\n" +
                "it was the worst of times,\n" +
                "it was the age of wisdom,\n" +
                "it was the age of foolishness,\n" +
                "it was the epoch of belief,\n" +
                "it was the epoch of incredulity,\n" +
                "it was the season of Light,\n" +
                "it was the season of Darkness,\n" +
                "it was the spring of hope,\n" +
                "it was the winter of despair.\n";
        final Text text = new Text(10, 20, "");
        text.setFont(Font.font(20));
        pane.getChildren().add(text);

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(30000));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                text.setText(content.substring(0, n));
            }

        };

        animation.play();
    }
}
