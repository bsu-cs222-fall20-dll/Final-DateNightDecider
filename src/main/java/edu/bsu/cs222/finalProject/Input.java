package edu.bsu.cs222.finalProject;

import java.util.Scanner;

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

}
