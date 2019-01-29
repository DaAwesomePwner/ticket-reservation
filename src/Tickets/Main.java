package Tickets;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
        } catch (Exception e) {
            System.out.println("Error reading file. Error: " + e.getMessage());
        }

        columns = scanner.nextLine().trim().length() - 3; //length of array

        //store the lines of seating arrangements
        while (scanner.hasNextLine()) {
            scanner.nextInt();
            container.add(scanner.nextLine().trim());
        }
        System.out.println(container);

        //create a new matrix
        seating = new String[container.size()][container.get(0).replaceAll("\\s+", "").length()];

        //translating the read strings into a matrix
        for (int x = 0; x < container.size(); x++) {
            temp = container.get(x).split(" ");
            for (int i = 0; i < temp.length; i++) {
                seating[currentRow][i] = temp[i];
            }
            currentRow++;
        }

//        System.out.print("[");
//        for (int i = 0; i < seating[0].length; i++) {
//            System.out.print(seating[0][i] + ", ");
//        }
//        System.out.println("]");
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
        //print letters for seating chart
        String line = " ";
        System.out.println(seating[0].length);
        for (int x = 0; x < seating[0].length; x++) {
            line = line + " " + (char) (x + 65);
        }
        System.out.println(line);

        //print matrix
        for (int y = 0; y < seating.length; y++) {
            System.out.print(y + 1);
            for (int z = 0; z < seating[y].length; z++) {
                System.out.print(" " + seating[y][z]);
            }
            System.out.println();
        }

        //get information about seats
        scanner = new Scanner(System.in);
        int rowNum, childTicket, adultTicket, seniorTicket, totalTickets = 0;
        String seatLetter = "";

        //checks to make sure input and boundaries are valid
        while (true){
            System.out.println("Which row number did you want?");
            rowNum = scanner.nextInt();
            if (rowNum - 1 <= seating.length && rowNum >= 0)
                break;
            else
                System.out.println("Error: Invalid row number");
        }

        while (true){
            System.out.println("Which seat letter did you want?");
            seatLetter = scanner.next();
            if (seatLetter.toCharArray()[0] - 65 < seating[0].length && seatLetter.toCharArray()[0] - 65 >= 0)
                break;
            else
                System.out.println("Error: Invalid seat letter");
        }

        while (true){
            System.out.println("How many adult tickets?");
            adultTicket = scanner.nextInt();
            if (adultTicket > 0)
                break;
            else
                System.out.println("Error: Invalid amount of tickets");
        }

        while (true){
            System.out.println("How many child tickets?");
            childTicket = scanner.nextInt();
            if (childTicket > 0)
                break;
            else
                System.out.println("Error: Invalid amount of tickets");
        }

        while (true){
            System.out.println("How many senior tickets?");
            seniorTicket = scanner.nextInt();
            if (seniorTicket > 0)
                break;
            else
                System.out.println("Error: Invalid amount of tickets");
        }
        totalTickets = childTicket + adultTicket + seniorTicket;
        String[][] tempSeating = new String[seating.length][seating[0].length];
        Boolean invalid = false;
        for (int i = 0; i < seating.length; i++)
            tempSeating[i] = Arrays.copyOf(seating[i], seating[i].length);


        //check if seat(s) is/are valid
        for (int i = 0; i < totalTickets; i++) {
            if (seating[rowNum + i][seatLetter.toCharArray()[0] + i] != "#") {
                tempSeating[rowNum + i][seatLetter.toCharArray()[0] + i] = "#";
            }
            else
                invalid = true;
        }
        if (invalid == true)
            invalid();
        else {
            System.out.println("Tickets confirmed!");
            seating = tempSeating;
        }
        beginPrompts();

    }

    public static void invalid() {

    }

    public static void exit() {

    }
}
