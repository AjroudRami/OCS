package model;

import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;

import java.util.Arrays;

public class Location {
    private String address;
    private String country;
    private String locality;

    public Location () {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public static Location fromGeocodingResult(GeocodingResult result){
        AddressComponent[] addressComponent = result.addressComponents;
        Location location = new Location();
        for(AddressComponent comp : addressComponent) {
            if (containsType(comp.types, AddressComponentType.COUNTRY)) {
                location.country = comp.longName;
            } else if (containsType(comp.types, AddressComponentType.LOCALITY)) {
                location.locality = comp.longName;
            }
        }
        location.address = result.formattedAddress;
        return location;
    }

    private static boolean containsType(AddressComponentType[] types, AddressComponentType searchType) {
        for (AddressComponentType type: types) {
            if (type.equals(searchType)) {
                return true;
            }
        }
        return false;
    }
}
