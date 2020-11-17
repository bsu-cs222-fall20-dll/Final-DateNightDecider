package edu.bsu.cs222.finalProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Controller
{
    public TextField streetNameBox;
    public TextField cityBox;
    public TextField stateInitialsBox;
    public TextField radiusBox;
    public TextField keywordBox;

    public ObservableList<String> placeTypes = FXCollections.observableArrayList("Restaurant", "Museum", "Bowling Alley", "Movie Theater");
    public ObservableList<Integer> priceLevels = FXCollections.observableArrayList(0,1,2,3,4);

    public ComboBox<String> placeTypeComboBox = new ComboBox<>();
    public ComboBox<Integer> minPriceLevelComboBox = new ComboBox<>();
    public ComboBox<Integer> maxPriceLevelComboBox = new ComboBox<>();

    public VBox displayVBox;

    public Button searchButton;

    Display display = new Display();



    public void initialize()
    {
        configureComboBoxes();
    }


    public void configureComboBoxes()
    {
        placeTypeComboBox.setItems(placeTypes);
        placeTypeComboBox.getSelectionModel().selectFirst();
        minPriceLevelComboBox.setItems(priceLevels);
        minPriceLevelComboBox.getSelectionModel().selectFirst();
        maxPriceLevelComboBox.setItems(priceLevels);
        maxPriceLevelComboBox.getSelectionModel().selectFirst();
    }

    public void searchPlaces() throws Exception
    {
        displayVBox.getChildren().clear();
        checkForValidPriceLevels();
        String address = streetNameBox.getText() + " " + cityBox.getText() + " " + stateInitialsBox.getText();
        convertPlaceTypeForURL();

        GeocodeParser geocodeParser = display.prepareConnection(address);

        PlaceConnector placeConnector = new PlaceConnector(geocodeParser.getLocation().getLatitude(), geocodeParser.getLocation().getLongitude(), placeTypeComboBox.getValue(), radiusBox.getText(), keywordBox.getText());
        PlaceParser placeParser = new PlaceParser(placeConnector.placeInputstream());

        if (geocodeParser.checkForProperInput())
        {
            displayPlaces(placeParser);
        }
    }

    public void displayPlaces(PlaceParser placeParser)
    {
        Integer number = 1;

        ArrayList<Place> filteredPlaceNames = placeParser.filterByPriceLevel(placeParser.getPlaceNames(), minPriceLevelComboBox.getValue(), maxPriceLevelComboBox.getValue());
        if(filteredPlaceNames.isEmpty()) {
            Label noResultsInRadius = new Label("Sorry, no results were found in the specified radius.");
            displayVBox.getChildren().add(noResultsInRadius);
        } else {
            for (Place place : filteredPlaceNames)
            {
                Label displayInformation = new Label(String.format("%d. %s - %s\n", number, place.getName(), place.getAddress()));
                displayVBox.getChildren().add(displayInformation);
                number++;
            }
        }
    }


    public void checkForValidPriceLevels()
    {
        if (minPriceLevelComboBox.getValue() > maxPriceLevelComboBox.getValue())
        {
            maxPriceLevelComboBox.setValue(minPriceLevelComboBox.getValue()); //if minimum price is set higher than maximum price
        }                                                                       //set max price to be equal to minimum price
    }

    public void convertPlaceTypeForURL()
    {
        switch(placeTypeComboBox.getValue())
                {
                    case "Restaurant" -> placeTypeComboBox.setValue("restaurant");
                    case "Museum" -> placeTypeComboBox.setValue("museum");
                    case "Bowling Alley" -> placeTypeComboBox.setValue("bowling_alley");
                    case "Movie Theater" -> placeTypeComboBox.setValue("movie_theater");
                }
    }
}
