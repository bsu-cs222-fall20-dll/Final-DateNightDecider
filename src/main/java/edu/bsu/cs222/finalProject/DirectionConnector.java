package edu.bsu.cs222.finalProject;

import java.io.InputStream;
import java.net.URL;

public class DirectionConnector {
    String apiKey = "AIzaSyB8dwWGPNjm7kqbrcj335AV2n4X8kYoKc4";
    String originPlaceID;
    String destinationPlaceID;

    public DirectionConnector(String originPlaceID, String destinationPlaceID) {
        this.originPlaceID = originPlaceID;
        this.destinationPlaceID = destinationPlaceID;
    }

    public URL convertToDirectionURL() throws Exception {
        return new URL("https://maps.googleapis.com/maps/api/directions/json?origin=place_id:" + originPlaceID + "&destination=place_id:" + destinationPlaceID + "&key=" + apiKey);
    }

    public InputStream directionInputstream() throws Exception {
        java.net.URLConnection connection = convertToDirectionURL().openConnection();
        connection.setRequestProperty("User-Agent", "Place Tracker/0.1 (caslash@bsu.edu)");
        return connection.getInputStream();
    }

}
