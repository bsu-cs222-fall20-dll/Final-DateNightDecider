package edu.bsu.cs222.finalProject;

import java.util.Scanner;

public class Input
{
    Scanner read = new Scanner(System.in);

    public String getAddress()
    {
        System.out.println("Enter address:");
        return read.nextLine();
    }

    public String getRadius()
    {
        System.out.println("Enter radius in miles:");
        return read.nextLine();
    }

    public String getPlaceType()
    {
        System.out.println("Enter place type:");
        return read.nextLine();
    }

}
