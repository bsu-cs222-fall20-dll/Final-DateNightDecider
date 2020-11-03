package edu.bsu.cs222.finalProject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@SuppressWarnings("deprecation") //Gets rid of warnings for soon-to-be obsolete classes in the API
public class DirectionParser {
    InputStream inputStream;
    JsonObject rootObject;

    public DirectionParser(InputStream inputStream){
        Reader reader = new InputStreamReader(inputStream);
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        this.rootObject = rootElement.getAsJsonObject();
        this.inputStream = inputStream;
    }

    public String getTravelTime(){
        String travelTime = " ";
        JsonArray routes = rootObject.getAsJsonObject().get("routes").getAsJsonArray();
        for(JsonElement element : routes){
            if(element.getAsJsonObject().getAsJsonArray("legs").isJsonArray()){
                JsonArray legs = element.getAsJsonObject().getAsJsonArray("legs");
                for(JsonElement element1 : legs){
                    if(element1.getAsJsonObject().isJsonObject()){
                        travelTime = element1.getAsJsonObject().getAsJsonObject("duration").get("text").getAsString();
                    }
                }
            }
        }
        return travelTime;
    }
}
