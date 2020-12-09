package edu.bsu.cs222.finalProject;

public class Place {
    private final String name;
    private final String address;
    private final Integer priceLevel;
    private final String placeID;
    private final Integer rating;
    public Place(String name, String address, Integer priceLevel, String placeID, Integer rating) {
        this.name = name;
        this.address = address;
        this.priceLevel = priceLevel;
        this.placeID = placeID;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public Integer getPriceLevel() {
        return priceLevel;
    }
    public String getPlaceID() {
        return placeID;
    }
    public Integer getRating() {
        return rating;
    }
}
