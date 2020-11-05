package edu.bsu.cs222.finalProject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Display display = new Display();
        Scanner read = new Scanner(System.in);
        Input input = new Input(read);

        String address = input.getAddress(); //for now i'll leave it like this
        String radius = input.getRadius(); //but later on i'll move these into one big getInfo method and store the info in a new class -AB
        String placeType = input.getPlaceType();
        String keyword = input.getKeyword();
        Integer minimum = input.getMinPriceLevel();
        Integer maximum = input.getMaxPriceLevel();
        GeocodeParser geocodeParser = display.prepareConnection(address);

        PlaceConnector placeConnector = new PlaceConnector(geocodeParser.getLocation().getLatitude(), geocodeParser.getLocation().getLongitude(), placeType, radius, keyword);
        PlaceParser placeParser = new PlaceParser(placeConnector.placeInputstream());


        if(geocodeParser.checkForProperInput()) {
            Display.printPlaces(geocodeParser.getLocation().getLatitude(), geocodeParser.getLocation().getLongitude(), placeType, radius, keyword, minimum, maximum);
        }

        Integer destination = input.getDestination(); // User inputs desired destination
        String placePlaceID = placeParser.getPlaceNames().get(destination).getPlaceID(); // Get the place's placeID
        String placeName = placeParser.getPlaceNames().get(destination).getName();
        String originPlaceID = geocodeParser.getLocation().getPlaceID(); // Get the origin's placeID

        DirectionConnector directionConnector = new DirectionConnector(originPlaceID, placePlaceID);
        DirectionParser directionParser = new DirectionParser(directionConnector.directionInputstream());

        Display.printTravelTime(directionParser.getTravelTime(), placeName);

        read.close();
    }
}
