package com.subutai.nova.arduino.ws.entities;

public class LedRequest {

    private int blue;
    private int red;
    private int green;

    public LedRequest() {
    }

    public LedRequest(int blue, int red, int green) {
        this.blue = blue;
        this.red = red;
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }
}
