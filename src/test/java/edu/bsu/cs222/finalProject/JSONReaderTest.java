package edu.bsu.cs222.finalProject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class JSONReaderTest {

    @Test
    public void testJsonReader() throws Exception {
        GeocodeConnector geocodeConnector = new GeocodeConnector("dfsahjkibsgdfhsdfghshjsh");
        GeocodeParser geocodeParser = new GeocodeParser(geocodeConnector.geocodeInputstream());
        Assertions.assertFalse(geocodeParser.properInput());
    }

    @Test
    public void testGetLatitude() throws Exception {
        GeocodeConnector geocodeConnector = new GeocodeConnector("1401 West Neely Muncie IN");
        GeocodeParser geocodeParser = new GeocodeParser(geocodeConnector.geocodeInputstream());
        Assertions.assertEquals("40.2033498", geocodeParser.getLatLng().getLatitude());
    }

    @Test
    public void testGetLongitude() throws Exception {
        GeocodeConnector geocodeConnector = new GeocodeConnector("1401 West Neely Muncie IN");
        GeocodeParser geocodeParser = new GeocodeParser(geocodeConnector.geocodeInputstream());
        Assertions.assertEquals("-85.40260479999999", geocodeParser.getLatLng().getLongitude());
    }

    @Test
    public void testGetNames() throws Exception {
        GeocodeConnector geocodeConnector = new GeocodeConnector("1401 West Neely Muncie IN");
        GeocodeParser geocodeParser = new GeocodeParser(geocodeConnector.geocodeInputstream());
        PlaceConnector placeConnector = new PlaceConnector(geocodeParser.getLatLng().getLatitude(), geocodeParser.getLatLng().getLongitude(), "restaurant", "1");
        PlaceParser placeParser = new PlaceParser(placeConnector.placeInputstream());
        Assertions.assertEquals("Amazing Joe's Grill", placeParser.getPlaceNames().get(0).getName());
    }
}