package edu.bsu.cs222.finalProject;
import java.io.InputStream;
import java.net.URL;

public class GeocodeConnector {
    String apiKey = "AIzaSyB8dwWGPNjm7kqbrcj335AV2n4X8kYoKc4";
    String address;
    public GeocodeConnector(String address) {
        this.address = address;
    }

    public URL convertToGeocodeURL() throws Exception {
        String convertedAddress = address.replaceAll(" ", "%20");
        return new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + convertedAddress + "&key=" + apiKey);
    }

    //Connects to the above URL returned from convertToGeocodeURL() and returns an Inputstream for the GeocodeParser.
    public InputStream geocodeInputstream() throws Exception {
        java.net.URLConnection connection = convertToGeocodeURL().openConnection();
        connection.setRequestProperty("User-Agent", "Place Tracker/0.1 (caslash@bsu.edu)");
        return connection.getInputStream();
    }
}
