package edu.bsu.cs222.finalProject;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

@SuppressWarnings("deprecation") //Gets rid of warnings for soon-to-be obsolete classes in the API
public class GoogleMapsConnectorTest {
    @Test
    public void testGeocodeURLConverter() throws Exception{
        GeocodeConnector geocodeConnector = new GeocodeConnector("1401 West Neely Muncie IN");
        Assertions.assertEquals( new URL("https://maps.googleapis.com/maps/api/geocode/json?address=1401%20West%20Neely%20Muncie%20IN&key=AIzaSyB8dwWGPNjm7kqbrcj335AV2n4X8kYoKc4"), geocodeConnector.convertToGeocodeURL());
    }

    @Test
    public void testGeocodeInputstream() throws Exception {
        JsonParser parser = new JsonParser();
        GeocodeConnector geocodeConnector = new GeocodeConnector("1401 West Neely Muncie IN");
        Reader reader = new InputStreamReader(geocodeConnector.geocodeInputstream());
        JsonElement rootElement = parser.parse(reader);
        Assertions.assertEquals("OK", rootElement.getAsJsonObject().get("status").getAsString());
    }

    @Test
    public void testPlaceURLConverter() throws Exception {
        GeocodeConnector geocodeConnector = new GeocodeConnector("1401 West Neely Muncie IN");
        GeocodeParser geocodeParser = new GeocodeParser(geocodeConnector.geocodeInputstream());
        PlaceConnector placeConnector = new PlaceConnector(geocodeParser.getLatLng().getLatitude(), geocodeParser.getLatLng().getLongitude(), "restaurant", "1", "mcdonalds");
        Assertions.assertEquals( new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=mcdonalds&location=40.2033498,-85.40260479999999&type=restaurant&radius=1609.34&key=AIzaSyB8dwWGPNjm7kqbrcj335AV2n4X8kYoKc4"), placeConnector.convertToPlaceURL());
    }

    @Test
    public void testPlaceInputstream() throws Exception {
        JsonParser parser = new JsonParser();
        GeocodeConnector geocodeConnector = new GeocodeConnector("1401 West Neely Muncie IN");
        GeocodeParser geocodeParser = new GeocodeParser(geocodeConnector.geocodeInputstream());
        PlaceConnector placeConnector = new PlaceConnector(geocodeParser.getLatLng().getLatitude(), geocodeParser.getLatLng().getLongitude(), "restaurant","1", "mcdonalds");
        Reader reader = new InputStreamReader(placeConnector.placeInputstream());
        JsonElement rootElement = parser.parse(reader);
        Assertions.assertEquals("OK", rootElement.getAsJsonObject().get("status").getAsString());
    }

    @Test
    public void testDirectionURLConverter() throws Exception {
        GeocodeConnector geocodeConnector = new GeocodeConnector("1401 West Neely Muncie IN");
        GeocodeParser geocodeParser = new GeocodeParser(geocodeConnector.geocodeInputstream());
        PlaceConnector placeConnector = new PlaceConnector(geocodeParser.getLatLng().getLatitude(), geocodeParser.getLatLng().getLongitude(), "restaurant", "1", "");
        PlaceParser placeParser = new PlaceParser(placeConnector.placeInputstream());
        DirectionConnector directionConnector = new DirectionConnector(geocodeParser.getLatLng().getPlaceID(), placeParser.getPlaceNames().get(0).getPlaceID());
        Assertions.assertEquals(new URL("https://maps.googleapis.com/maps/api/directions/json?origin=ChIJ08wD1XM9FYgR8-Uvaf_1G7A&destination=ChIJKwVAmp49FYgRuZ7TJlBWmwY&key=AIzaSyB8dwWGPNjm7kqbrcj335AV2n4X8kYoKc4"), directionConnector.convertToDirectionURL());
    }
}