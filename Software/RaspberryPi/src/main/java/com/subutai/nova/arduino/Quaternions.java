package com.subutai.nova.arduino;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Quaternions {

    private float w;
    private float x;
    private float y;
    private float z;

    public Quaternions(){}

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public static Quaternions fromBytes(byte[] bytes) {
        Quaternions quaternions = new Quaternions();
        byte[] w = new byte[4];
        byte[] x = new byte[4];
        byte[] y = new byte[4];
        byte[] z = new byte[4];
        ByteArrayUtil.copy(bytes, w, 0, 0, 4);
        ByteArrayUtil.copy(bytes, x, 4, 0, 4);
        ByteArrayUtil.copy(bytes, y, 8, 0, 4);
        ByteArrayUtil.copy(bytes, z, 12,0, 4);

        quaternions.w = ByteBuffer.wrap(w).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        quaternions.x = ByteBuffer.wrap(x).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        quaternions.y = ByteBuffer.wrap(y).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        quaternions.z = ByteBuffer.wrap(z).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        return quaternions;
    }
}
