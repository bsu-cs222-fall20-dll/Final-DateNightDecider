package edu.bsu.cs222.finalProject;

public class Main {
    public static void main(String[] args) throws Exception
    {
        Input input = new Input();
        Display display = new Display();

        String address = input.getAddress(); //was thinking about putting all these in one big getInputs method
        String radius = input.getRadius(); //but i don't know how/if you could return all these strings in one method?
        String placeType = input.getPlaceType();

        GeocodeParser geocodeParser = display.prepareConnection(address);

        prepareToPrintPlaces(geocodeParser, radius, placeType);

    }

    public static void prepareToPrintPlaces(GeocodeParser geocodeParser, String placeType, String radius) throws Exception
    {
        if(geocodeParser.properInput())
        {
            Display.printPlaces(geocodeParser.getLatLng().getLatitude(), geocodeParser.getLatLng().getLongitude(), placeType, radius);
        }
    }
}
