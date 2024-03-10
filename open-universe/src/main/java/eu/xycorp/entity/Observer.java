package eu.xycorp.entity;

import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.transform.Rotate;

public class Observer extends PerspectiveCamera {

    private double x, y, z;

    private double a, b;
    private double yaw, pitch;

    private final double CAM_DISTANCE = -100;
    private final double CAM_FOV = 60;

    private double SENSITIVITY = 0.5;

    private double SPEED = 1.0;

    // TO REMOVAL
    private Rotate rx = new Rotate(0, Rotate.X_AXIS);
    private Rotate ry = new Rotate(0, Rotate.Y_AXIS);

    public Observer() {
        super(true);

        this.setTranslateZ(CAM_DISTANCE);

        // TODO set starting pos

        this.getTransforms().addAll(rx, ry);
    }

    // TODO move func, teleport func
        // for that i need to write live map loading

    @Deprecated
    public void rot(double yaw, double pitch, double width, double height) {

        double r = Math.min(width, height) / 2;

        System.out.println(yaw + ", " + pitch);

        double dx = (yaw - width / 2) / r * 20;
        double dy = (pitch - height / 2) / r * 20;

        this.rx.setAngle((rx.getAngle() + dy) * SENSITIVITY);
        this.ry.setAngle((ry.getAngle() + dx) * SENSITIVITY);
    }

    @Deprecated
    public void move(final KeyCode code) {

        // its very lazy, because it doesn't take in account
            // things like yaw and pitch, speed
            // front should be according to current yaw and pitch
            
        switch (code) {
            case UP: {this.setTranslateZ(this.getTranslateZ() + 1); break;}
            case LEFT: {this.setTranslateX(this.getTranslateX() - 1); break;}
            case RIGHT: {this.setTranslateX(this.getTranslateX() + 1); break;}
            case DOWN: {this.setTranslateZ(this.getTranslateZ() - 1); break;}
            default: break;
        }
    }

}