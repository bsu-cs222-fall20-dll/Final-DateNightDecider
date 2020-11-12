package edu.bsu.cs222.finalProject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@SuppressWarnings("deprecation") //Gets rid of warnings for soon-to-be obsolete classes in the API
public class LocationParser {
    InputStream inputStream;
    public LocationParser (InputStream inputStream){
        this.inputStream = inputStream;
    }

    public boolean properInput(){
        Reader reader = new InputStreamReader(inputStream);
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        if(rootObject.getAsJsonObject().get("status").getAsString().equals("ZERO_RESULTS")){
            return false;
        }else{
            return true;
        }
    }
}
