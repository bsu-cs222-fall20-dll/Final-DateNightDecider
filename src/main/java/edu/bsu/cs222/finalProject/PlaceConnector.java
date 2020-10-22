package edu.bsu.cs222.finalProject;

import java.io.InputStream;
import java.net.URL;

public class PlaceConnector {
    String apiKey = "AIzaSyB8dwWGPNjm7kqbrcj335AV2n4X8kYoKc4";
    String latitude;
    String longitude;
    String radius;

    public PlaceConnector(String latitude, String longitude, String radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public URL convertToPlaceURL() throws Exception {
        String latLng = latitude + " " + longitude;
        String convertedLatLng = latLng.replaceAll(" ", ",");
        return new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + convertedLatLng + "&radius=" + radius + "&key=" + apiKey);
    }

    public InputStream placeInputstream() throws Exception {
        java.net.URLConnection connection = convertToPlaceURL().openConnection();
        connection.setRequestProperty("User-Agent", "Place Tracker/0.1 (caslash@bsu.edu)");
        return connection.getInputStream();
    }

}
