package edu.bsu.cs222.finalProject;

import java.util.Scanner;

@SuppressWarnings("all") //Suppresses warnings about read being unused.
public class Input {
    public Scanner read;

    public Input(Scanner read) {
        this.read = new Scanner(System.in);
    }

    public String getAddress() {
        System.out.println("Enter address:"); //right now format is "STREETNAME CITY STATE", maybe split address into these three parts?
        return read.nextLine(); //might make it easier for the layman to understand -AB
    }

    public String getRadius() {
        System.out.println("Enter radius in miles:");
        return read.nextLine();
    }

    public String getPlaceType() {
        System.out.println("Enter place type:\n1.Restaurant\n2.Museum\n3.Bowling Alley\n4.Movie Theater\n5.Any type");
        String numb = read.nextLine();
        return switch (numb) {
            case "1" -> "restaurant";
            case "2" -> "museum";
            case "3" -> "bowling_alley";
            case "4" -> "movie_theater";
            case "5" -> "";
            default -> getPlaceType();
        };
    }

    public String getKeyword() {
        System.out.println("Enter a keyword you would like to filter by. Or enter 0 if you wouldn't like to:");
        String keyword = read.nextLine();
        return switch (keyword) {
            case "0" -> "";
            default -> keyword;
        };
    }

    public Integer getMinPriceLevel() {
        System.out.println("Enter the minimum price level you wish to filter by:");
        return Integer.parseInt(read.nextLine());
    }

    public Integer getMaxPriceLevel() {
        System.out.println("Enter the maximum price level you wish to filter by:");
        return Integer.parseInt(read.nextLine());
    }

    public Integer getDestination() {
        Integer destination = 0;
        System.out.println("Enter the place you would like to go to:");
        destination = Integer.parseInt(read.nextLine());
        return destination--;
    }
}
