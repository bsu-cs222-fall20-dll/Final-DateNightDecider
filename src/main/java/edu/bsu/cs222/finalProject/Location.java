package edu.bsu.cs222.finalProject;

public class Location {
    private String latitude;
    private String longitude;
    public Location(String latitude, String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }
}
