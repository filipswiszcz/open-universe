package eu.xycorp;

import eu.xycorp.entity.Celestial;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public final class Universe extends Application {

    public Universe() {}

    public void init() {} // TODO load settings, textures etc

    public void start(final Stage stage) {

        BorderPane pane = new BorderPane();
        Group content = new Group(new AmbientLight()), map = new Group();
        Pane stackpane = new Pane(map);

        // Scenes
        Scene scene = new Scene(pane, 500, 500);
        SubScene contentSubscene = new SubScene(content, 500, 500, true, SceneAntialiasing.BALANCED);
        contentSubscene.widthProperty().bind(scene.widthProperty());
        contentSubscene.heightProperty().bind(scene.heightProperty());
        SubScene minimapSubscene = new SubScene(stackpane, 256, 256);
        minimapSubscene.setFill(Color.DARKGREY);

        pane.getChildren().add(contentSubscene);
        pane.setBottom(minimapSubscene);

        // Create Player
        Celestial player = new Celestial(stage);
        Rectangle currentPosition = new Rectangle(5, 5);
        currentPosition.layoutXProperty().bind(minimapSubscene.widthProperty().divide(2));
        currentPosition.layoutYProperty().bind(minimapSubscene.heightProperty().divide(2));
        currentPosition.setFill(Color.RED);
        stackpane.getChildren().add(currentPosition);

        /*map.layoutXProperty().bind(player.posXPorperty().divide(-10).add(minimapSubscene.widthProperty().divide(2)));
        map.layoutYProperty().bind(player.posYPorperty().divide(-10).add(minimapSubscene.heightProperty().divide(2)));*/

        // Create Box in
        PhongMaterial material = new PhongMaterial(Color.AQUA);
        for (int v = 0; v < 3_600; v += 180) {
            for (int y = 0; y < 500; y += 100) {
                Box box = new Box(50, 50, 50);
                box.setTranslateX(Math.sin(v / 10) * 1_000);
                box.setTranslateY(y);
                box.setTranslateZ(Math.cos(v / 10) * 1_000);
                box.setMaterial(material);
                content.getChildren().add(box);

                Rectangle boxPosition = new Rectangle(5, 5);
                boxPosition.translateXProperty().bind(box.translateXProperty().divide(10));
                boxPosition.translateYProperty().bind(box.translateZProperty().divide(10));
                boxPosition.setFill(Color.AQUA);
                map.getChildren().add(boxPosition);
            }
        }

        contentSubscene.setCamera(player.getCamera());
        scene.addEventHandler(KeyEvent.KEY_PRESSED, player.handleKeyboard());
        scene.addEventHandler(MouseEvent.MOUSE_MOVED, player.handleMouse());
        scene.setCursor(Cursor.CROSSHAIR);
        scene.setFill(Color.WHITE);

        stage.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            if (e.getCode() != KeyCode.F11) {
                return;
            }
            if (stage.isFullScreen()) {
                stage.setFullScreen(false);
            } else {
                stage.setFullScreen(true);
            }
        });
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();

        /*final BorderPane pane = new BorderPane();
        final Group content = new Group(new AmbientLight());

        final Scene scene = new Scene(pane, 800, 600);
        final SubScene galaxy = new SubScene(content, 800, 600, true, SceneAntialiasing.BALANCED);
        galaxy.widthProperty().bind(scene.widthProperty());
        galaxy.heightProperty().bind(scene.heightProperty());

        pane.getChildren().add(galaxy);

        final Celestial celestial = new Celestial(stage);

        final PhongMaterial material = new PhongMaterial(Color.AQUA);
        for (int i = 0; i < 3600; i += 180) {
            for (int j = 0; j < 500; j += 100) {

                final Box box = new Box(50, 50, 50);
                box.setTranslateX(Math.sin(i / 10) * 1000);
                box.setTranslateY(j);
                box.setTranslateZ(Math.cos(i / 10) * 1000);
                box.setMaterial(material);

                content.getChildren().add(box);
            }
        }
        
        galaxy.setCamera(celestial.getCamera());

        scene.addEventHandler(KeyEvent.KEY_PRESSED, celestial.handleKeyboard());
        scene.addEventHandler(MouseEvent.MOUSE_MOVED, celestial.handleMouse());
        scene.setCursor(Cursor.CROSSHAIR);
        scene.setFill(Color.WHITE);

        stage.setTitle("Powered by xycorp");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();*/
    }

    public void stop() {
        // TODO save data, curr windows pos
    }

}