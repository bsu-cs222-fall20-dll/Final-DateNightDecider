package edu.bsu.cs222.finalProject;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class GoogleMapsConnectorTest {

    @Test
    public void testURLConverter() throws Exception{
        GoogleConnector googleConnector = new GoogleConnector("1401 West Neely Muncie IN");
        Assertions.assertEquals( new URL("https://maps.googleapis.com/maps/api/geocode/json?address=1401%20West%20Neely%20Muncie%20IN&key=AIzaSyD6D-ZQXelCl7fAC6nMjxKXRMDOHGsVjaM"),googleConnector.convertToGeocodeURL());
    }
    @Test
    public void testGeocodeInputStream() throws Exception {
        JsonParser parser = new JsonParser();
        GoogleConnector googleConnector = new GoogleConnector("1401 West Neely Muncie IN");
        Reader reader = new InputStreamReader(googleConnector.geocodeInputstream());
        JsonElement rootElement = parser.parse(reader);
        Assertions.assertEquals("OK", rootElement.getAsJsonObject().get("status").getAsString());
    }
}