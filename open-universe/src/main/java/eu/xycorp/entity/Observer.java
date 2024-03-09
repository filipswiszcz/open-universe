package eu.xycorp.entity;

import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;

public class Observer extends PerspectiveCamera {

    private double x, y, z;
    private double yaw, pitch;

    private double sensitivity = 0.1;

    private double speed = 1.0;

    // TO REMOVAL
    private Rotate ry = new Rotate(0, Rotate.Y_AXIS);
    private Rotate rx = new Rotate(0, Rotate.X_AXIS);

    public Observer() {
        super(true);

        // TODO set starting pos

        this.getTransforms().addAll(rx, ry);
    }

    // TODO move func, teleport func
        // for that i need to write live map loading

    @Deprecated
    public void rotate(final double yaw, double pitch, final MouseEvent event, final double width, final double height) {

        if (pitch > 0) pitch = 0 - pitch;

        // System.out.println("x_axis: " + yaw + ", y_axis: " + pitch);

        rx.setAngle(rx.getAngle() - (pitch - this.pitch) * 0.1);
        ry.setAngle(ry.getAngle() - (yaw - this.yaw) * 0.1);

        this.yaw = yaw;
        this.pitch = pitch;
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