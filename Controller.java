package edu.bsu.cs222.finalProject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller
{
    public TextField streetNameBox;
    public TextField cityBox;
    public TextField stateInitialsBox;
    public TextField radiusBox;
    public TextField keywordBox;

    private ObservableList<String> placeTypes = FXCollections.observableArrayList("Restaurant", "Museum", "Bowling Alley", "Movie Theater");
    private ObservableList<Integer> priceLevels = FXCollections.observableArrayList(0,1,2,3,4);

    public ComboBox<String> placeTypeComboBox = new ComboBox<>();
    private ComboBox<Integer> minPriceLevelComboBox = new ComboBox<>();
    private ComboBox<Integer> maxPriceLevelComboBox = new ComboBox<>();

    public Button searchButton;

    Display display = new Display();



    public void initialize()
    {
        configureComboBoxes();
    }


    public void configureComboBoxes()
    {
        placeTypeComboBox.setItems(placeTypes);
    }

    public void searchPlaces() throws Exception
    {
        String address = streetNameBox.getText() + " " + cityBox.getText() + " " + stateInitialsBox.getText();
        GeocodeParser geocodeParser = display.prepareConnection(address);
        PlaceConnector placeConnector = new PlaceConnector(geocodeParser.getLocation().getLatitude(), geocodeParser.getLocation().getLongitude(), placeTypeComboBox.getValue(), radiusBox.getText(), keywordBox.getText());
        PlaceParser placeParser = new PlaceParser(placeConnector.placeInputstream());
    }


}
