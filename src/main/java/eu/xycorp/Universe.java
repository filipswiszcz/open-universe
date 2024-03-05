package eu.xycorp;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public final class Universe extends Application {

    private double x = 0, y = 0;

    public Universe() {}

    public void init() {
        // TODO load data
    }

    public void start(final Stage stage) {

        final Group root = new Group();

        final URL url = getClass().getClassLoader().getResource("textures/silver_water_surface.jpg");
        final PhongMaterial material = new PhongMaterial();
        if (url != null) material.setDiffuseMap(new Image(url.toExternalForm()));

        final Sphere sphere = new Sphere(10);
        sphere.setMaterial(material);

        root.getChildren().add(sphere);

        final PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-100);

        final Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                this.x += 5; this.y += 5;
                root.getTransforms().addAll(
                    new Rotate(this.x, Rotate.X_AXIS),
                    new Rotate(this.y, Rotate.Y_AXIS)
                );
            }
        });

        stage.setTitle("Powered by xycorp");
        stage.setHeight(800);
        stage.setWidth(800);
        stage.setScene(scene);
        stage.show();
    }

    public void stop() {
        // TODO save data, curr windows pos
    }

}