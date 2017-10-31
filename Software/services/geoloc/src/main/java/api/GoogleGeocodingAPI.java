package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import model.Location;

import javax.ejb.Local;
import java.io.IOException;

public class GoogleGeocodingAPI implements API {
    private String API_KEY;
    private GeoApiContext geoApiContext;

    GoogleGeocodingAPI(){
    }

    @Override
    public Location[] getLocation(double lat, double lon) throws IOException, InterruptedException, exception.ApiException {
        GeocodingResult[] results;
        try {
            results = GeocodingApi.reverseGeocode(geoApiContext, new LatLng(lat, lon)).await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(results[0].addressComponents[0]));
            Location[] locs = new Location[results.length];
            for (int i = 0; i < results.length; i++) {
                locs[i] = new Location(results[i].formattedAddress);
            }
            return locs;
        } catch (ApiException e) {
            e.printStackTrace();
            throw new exception.ApiException(e.getMessage());
        }
    }

    void setGeoApiContext(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    void setGeoApiKey(String apiKey){
        this.API_KEY = apiKey;
    }
}
