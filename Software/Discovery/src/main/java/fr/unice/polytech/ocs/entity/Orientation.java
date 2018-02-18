package fr.unice.polytech.ocs.entity;

public class Orientation {

    private float yaw;
    private float pitch;
    private float roll;

    public Orientation() {
    }

    public Orientation(float yaw, float pitch, float roll) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public Orientation clone() {
        Orientation orientation = new Orientation();
        orientation.pitch = pitch;
        orientation.roll = roll;
        orientation.yaw = yaw;
        return orientation;
    }

    @Override
    public String toString() {
        return "Yaw: " + yaw + " Pitch: " + pitch + " Roll: " + roll;
    }
}
