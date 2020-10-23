package edu.bsu.cs222.finalProject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter address:");
        String address = read.nextLine();
        System.out.println("Enter radius in miles:");
        String radius = read.nextLine();
        System.out.println("Enter place type:\n1.Restaurant\n2.Museum\n3.Bowling Alley\n4.Movie Theatre");
        String numb = read.nextLine();
        String type = switch (numb) {
            case "1" -> "restaurant";
            case "2" -> "museum";
            case "3" -> "bowling_alley";
            case "4" -> "movie_theater";
            default -> " ";
        };


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
            System.out.println(placeConnector.convertToPlaceURL());
            for (Place place : placeParser.getPlaceNames()) {
                System.out.printf("%s - %s\n", place.getName(), place.getAddress());
            }
        }else{
            System.out.println("There were no results within that radius. Please enter a wider radius:");
            radius = read.nextLine();

            printPlaces(latitude, longitude, type, radius);
        }
    }
}
