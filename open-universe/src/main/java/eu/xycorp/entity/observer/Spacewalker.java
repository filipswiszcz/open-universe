package eu.xycorp.entity.observer;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.robot.Robot;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Spacewalker extends Observer {

    // private double cx; // center x
    // private double cy;

    // private double rcx; // relative center x
    // private double rcy;

    private DoubleProperty cx = new SimpleDoubleProperty();
    private DoubleProperty cy = new SimpleDoubleProperty();

    private DoubleProperty rcx = new SimpleDoubleProperty();
    private DoubleProperty rcy = new SimpleDoubleProperty();

    private final PerspectiveCamera camera;

    private final Rotate rx; // yaw
    private final Rotate ry; // pitch

    private final Robot robot;

    public Spacewalker(final Stage stage) {
        // this.cx = stage.getWidth() / 2; // TODO it should be binded, all of it!
        // this.cy = stage.getHeight() / 2;    // it should change together with stage sizes
        // this.rcx = stage.getX() + (stage.getWidth() / 2);
        // this.rcy = stage.getY() + (stage.getHeight() / 2);
        this.cx.bind(stage.widthProperty().divide(2));
        this.cy.bind(stage.heightProperty().divide(2));
        this.rcx.bind(stage.xProperty().add(this.cx));
        this.rcy.bind(stage.yProperty().add(this.cy));

        this.camera = new PerspectiveCamera(true);

        this.rx = new Rotate(0, 250, 0, 0, Rotate.Y_AXIS);
        this.ry = new Rotate(0, 0, 250, 0, Rotate.X_AXIS);

        this.robot = new Robot();

        // camera settings
        this.camera.getTransforms().addAll(this.rx, this.ry);
        this.camera.setVerticalFieldOfView(true);
        this.camera.setFieldOfView((40 + 62) / 2);
        this.camera.setFarClip(100_000);
        this.camera.setNearClip(0.1);
    }

    public void keyboard(final KeyCode code) { // very bad interpretation, like the worst
        switch (code) {
            case W: {this.move(0, 0, 1); break;}
            case A: {this.move(-1, 0, 0); break;}
            case S: {this.move(0, 0, -1); break;}
            case D: {this.move(1, 0, 0); break;}
            case SPACE: {this.move(0, 5, 0); break;}
            case SHIFT: {this.move(0, -5, 0); break;}
            default: break;
        }
    }

    public void mouse(final double yaw, final double pitch, final double width, final double height) {
        this.rotate(yaw, pitch, width, height);
        // Platform.runLater(() -> this.robot.mouseMove(this.rcx.intValue(), this.rcy.intValue()));
    }

    private void move(final double x, final double y, final double z) {

        final double fx = Math.cos(Math.toRadians(this.getYaw()));
        final double fy = -Math.sin(Math.toRadians(this.getYaw()));
        final double fz = 1;

        this.setX(this.getX() + fx * x * this.getSpeed());
        this.setY(this.getY() + fy * y * this.getSpeed());
        this.setZ(this.getZ() + fz * z);

        this.camera.setTranslateX(this.getX());
        this.camera.setTranslateY(this.getY());
        this.camera.setTranslateZ(this.getZ());
    }

    private void rotate(final double yaw, final double pitch, final double width, final double height) {
        
        final double r = Math.min(width, height) / 2;

        final double dx = (yaw - width / 2) / r * 20;
        final double dy = (pitch - height / 2) / r * 20;

        this.rx.setAngle((this.getYaw() + dx) * this.getSensitivity());
        this.ry.setAngle((this.getPitch() + dy) * this.getSensitivity());

        this.setYaw(yaw);
        this.setPitch(pitch);
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

}