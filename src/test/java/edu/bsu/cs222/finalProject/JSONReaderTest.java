package edu.bsu.cs222.finalProject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.InputStream;

public class JSONReaderTest {

    @Test
    public void testJsonReader() throws Exception {
        GeocodeConnector geocodeConnector = new GeocodeConnector("dfsahjkibsgdfhsdfghshjsh", "ahbdgsifhdfhlabjdsfhjl", "fhyjbdasflkh");
        GeocodeParser geocodeParser = new GeocodeParser(geocodeConnector.geocodeInputstream());
        Assertions.assertFalse(geocodeParser.checkForProperInput());
    }

    @Test
    public void testGetLatitude() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Location.json");
        GeocodeParser geocodeParser = new GeocodeParser(inputStream);
        Assertions.assertEquals("40.2033498", geocodeParser.getLocation().getLatitude());
    }

    @Test
    public void testGetLongitude() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Location.json");
        GeocodeParser geocodeParser = new GeocodeParser(inputStream);
        Assertions.assertEquals("-85.40260479999999", geocodeParser.getLocation().getLongitude());
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

    @Test
    public void testGetTravelTime() throws Exception{
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Direction.json");
        DirectionParser directionParser = new DirectionParser(inputStream);
        Assertions.assertEquals("4 mins", directionParser.getTravelTime());
    }

    @Test
    public void testGetReviewName() throws Exception{
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Details.json");
        DetailsParser detailsParser = new DetailsParser(inputStream);
        Assertions.assertEquals("Whitley-Le'Ann Buck", detailsParser.getReviews().get(0).getAuthorName());
    }
}