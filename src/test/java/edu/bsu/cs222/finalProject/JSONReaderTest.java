package edu.bsu.cs222.finalProject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.InputStream;

public class JSONReaderTest {

    @Test
    public void testJsonReader() throws Exception {
        GeocodeConnector geocodeConnector = new GeocodeConnector("dfsahjkibsgdfhsdfghshjsh");
        GeocodeParser geocodeParser = new GeocodeParser(geocodeConnector.geocodeInputstream());
        Assertions.assertFalse(geocodeParser.checkForProperInput());
    }

    @Test
    public void testGetLatitude() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Location.json");
        GeocodeParser geocodeParser = new GeocodeParser(inputStream);
        Assertions.assertEquals("40.2033498", geocodeParser.getLatLng().getLatitude());
    }

    @Test
    public void testGetLongitude() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Location.json");
        GeocodeParser geocodeParser = new GeocodeParser(inputStream);
        Assertions.assertEquals("-85.40260479999999", geocodeParser.getLatLng().getLongitude());
    }

    @Test
    public void testGetNames() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Places.json");
        PlaceParser placeParser = new PlaceParser(inputStream);
        Assertions.assertEquals("Amazing Joe's Grill", placeParser.getPlaceNames().get(0).getName());
    }


    @Test
    public void testFilterPriceLevel() throws Exception{
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Places.json");
        PlaceParser placeParser = new PlaceParser(inputStream);
        Assertions.assertEquals("Subway", placeParser.filterByPriceLevel(placeParser.getPlaceNames(), 0, 1).get(0).getName());
    }
}