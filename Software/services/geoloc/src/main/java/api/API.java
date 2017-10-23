package api;

import exception.ApiException;
import model.Location;

import java.io.IOException;

public interface API {
    Location[] getLocation(double lat, double lon) throws IOException, InterruptedException, ApiException;
}
