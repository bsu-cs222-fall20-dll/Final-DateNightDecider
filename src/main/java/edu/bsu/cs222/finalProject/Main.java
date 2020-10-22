package edu.bsu.cs222.finalProject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter Address");
        String address = read.nextLine();
        GoogleConnector googleConnector = new GoogleConnector(address);
        LocationParser locationParser = new LocationParser(googleConnector.geocodeInputstream());

        if(locationParser.properInput()){
            System.out.println(locationParser.getLatLng());
        }
    }
}
