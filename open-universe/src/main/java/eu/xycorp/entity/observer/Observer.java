package eu.xycorp.entity.observer;

abstract class Observer {

    private double x;
    private double y;
    private double z;

    private double yaw, pitch;

    private double sensitivity = 0.5;
    private double speed = 20.0;

    Observer() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.yaw = 0.0;
        this.pitch = 0.0;
    }

    double getX() {
        return x;
    }

    void setX(final double x) {
        this.x = x;
    }

    double getY() {
        return y;
    }

    void setY(final double y) {
        this.y = y;
    }

    double getZ() {
        return z;
    }

    void setZ(final double z) {
        this.z = z;
    }

    double getYaw() {
        return yaw;
    }

    void setYaw(final double yaw) {
        this.yaw = yaw;
    }

    double getPitch() {
        return pitch;
    }

    void setPitch(final double pitch) {
        this.pitch = pitch;
    }

    double getSensitivity() {
        return sensitivity;
    }

    double getSpeed() {
        return speed;
    }

    // TODO convert to string

}