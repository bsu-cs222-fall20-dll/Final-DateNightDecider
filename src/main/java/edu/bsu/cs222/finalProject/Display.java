package edu.bsu.cs222.finalProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Display {

    public GeocodeParser prepareConnection(String streetAddress, String city, String state) throws Exception {
        GeocodeConnector geocodeConnector = new GeocodeConnector(streetAddress, city, state);
        return new GeocodeParser(geocodeConnector.geocodeInputstream());
    }

    public static void printPlaces(String latitude, String longitude, String type, String radius, String keyword, Integer minimum, Integer maximum) throws Exception {
        Integer number = 1;
        Scanner read = new Scanner(System.in);
        Input input = new Input(read);

        PlaceConnector placeConnector = new PlaceConnector(latitude, longitude, type, radius, keyword);
        PlaceParser placeParser = new PlaceParser(placeConnector.placeInputstream());
        ArrayList<Place> filteredPlaceNames = placeParser.filterByPriceLevel(placeParser.getPlaceNames(), minimum, maximum);
        if(filteredPlaceNames.isEmpty()) {
            System.out.println("Sorry, there were no results within that radius.");
            radius = input.getRadius();
            printPlaces(latitude, longitude, type, radius, keyword, minimum, maximum);
        } else {
            System.out.println(placeConnector.convertToPlaceURL());
            for (Place place : filteredPlaceNames){
                System.out.printf("%d. %s - %s\n", number, place.getName(), place.getAddress());
                number++;
            }
        }
    }

    public static void printTravelTime(String travelTime, String placeName, String placeAddress) {
        System.out.printf("It will take approximately %s to get to %s at %s", travelTime, placeName, placeAddress);
    }
}
