package edu.bsu.cs222.finalProject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;

@SuppressWarnings("all")//Suppresses warnings about actionEvent not being used even though it is necessary
public class Controller {
    public TextField streetAddress;
    public TextField city;
    public TextField state;
    public TextField keyword;
    public ChoiceBox type;
    public VBox mainBox;
    public Spinner radius;
    public Spinner maxPrice;
    public Spinner minPrice;
    public Button submitButton;
    public Label travelTime;
    public TableView<Review> reviewTable;
    public VBox reviewVBox;

    public Boolean hasAddedTravelTime = false;
    public Boolean hasAddedReviewTable = false;


    public String convertType(){
        return switch (type.getValue().toString()) {
            case "bowling alley" -> "bowling_alley";
            case "movie theater" -> "movie_theater";
            case "any" -> "";
            default -> type.getValue().toString();
        };
    }

    public void displayPlaces(ActionEvent actionEvent) throws Exception {
        GeocodeConnector geocodeConnector = new GeocodeConnector(streetAddress.getText(), city.getText(), state.getText());
        GeocodeParser geocodeParser = new GeocodeParser(geocodeConnector.geocodeInputstream());
        PlaceConnector placeConnector = new PlaceConnector(geocodeParser.getLocation().getLatitude(), geocodeParser.getLocation().getLongitude(), convertType(), radius.getValue().toString(), keyword.getText());
        PlaceParser placeParser = new PlaceParser(placeConnector.placeInputstream());
        ArrayList<Place> filteredPlaceNames = placeParser.filterByPriceLevel(placeParser.getPlaceNames(), Integer.parseInt(minPrice.getValue().toString()) , Integer.parseInt(maxPrice.getValue().toString()));
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getChildren().clear();
        Label travel = new Label("Select a place from the table to view travel time along with its reviews");
        travel.setTextFill(Color.WHITE);
        travel.setWrapText(true);
        travel.setFont(new Font("System Bold", 18));
        mainBox.getChildren().add(travel);
        createTable(filteredPlaceNames, geocodeParser.getLocation().getPlaceID());
    }

    public void createTable(ArrayList<Place> places, String originPlaceID) {
        TableView<Place> outputTable = new TableView<>();

        TableColumn<Place, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Place, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Place, String> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        outputTable.getColumns().add(nameColumn);
        outputTable.getColumns().add(addressColumn);
        outputTable.getColumns().add(ratingColumn);

        ObservableList<Place> listOfPlaces = FXCollections.observableArrayList(places);
        outputTable.setItems(listOfPlaces);
        outputTable.setMinWidth(600);

        mainBox.getChildren().add(outputTable);
        mainBox.getChildren().add(reviewVBox);

        selectPlace(outputTable, originPlaceID);
    }

    public void selectPlace(TableView<Place> outputTable, String originPlaceID){
        outputTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Place>() {
            @Override
            public void changed(ObservableValue<? extends Place> observable, Place oldValue, Place newValue) {
                DetailsConnector detailsConnector = new DetailsConnector(newValue.getPlaceID());
                DetailsParser detailsParser = null;

                DirectionConnector directionConnector = new DirectionConnector(originPlaceID, newValue.getPlaceID());
                DirectionParser directionParser = null;

                try {
                    directionParser = new DirectionParser(directionConnector.directionInputstream());
                    detailsParser = new DetailsParser(detailsConnector.detailsInputstream());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String travelTimeString = String.format("It will take approximately %s to get to %s at %s", directionParser.getTravelTime(), newValue.getName(), newValue.getAddress());
                travelTime.setText(travelTimeString);
                if (!hasAddedTravelTime) {//checks to see if traveltime has been added yet, so it only happens once and just updates the text afterwards
                    travelTime.setTextFill(Color.WHITE);
                    travelTime.setWrapText(true);
                    travelTime.setFont(new Font("System Bold", 12));
                    mainBox.getChildren().add(travelTime);
                    hasAddedTravelTime = true;
                }

                try {
                    if (hasAddedReviewTable == true){
                        reviewVBox.getChildren().clear();
                        createReviewsTable(detailsParser.getReviews());
                    } else {
                        createReviewsTable(detailsParser.getReviews());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void createReviewsTable(ArrayList<Review> reviews) throws Exception {
        TableView<Review> reviewTable = new TableView<>();

        TableColumn<Review, String> nameColumn = new TableColumn<>("Author Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("authorName"));

        TableColumn<Review, String> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        TableColumn<Review, String> messageColumn = new TableColumn<>("Message");
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        reviewTable.getColumns().add(nameColumn);
        reviewTable.getColumns().add(ratingColumn);
        reviewTable.getColumns().add(messageColumn);
        reviewTable.setPrefWidth(600);

        ObservableList<Review> listOfReviews = FXCollections.observableArrayList(reviews);
        reviewTable.setItems(listOfReviews);



        reviewVBox.getChildren().add(reviewTable);
        hasAddedReviewTable = true;
    }
}
