package eu.xycorp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public final class Universe extends Application {

    public Universe() {}

    public void start(final Stage stage) {

        final Scene scene = new Scene(new StackPane(), 1280, 960);

        stage.setScene(scene);
        stage.show();
    }

}