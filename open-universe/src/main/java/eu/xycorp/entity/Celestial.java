package eu.xycorp.entity;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.robot.Robot;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Celestial extends Observer {

    private DoubleProperty rcx = new SimpleDoubleProperty();
    private DoubleProperty rcy = new SimpleDoubleProperty();

    private DoubleProperty cx = new SimpleDoubleProperty();
    private DoubleProperty cy = new SimpleDoubleProperty();

    private PerspectiveCamera camera = new PerspectiveCamera(true);

    private final Rotate rx = new Rotate(0, 250, 0, 0, Rotate.Y_AXIS);
    private final Rotate ry = new Rotate(0, 0, 250, 0, Rotate.X_AXIS);

    private final Robot robot = new Robot();

    public Celestial(final Stage stage) {
        this.camera.getTransforms().addAll(this.rx, this.ry);
        this.camera.setFieldOfView((40 + 62) / 2);
        this.camera.setNearClip(0.1);
        this.camera.setFarClip(100000);
        this.camera.setVerticalFieldOfView(true);

        this.cx.bind(stage.widthProperty().divide(2));
        this.cy.bind(stage.heightProperty().divide(2));

        this.rcx.bind(stage.xProperty().add(this.cx));
        this.rcy.bind(stage.yProperty().add(this.cy));

        this.rx.angleProperty().bind(this.yaw.subtract(90));
        this.ry.angleProperty().bind(this.pitch);

        this.camera.translateXProperty().bind(this.x);
        this.camera.translateZProperty().bind(this.y);
        this.camera.translateYProperty().bind(this.z);
    }

    public EventHandler<KeyEvent> handleKeyboard() {
        return event -> {
            switch (event.getCode()) {
                case A: {this.rotate(-1, 0); break;}
                case D: {this.rotate(1, 0); break;}
                case W: {this.move(1, 1, 0); break;}
                case S: {this.move(-1, -1, 0); break;}
                case SPACE: {this.move(0, 0, 10); break;}
                case SHIFT: {this.move(0, 0, -10); break;}
                default: break;
            }
        };
    }

    public EventHandler<MouseEvent> handleMouse() {
        return event -> {
            this.rotate(event.getSceneX() - this.cx.doubleValue(), this.cy.doubleValue() - event.getSceneY());
            Platform.runLater(() -> {
                this.robot.mouseMove(this.rcx.intValue(), this.rcy.intValue());
            });
        };
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

}