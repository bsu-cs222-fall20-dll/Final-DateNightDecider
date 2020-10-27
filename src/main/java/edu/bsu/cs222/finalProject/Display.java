package edu.bsu.cs222.finalProject;

import java.util.Scanner;

public class Display {

    public GeocodeParser prepareConnection(String address) throws Exception {
        GeocodeConnector geocodeConnector = new GeocodeConnector(address);
        return new GeocodeParser(geocodeConnector.geocodeInputstream());
    }

    public static void printPlaces(String latitude, String longitude, String type, String radius, String keyword) throws Exception {
        Input input = new Input();
        Scanner read = new Scanner(System.in);

        PlaceConnector placeConnector = new PlaceConnector(latitude, longitude, type, radius, keyword);
        PlaceParser placeParser = new PlaceParser(placeConnector.placeInputstream());
        if(placeParser.getPlaceNames().size()>0) {
            System.out.println(placeConnector.convertToPlaceURL());
            for (Place place : placeParser.getPlaceNames())
            {
                System.out.printf("%s - %s\n", place.getName(), place.getAddress());
            }
        } else {
            System.out.println("Sorry, there were no results within that radius.");
            radius = input.getRadius(read);

            printPlaces(latitude, longitude, type, radius, keyword);
        }
    }
}
