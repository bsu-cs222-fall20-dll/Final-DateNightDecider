package edu.bsu.cs222.finalProject;

public class Place {
    private final String name;
    private final String address;
    private final Integer priceLevel;
    public Place(String name, String address, Integer priceLevel) {
        this.name = name;
        this.address = address;
        this.priceLevel = priceLevel;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public Integer getPriceLevel(){ return priceLevel; }
}
