package edu.bsu.cs222.finalProject;

import com.google.gson.stream.JsonReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class JSONReaderTest {

    @Test
    public void testJsonReader() throws Exception {
        GoogleConnector googleConnector = new GoogleConnector("dfsahjkibsgdfhsdfghshjsh");
        LocationParser locationParser = new LocationParser(googleConnector.geocodeInputstream());
        Assertions.assertFalse(locationParser.properInput());
    }
}
