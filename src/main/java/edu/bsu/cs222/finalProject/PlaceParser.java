package edu.bsu.cs222.finalProject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

@SuppressWarnings("deprecation") //Gets rid of warnings for soon-to-be obsolete classes in the API
public class PlaceParser {
    InputStream inputStream;
    JsonObject rootObject;
    public PlaceParser(InputStream inputStream){
        Reader reader = new InputStreamReader(inputStream);
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        this.rootObject = rootElement.getAsJsonObject();
        this.inputStream = inputStream;
    }



    public ArrayList<Place> getPlaceNames(){
        ArrayList<Place> placeNames = new ArrayList<>();
        JsonArray results = rootObject.getAsJsonObject().get("results").getAsJsonArray();
        for (JsonElement element : results) {
            if (element.getAsJsonObject().has("price_level")) {
                JsonElement name = element.getAsJsonObject().get("name");
                JsonElement address = element.getAsJsonObject().get("vicinity");
                JsonElement priceLevel = element.getAsJsonObject().get("price_level");
                JsonElement ID = element.getAsJsonObject().get("place_id");
                String placeName = name.getAsString();
                String placeAddress = address.getAsString();
                Integer placePriceLevel = priceLevel.getAsInt();
                String placeID = ID.getAsString();
                Place newPlace = new Place(placeName, placeAddress, placePriceLevel, placeID);
                placeNames.add(newPlace);
            }
        }
        return placeNames;
    }

    public ArrayList<Place> filterByPriceLevel(ArrayList<Place> placeList, Integer minimum, Integer maximum) {
        ArrayList<Place> placesToRemove = new ArrayList<>();
        for (Place place : placeList){
            if(place.getPriceLevel() != null){
                Integer priceLevel = place.getPriceLevel();
                if (priceLevel > maximum || priceLevel < minimum){
                    placesToRemove.add(place);
                }
            }else{
                return null;
            }
        }

        if(minimum != null && maximum != null){
            placeList.removeAll(placesToRemove);
        }

        return placeList;
    }
}
