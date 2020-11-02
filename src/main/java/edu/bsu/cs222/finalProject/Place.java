package edu.bsu.cs222.finalProject;

public class Place {
    private final String name;
    private final String address;
    private final Integer priceLevel;
    private final String placeID;
    public Place(String name, String address, Integer priceLevel, String placeID) {
        this.name = name;
        this.address = address;
        this.priceLevel = priceLevel;
        this.placeID = placeID;
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
}
