package Tickets;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Bora Gomez on 1/22/19.
 * Name: Bora Gomez
 * UTD ID: BXG180011
 */
public class Main {
    private static Scanner scanner;
    private static String[][] seating;
    private static Ticket ticket;

    public static void main(String[] args) {
        readFile();
        ticket = new Ticket(seating);
        beginPrompts();
    }

    //method to read the file and create a seating chart
    public static void readFile() {
        //intialize the variables
        int columns = 0;
        int rows = 0;
        seating = new String[0][0];
        String[] temp;
        ArrayList<String> container = new ArrayList<String>();
        int currentRow = 0;

        //read the file and check for errors while doing so
        try {
            scanner = new Scanner(new File("a1.txt"));
        } catch (Exception e) {System.out.println("Error reading file. Error: " + e.getMessage());}

        columns = scanner.nextLine().trim().length() - 3; //length of array

        //store the lines of seating arrangements
        while (scanner.hasNextLine()) {
            scanner.nextInt();
            container.add(scanner.nextLine().trim());
        }
        System.out.println(container);

        //create a new matrix
        seating = new String[container.size()][container.get(0).length()];

        //translating the read strings into a matrix
        for (int x = 0; x < container.size(); x++) {
            temp = container.get(x).split(" ");
            for (int i = 0; i < temp.length; i++) {
                seating[currentRow][i] = temp[i];
            }
            currentRow++;
        }
        System.out.println(seating[0][17]);

    }

    //asking the user for what they want to do
    public static void beginPrompts() {
        scanner = new Scanner(System.in);
        System.out.println("1. Reserve Seats\n" +
                "2. Exit");
        int selection = scanner.nextInt();
        switch (selection) {
            case 1:
                reserveSeats();
                break;
            case 2:
                exit();
                break;
        }
    }

    //method to reserve seats should the user choose to do so
    public static void reserveSeats() {
        //print current seating chart
        String line = "   ";
        for (int x = 0; x < seating[0].length; x++) {
            line += "" + (char)(x + 65);
        }
        System.out.println(line);


    }

    public static void exit() {

    }
}
