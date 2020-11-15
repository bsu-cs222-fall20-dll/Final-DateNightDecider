package edu.bsu.cs222.finalProject;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
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

    public Button searchButton;


    public void searchPlaces()
    {
        String address = streetNameBox.getText() + " " + cityBox.getText() + " " + stateInitialsBox.getText();
        System.out.println(address);
    }

}
