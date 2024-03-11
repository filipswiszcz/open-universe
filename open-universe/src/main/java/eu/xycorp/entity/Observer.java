package eu.xycorp.entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Observer {

    protected DoubleProperty x = new SimpleDoubleProperty();
    protected DoubleProperty y = new SimpleDoubleProperty();
    protected DoubleProperty z = new SimpleDoubleProperty();

    protected DoubleProperty yaw = new SimpleDoubleProperty();
    protected DoubleProperty pitch = new SimpleDoubleProperty();
    
    protected DoubleProperty SPEED = new SimpleDoubleProperty(10);

    public void move(final double x, final double y, final double z) {

        final double fx = Math.cos(Math.toRadians(this.yaw.get()));
        final double fy = -Math.sin(Math.toRadians(this.yaw.get()));
        final double fz = 1;

        this.x.set(this.x.get() + fx * x * SPEED.get());
        this.y.set(this.y.get() + fy * y * SPEED.get());
        this.z.set(this.z.get() + fz * z);
    }

    public void rotate(final double yaw, final double pitch) {
        this.yaw.set(this.yaw.get() + yaw);
        this.pitch.set(this.pitch.get() + pitch);
    }
    
}