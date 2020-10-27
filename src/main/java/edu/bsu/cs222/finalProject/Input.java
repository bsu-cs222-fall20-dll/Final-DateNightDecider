package edu.bsu.cs222.finalProject;

import java.util.Scanner;

public class Input
{

    public String getAddress(Scanner read)
    {
        System.out.println("Enter address:"); //right now format is "STREETNAME CITY STATE", maybe split address into these three parts?
        return read.nextLine(); //might make it easier for the layman to understand -AB
    }

    public String getRadius(Scanner read)
    {
        System.out.println("Enter radius in miles:");
        return read.nextLine();
    }

    public String getPlaceType(Scanner read)
    {
        System.out.println("Enter place type:\n1.Restaurant\n2.Museum\n3.Bowling Alley\n4.Movie Theater");
        String numb = read.nextLine();
        return switch (numb) {
            case "1" -> "restaurant";
            case "2" -> "museum";
            case "3" -> "bowling_alley";
            case "4" -> "movie_theater";
            default -> " ";
        };
    }

}
