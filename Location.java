package edu.bsu.cs222.finalProject;

public class Location {
    private final String latitude;
    private final String longitude;
    private final String placeID;
    public Location(String latitude, String longitude, String placeID){
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeID = placeID;
    }

    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public String getPlaceID() {
        return placeID;
    }
}
