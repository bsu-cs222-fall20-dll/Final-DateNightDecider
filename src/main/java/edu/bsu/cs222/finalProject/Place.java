package edu.bsu.cs222.finalProject;

public class Place {
    private String name;
    private String address;
    private Integer priceLevel;
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
