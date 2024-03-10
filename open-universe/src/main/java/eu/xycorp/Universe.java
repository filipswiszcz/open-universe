package eu.xycorp;

import java.net.URL;

import eu.xycorp.entity.Observer;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public final class Universe extends Application {

    public Universe() {}

    public void init() {} // TODO load settings, textures etc

    public void start(final Stage stage) {

        final Group root = new Group();

        final URL url = getClass().getClassLoader().getResource("textures/silver_water_surface.jpg");
        final PhongMaterial material = new PhongMaterial();
        if (url != null) material.setDiffuseMap(new Image(url.toExternalForm()));

        final Sphere a = new Sphere(10);
        a.setMaterial(material);

        final Sphere b = new Sphere(10);
        b.setLayoutX(-20);
        // b.setTranslateZ(5);
        b.setMaterial(material);

        final Box c = new Box(20, 20, 20);
        c.setLayoutX(-15);
        c.setLayoutY(15);
        c.setMaterial(material);

        root.getChildren().addAll(a, b, c);

        final Observer observer = new Observer();

        final Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        scene.setCamera(observer);
        // scene.setCursor(Cursor.NONE);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> observer.move(event.getCode()));
        scene.addEventHandler(MouseEvent.MOUSE_MOVED, event -> observer.rot(event.getSceneX(), event.getSceneY(), scene.getWidth(), scene.getHeight()));
        scene.addEventHandler(MouseEvent.MOUSE_EXITED, event -> System.out.println("ex"));

        stage.setTitle("Powered by xycorp");
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        // stage.setFullScreen(true);
        stage.setFullScreenExitHint("ESC to exit fullscreen!");
        stage.show();
    }

    public void stop() {
        // TODO save data, curr windows pos
    }

}