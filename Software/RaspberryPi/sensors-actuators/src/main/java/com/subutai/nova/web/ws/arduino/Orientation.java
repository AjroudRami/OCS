package com.subutai.nova.web.ws.arduino;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Orientation {

    private float yaw;
    private float pitch;
    private float roll;

    public Orientation() {
    }

    public static Orientation fromBytes(byte[] bytes) {
        Orientation orientation = new Orientation();
        byte[] yawBuff = new byte[4];
        byte[] pitchBuff = new byte[4];
        byte[] rollBuff = new byte[4];
        ByteArrayUtil.copy(bytes, yawBuff, 0, 0, 4);
        ByteArrayUtil.copy(bytes, pitchBuff, 4, 0, 4);
        ByteArrayUtil.copy(bytes, rollBuff, 8, 0, 4);

        orientation.yaw = ByteBuffer.wrap(yawBuff).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        orientation.pitch = ByteBuffer.wrap(pitchBuff).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        orientation.roll = ByteBuffer.wrap(rollBuff).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        return orientation;
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
