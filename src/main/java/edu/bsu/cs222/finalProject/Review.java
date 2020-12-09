package edu.bsu.cs222.finalProject;

public class Review {
    private final String authorName;
    private final Integer rating;
    private final String message;

    public Review(String authorName, Integer rating, String message) {
        this.authorName = authorName;
        this.rating = rating;
        this.message = message;
    }

    public String getAuthorName() {
        return authorName;
    }
    public Integer getRating() {
        return rating;
    }
    public String getMessage() {
        return message;
    }
}
