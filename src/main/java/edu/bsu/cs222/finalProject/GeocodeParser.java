package edu.bsu.cs222.finalProject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@SuppressWarnings("deprecation") //Gets rid of warnings for soon-to-be obsolete classes in the API
public class GeocodeParser {
    InputStream inputStream;
    JsonObject rootObject;
    public GeocodeParser(InputStream inputStream){
        Reader reader = new InputStreamReader(inputStream);
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        this.rootObject = rootElement.getAsJsonObject();
        this.inputStream = inputStream;
    }

    public Location getLatLng(){
        String lat = " ";
        String lng = " ";
        JsonArray results = rootObject.getAsJsonObject().get("results").getAsJsonArray();
        for(JsonElement element : results){
            if(element.getAsJsonObject().getAsJsonObject("geometry").isJsonObject()){
                JsonElement location = element.getAsJsonObject().get("geometry").getAsJsonObject().get("location");
                lat = location.getAsJsonObject().get("lat").getAsString();
                lng = location.getAsJsonObject().get("lng").getAsString();
            }
        }
        return new Location(lat, lng);
    }

    public boolean checkForProperInput(){
        return !rootObject.getAsJsonObject().get("status").getAsString().equals("ZERO_RESULTS"); //This returns true if the user inputs an address that exists.
    }
}
