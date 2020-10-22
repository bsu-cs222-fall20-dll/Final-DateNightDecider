package edu.bsu.cs222.finalProject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter address:");
        String address = read.nextLine();
        System.out.println("Enter radius in meters:");
        String radius = read.nextLine();
        System.out.println("Enter place type:");
        String type = read.nextLine();

        GeocodeConnector geocodeConnector = new GeocodeConnector(address);
        GeocodeParser geocodeParser = new GeocodeParser(geocodeConnector.geocodeInputstream());

        if(geocodeParser.properInput()){
            PlaceConnector placeConnector = new PlaceConnector(geocodeParser.getLatLng().getLatitude(), geocodeParser.getLatLng().getLongitude(), type, radius);
            PlaceParser placeParser = new PlaceParser(placeConnector.placeInputstream());
            for(Place place : placeParser.getPlaceNames()){
                System.out.println(place.getName());
            }
        }

    }
}
