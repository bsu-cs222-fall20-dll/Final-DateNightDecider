package edu.bsu.cs222.finalProject;

import java.util.Scanner;

public class Display
{
    public GeocodeParser prepareConnection(String address) throws Exception
    {
        GeocodeConnector geocodeConnector = new GeocodeConnector(address);
        return new GeocodeParser(geocodeConnector.geocodeInputstream());
    }

    public static void printPlaces(String latitude, String longitude, String type, String radius) throws Exception {
        Scanner read = new Scanner(System.in);
        PlaceConnector placeConnector = new PlaceConnector(latitude, longitude, type, radius);
        PlaceParser placeParser = new PlaceParser(placeConnector.placeInputstream());
        if(placeParser.getPlaceNames().size()>0)
        {
            for (Place place : placeParser.getPlaceNames())
            {
                System.out.println(place.getName());
            }
        }
        else
        {
            System.out.println("There were no results within that radius. Please enter a wider radius:"); //need to move this somewhere?
            radius = read.nextLine();

            printPlaces(latitude, longitude, type, radius);
        }
    }

}
