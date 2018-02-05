package com.subutai.nova.arduino.ws.entities;

public class BatteryState {
    private byte state;

    public BatteryState() {
    }

    public BatteryState(byte state) {
        this.state = state;
    }

    public static BatteryState fromBytes(byte[] bytes) {
        BatteryState res = new BatteryState(bytes[0]);
        return res;
    }

    public float getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}