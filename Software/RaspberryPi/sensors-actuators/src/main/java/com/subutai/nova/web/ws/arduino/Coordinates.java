package com.subutai.nova.web.ws.arduino;

import static java.lang.Math.*;


public class Coordinates {

    private static double RAD_TO_DEGREE = 57.295779513;

    private double x;
    private double y;
    private double z;

    private double lat = 0;
    private double lng = 0;

    public Coordinates() {
        x = 0;
        y = 0;
        z = 1;
    }

    public Coordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void calibrate() {
        x = 0;
        y = 0;
        z = 1;
    }

    public void setXYZfromRPY(double roll, double pitch, double yaw) {
        /*
        seems too easy to be right
        x = -cos(yaw) * sin(pitch) * sin(roll) - sin(yaw) * cos(roll);
        y = -sin(yaw) * sin(pitch) * sin(roll) + cos(yaw) * cos(roll);
        z = cos(pitch) * sin(roll);
        */
        double xR, yR, zR;
        xR = x * (cos(yaw) * cos(pitch))
                + y * (sin(yaw) * cos(pitch))
                - z * (sin(pitch));
        yR = x * (cos(yaw) * sin(pitch) * sin(roll) - sin(yaw) * cos(roll))
                + y * (sin(yaw) * sin(pitch) * sin(roll) + cos(yaw) * cos(roll))
                + z * (cos(pitch) * sin(roll));
        zR = x * (cos(yaw) * sin(pitch) * cos(roll) + sin(yaw) * sin(roll))
                + y * (sin(yaw) * sin(pitch) * cos(roll) - cos(yaw) * sin(roll))
                + z * (cos(pitch) * cos(roll));

        x = xR;
        y = yR;
        z = zR;
    }

    public void calcEarthCoordinates() {
        double theta, phi;
        theta = acos(z) * RAD_TO_DEGREE;
        phi = (x == 0) ? 0 : atan(y / x) * RAD_TO_DEGREE;

        lat = (phi <= 90) ?
                abs(phi - 90) :
                -(phi - 90);

        lng = (theta > 180) ?
                -(theta - 180) - 3 :
                theta + 3;
    }

    public Double[] getEarthCoordinates() {
        Double[] earthCoordinates = new Double[2];
        earthCoordinates[0] = lat;
        earthCoordinates[1] = lng;
        return earthCoordinates;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public Coordinates clone() {
        return new Coordinates(x, y, z);
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y + " z: " + z;
    }
}
