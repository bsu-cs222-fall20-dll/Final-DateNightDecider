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
public class DetailsParser {
    InputStream inputStream;
    JsonObject rootObject;

    public DetailsParser(InputStream inputStream){
        Reader reader = new InputStreamReader(inputStream);
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        this.rootObject = rootElement.getAsJsonObject();
        this.inputStream = inputStream;
    }

    public ArrayList<Review> getReviews(){
        ArrayList<Review> reviewsList = new ArrayList<>();
        JsonObject result = rootObject.get("result").getAsJsonObject();
        JsonArray reviews = result.get("reviews").getAsJsonArray();
        if(!reviews.isJsonNull()){
            for(JsonElement element : reviews) {
                JsonElement authorName = element.getAsJsonObject().get("author_name");
                JsonElement rating = element.getAsJsonObject().get("rating");
                JsonElement message = element.getAsJsonObject().get("text");
                String reviewAuthorName = authorName.getAsString();
                Integer reviewRating = rating.getAsInt();
                String reviewMessage = message.getAsString();
                Review newReview = new Review(reviewAuthorName, reviewRating, reviewMessage);
                reviewsList.add(newReview);
            }
        }
        return reviewsList;
    }
}
