import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Coordinates {

    private double x;
    private double y;
    private double z;

    public Coordinates() { }

    public Coordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setXYZfromRPY(double roll, double pitch, double yaw) {
        x = -cos(yaw) * sin(pitch) * sin(roll) - sin(yaw) * cos(roll);
        y = -sin(yaw) * sin(pitch) * sin(roll) + cos(yaw) * cos(roll);
        z = cos(pitch) * sin(roll);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Coordinates clone(){
        return new Coordinates(x, y ,z);
    }

    @Override
    public String toString(){
        return "x: " + x + " y: " + y + " z: " + z;
    }
}
