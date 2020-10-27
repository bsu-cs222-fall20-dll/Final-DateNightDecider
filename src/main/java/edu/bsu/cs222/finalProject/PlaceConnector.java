package edu.bsu.cs222.finalProject;

import java.io.InputStream;
import java.net.URL;

public class PlaceConnector {
    String apiKey = "AIzaSyB8dwWGPNjm7kqbrcj335AV2n4X8kYoKc4";
    String latitude;
    String longitude;
    String radius;
    String type;

    public PlaceConnector(String latitude, String longitude, String type, String radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.type = type;
    }

    public URL convertToPlaceURL() throws Exception {
        String convertedLatLng = latitude + "," + longitude;
        String metersRadius = String.valueOf(Double.parseDouble(radius) * 1609.34);
        return new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + convertedLatLng + "&type=" + type + "&radius=" + metersRadius + "&key=" + apiKey);
    }

    public InputStream placeInputstream() throws Exception {
        java.net.URLConnection connection = convertToPlaceURL().openConnection();
        connection.setRequestProperty("User-Agent", "Place Tracker/0.1 (caslash@bsu.edu)");
        return connection.getInputStream();
    }

}
