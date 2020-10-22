package edu.bsu.cs222.finalProject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter address:");
        String address = read.nextLine();
        GeocodeConnector geocodeConnector = new GeocodeConnector(address);
        GeocodeParser geocodeParser = new GeocodeParser(geocodeConnector.geocodeInputstream());
        if(geocodeParser.properInput()){
            System.out.printf("Latitude:%s Longitude:%s", geocodeParser.getLatLng().getLatitude(), geocodeParser.getLatLng().getLongitude());
        }

    }
}
