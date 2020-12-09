package edu.bsu.cs222.finalProject;

import java.io.InputStream;
import java.net.URL;

public class DetailsConnector {
    String apiKey = "AIzaSyB8dwWGPNjm7kqbrcj335AV2n4X8kYoKc4";
    String placeID;

    public DetailsConnector(String placeID) {
        this.placeID = placeID;
    }

    public URL convertToDetailsURL() throws Exception {
        return new URL("https://maps.googleapis.com/maps/api/place/details/json?place_id=" + placeID + "&fields=rating&key=" + apiKey);
    }

    public InputStream detailsInputstream() throws Exception {
        java.net.URLConnection connection = convertToDetailsURL().openConnection();
        connection.setRequestProperty("User-Agent", "Place Tracker/0.1 (caslash@bsu.edu)");
        return connection.getInputStream();
    }
}
