package edu.bsu.cs222.finalProject;

import java.net.URL;
import java.net.URLConnection;

public class DirectionConnector {
    String apiKey = "AIzaSyB8dwWGPNjm7kqbrcj335AV2n4X8kYoKc4";
    String originPlaceID;
    String destinationPlaceID;
    public DirectionConnector(String originPlaceID, String destinationPlaceID) {
        this.originPlaceID = originPlaceID;
        this.destinationPlaceID = destinationPlaceID;
    }

    public URL convertToDirectionURL() throws Exception {
        return new URL("https://maps.googleapis.com/maps/api/directions/json?origin=" + originPlaceID + "&destination=" + destinationPlaceID + "&key=" + apiKey);
    }

}
