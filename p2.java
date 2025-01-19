import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

class Passenger implements Comparable<Passenger> {
    String name, ticketClass, startPlace, destination;
    int id, age, seatNumber;

    public Passenger(String name, int id, int age, String ticketClass, String startPlace, String destination,
            int seatNumber) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.ticketClass = ticketClass;
        this.startPlace = startPlace;
        this.destination = destination;
        this.seatNumber = seatNumber;
    }

    @Override
    public int compareTo(Passenger ob) {
        return this.name.compareTo(ob.name);
    }

    public void displayPassengerData() {
        System.out.printf("%-10s %-10d %-5d %-10s %-15s %-15s %-5d\n", name, id, age, ticketClass, startPlace,
                destination, seatNumber);
    }
}

class SortByID implements Comparator<Passenger> {
    public int compare(Passenger p1, Passenger p2) {
        return Integer.compare(p1.id, p2.id);
    }
}

class SortByAge implements Comparator<Passenger> {
    public int compare(Passenger p1, Passenger p2) {
        return Integer.compare(p1.age, p2.age);
    }
}

class SortByTicketClass implements Comparator<Passenger> {
    public int compare(Passenger p1, Passenger p2) {
        return p1.ticketClass.compareTo(p2.ticketClass);
    }
}

class SortByDestination implements Comparator<Passenger> {
    public int compare(Passenger p1, Passenger p2) {
        return p1.destination.compareTo(p2.destination);
    }
}

public class p2 {
    public static void main(String[] args) {
        Scanner SC = new Scanner(System.in);
        List<Passenger> passengers = new ArrayList<>();
        HashSet<Integer> uniqueIDs = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter details for Passenger " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = SC.next();
            System.out.print("Passenger ID: ");
            int id = SC.nextInt();
            // Ensure id is unique
            while (!uniqueIDs.add(id)) {
                System.out.print("Duplicate ID! Enter a unique Passenger ID: ");
                id = SC.nextInt();
            }
            System.out.print("Age: ");
            int age = SC.nextInt();
            System.out.print("Ticket Class: ");
            String ticketClass = SC.next();
            System.out.print("Start Place: ");
            String startPlace = SC.next();
            System.out.print("Destination: ");
            String destination = SC.next();
            System.out.print("Seat Number: ");
            int seatNumber = SC.nextInt();

            passengers.add(new Passenger(name, id, age, ticketClass, startPlace, destination, seatNumber));
        }
        // Sorting menu
        System.out.println("\nChoose attribute to sort by:");
        System.out.println("1. Name\n2. Passenger ID\n3. Age\n4. Ticket Class\n5. Destination");
        int choice = SC.nextInt();

        switch (choice) {
            case 1:
                Collections.sort(passengers);
                break;
            case 2:
                passengers.sort(new SortByID());
                break;
            case 3:
                passengers.sort(new SortByAge());
                break;
            case 4:
                passengers.sort(new SortByTicketClass());
                break;
            case 5:
                passengers.sort(new SortByDestination());
                break;
            default:
                System.out.println("Invalid choice!");
                SC.close();
                return;
        }

        // Display sorted data
        System.out.println("\nSorted Passenger Details:");
        System.out.printf("%-10s %-10s %-5s %-10s %-15s %-15s %-5s\n", "Name", "ID", "Age", "Class", "Start Place",
                "Destination", "Seat");
        for (Passenger p : passengers) {
            p.displayPassengerData();
        }
        SC.close();
    }
}