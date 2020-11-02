package edu.bsu.cs222.finalProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Display {

    public GeocodeParser prepareConnection(String address) throws Exception {
        GeocodeConnector geocodeConnector = new GeocodeConnector(address);
        return new GeocodeParser(geocodeConnector.geocodeInputstream());
    }

    public static void printPlaces(String latitude, String longitude, String type, String radius, String keyword, Integer minimum, Integer maximum) throws Exception {
        Scanner read = new Scanner(System.in);
        Input input = new Input(read);

        PlaceConnector placeConnector = new PlaceConnector(latitude, longitude, type, radius, keyword);
        PlaceParser placeParser = new PlaceParser(placeConnector.placeInputstream());
        if(placeParser.getPlaceNames().size()>0) {
            System.out.println(placeConnector.convertToPlaceURL());
            placeParser.filterPriceLevel(placeParser.getPlaceNames(), minimum, maximum);
        } else {
            System.out.println("Sorry, there were no results within that radius.");
            radius = input.getRadius();

            printPlaces(latitude, longitude, type, radius, keyword, minimum, maximum);
        }
    }
}
