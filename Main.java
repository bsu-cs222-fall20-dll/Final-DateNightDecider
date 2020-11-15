package edu.bsu.cs222.finalProject;

import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/view.fxml"));
        primaryStage.setTitle("Date Night Decider");
        primaryStage.setScene(new Scene(root, 800, 550));
        primaryStage.show();
    }


    public static void main(String[] args)  { launch(args); }


    //I'm keeping vv just in case we need to test something and want to do that via console instead of GUI
    //Let me know if you want to just scrap it anyway -AB
    public void consoleStart() throws Exception {
        Display display = new Display();
        Scanner read = new Scanner(System.in);
        Input input = new Input(read);

        String address = input.getAddress();
        String radius = input.getRadius();
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
