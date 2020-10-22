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
            printPlaces(geocodeParser.getLatLng().getLatitude(), geocodeParser.getLatLng().getLongitude(), type, radius);
        }
    }

    public static void printPlaces(String latitude, String longitude, String type, String radius) throws Exception {
        Scanner read = new Scanner(System.in);
        PlaceConnector placeConnector = new PlaceConnector(latitude, longitude, type, radius);
        PlaceParser placeParser = new PlaceParser(placeConnector.placeInputstream());
        if(placeParser.getPlaceNames().size()>0) {
            for (Place place : placeParser.getPlaceNames()) {
                System.out.println(place.getName());
            }
        }else{
            System.out.println("There were no results within that radius. Please enter a wider radius:");
            radius = read.nextLine();

            printPlaces(latitude, longitude, type, radius);
        }
    }
}
