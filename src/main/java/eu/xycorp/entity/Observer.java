package eu.xycorp.entity;

import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;

public class Observer extends PerspectiveCamera {

    // pos, yaw, pitch
    private double x, y, z;
    private double yaw, pitch;

    private double speed = 1.0;

    public Observer() {
        super(true);
    }

    // TODO move func, teleport func
        // for that i need to write live map loading
    @Deprecated
    public void move(final KeyCode code) {

        // its very lazy, because it doesn't take in account
            // things like yaw and pitch, speed
            
        switch (code) {
            case UP: {this.setTranslateZ(this.getTranslateZ() + 1); break;}
            case LEFT: {this.setTranslateX(this.getTranslateX() - 1); break;}
            case RIGHT: {this.setTranslateX(this.getTranslateX() + 1); break;}
            case DOWN: {this.setTranslateZ(this.getTranslateZ() - 1); break;}
            default: break;
        }
    }

}