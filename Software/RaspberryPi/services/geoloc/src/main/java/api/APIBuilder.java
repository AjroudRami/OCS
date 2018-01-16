package api;

import com.google.maps.GeoApiContext;

public class APIBuilder implements GoogleAPIBuilder{

    @Override
    public API build(String api_key) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(api_key)
                .build();
        GoogleGeocodingAPI googleGeocodingAPI = new GoogleGeocodingAPI();
        googleGeocodingAPI.setGeoApiKey(api_key);
        googleGeocodingAPI.setGeoApiContext(context);
        return googleGeocodingAPI;
    }
}
