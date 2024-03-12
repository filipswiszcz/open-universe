package eu.xycorp;

import eu.xycorp.entity.Galaxy;
import eu.xycorp.entity.observer.Spacewalker;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.AmbientLight;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public final class Universe extends Application {

    public Universe() {}

    public void init() {} // TODO load settings, textures etc

    public void start(final Stage stage) {

        final BorderPane pane = new BorderPane();
        final Group content = new Group(new AmbientLight());

        final Scene scene = new Scene(pane, 800, 600);
        final Galaxy galaxy = new Galaxy(content, 800, 600);
        galaxy.widthProperty().bind(scene.widthProperty());
        galaxy.heightProperty().bind(scene.heightProperty());

        pane.getChildren().add(galaxy);

        final Spacewalker spacewalker = new Spacewalker(stage);

        // final Celestial celestial = new Celestial(stage);
        /*final PerspectiveCamera camera = new PerspectiveCamera();
        camera.setFieldOfView((40 + 62) / 2);
        camera.setNearClip(0.1);
        camera.setFarClip(100000);
        camera.setVerticalFieldOfView(true);*/
        
        galaxy.setCamera(spacewalker.getCamera());
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> spacewalker.keyboard(event.getCode()));
        scene.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            spacewalker.mouse(event.getSceneX(), event.getSceneY(), stage.getWidth(), stage.getHeight());
        });
        scene.setCursor(Cursor.CROSSHAIR);
        scene.setFill(Color.BLACK);

        final ObservableList<Screen> screens = Screen.getScreens();
        final Screen screen = screens.size() > 1 ? screens.get(1) : screens.get(0); // TODO its only valid for my setup, change it later

        stage.setTitle("Powered by xycorp");
        stage.setAlwaysOnTop(true);
        stage.setX(screen.getBounds().getMinX() / 1.5);
        stage.setY(screen.getBounds().getMaxY() / 4);
        stage.setScene(scene);
        stage.show();
    }

    public void stop() {
        // TODO save data, curr windows pos
    }

}