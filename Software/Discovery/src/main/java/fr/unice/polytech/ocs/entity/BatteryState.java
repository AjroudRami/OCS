package fr.unice.polytech.ocs.entity;

public class BatteryState {
    private byte state;

    public BatteryState() {
    }

    public BatteryState(byte state) {
        this.state = state;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}