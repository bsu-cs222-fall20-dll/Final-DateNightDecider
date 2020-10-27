package edu.bsu.cs222.finalProject;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Input input = new Input();
        Display display = new Display();
        Scanner read = new Scanner(System.in);

        String address = input.getAddress(read); //for now i'll leave it like this
        String radius = input.getRadius(read); //but later on i'll move these into one big getInfo method and store the info in a new class -AB
        String placeType = input.getPlaceType(read);
        String keyword = input.getKeyword(read);

        GeocodeParser geocodeParser = display.prepareConnection(address);

        if(geocodeParser.checkForProperInput())
        {
            Display.printPlaces(geocodeParser.getLatLng().getLatitude(), geocodeParser.getLatLng().getLongitude(), placeType, radius, keyword);
            read.close();
        }

    }
}
