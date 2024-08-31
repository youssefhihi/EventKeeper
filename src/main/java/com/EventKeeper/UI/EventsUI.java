package com.EventKeeper.UI;

import com.EventKeeper.utility.ValidateEvent;
import com.EventKeeper.DAO.EventDAO;
import com.EventKeeper.DAO.Implementation.EventDaoImpl; // Ensure to import the correct implementation class
import com.EventKeeper.entity.Event;

import java.util.List;
import java.util.Scanner;

public class EventsUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EventDAO eventDAO = new EventDaoImpl(); // Properly initialize eventDAO
    private static final ValidateEvent validateEvent = new ValidateEvent(); // Initialize validateEvent

    public static void run() {
        int choice = 0;
        do {
            System.out.println("******************************************");
            System.out.println("****** Welcome To Events Management ******");
            System.out.println("******************************************");
            System.out.println("1. Get All Events");
            System.out.println("2. Search for event");
            System.out.println("3. Add new event");
            System.out.println("4. Update event");
            System.out.println("5. Delete event");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    System.out.println("Fetching all events...");
                        showEvents();
                    break;
                case 2:
                    System.out.println("Searching for an event...");
                        searchEvent();
                    break;
                case 3:
                    System.out.println("Adding a new event...");
                        addEvent();
                    break;
                case 4:
                    System.out.println("Updating an event...");
                        updateEvent();
                    break;
                case 5:
                    System.out.println("Deleting an event...");
                        deleteEvent();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 7);
    }

    public static void showEvents() {
        try {
            List<Event> events = eventDAO.getEvents();
            if (!events.isEmpty()) {
                System.out.println("List of Events:");
                System.out.println("-------------------------------------------------");
                for (Event event : events) {
                    System.out.println("ID: " + event.getId());
                    System.out.println("Title: "+ event.getTitle());
                    System.out.println("Description: " + event.getDescription());
                    System.out.println("Date: "+ event.getDate());
                    System.out.println("Location: "+ event.getLocation());
                    System.out.println("Type: "+ event.getType());
                    System.out.println("-------------------------------------------------");
                }
            } else {
                System.out.println("No events found.  :( ");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving events.");
            e.printStackTrace();
        }
    }


    public static void addEvent() {
        Event  event = eventData();
        try {
            eventDAO.addEvent(event);
            System.out.println("Event added successfully.  :)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void updateEvent() {
        Event event = eventData();
        try {
            boolean updated = eventDAO.updateEvent(event);
            if (updated){
                System.out.println("Event updated successfully.  :)");
            }else{
                System.out.println("ID for event not found ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteEvent() {
        System.out.print("Enter Event ID to update: ");
        int  id = scanner.nextInt();
        try{
           boolean deleted =  eventDAO.deleteEvent(id);
           if (deleted){
               System.out.println("Event deleted successfully.  :)");
           }else{
               System.out.println("ID for event not found ");
           }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static  void searchEvent() {
        int choice = 0;
        do{
            System.out.println("-------------------------------");
            System.out.println("| 1 |     search By Type       |");
            System.out.println("-------------------------------");
            System.out.println("| 2 |     search By Date       |");
            System.out.println("-------------------------------");
            System.out.println("| 3 |     search By Location   |");
            System.out.println("-------------------------------");
            System.out.println("| 4 |          Exit            |");
            System.out.println("-------------------------------");
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){
                scanner.nextLine();
            }
            switch (choice){
                case 1:
                    searchEventByType();
                    break;
                case 2:
                    searchEventByDate();
                    break;
                case 3:
                    searchEventByLocation();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }while (choice != 4);
    }

    public static void searchEventByType() {
        System.out.print("Enter Type to search: ");
        String type = scanner.nextLine();
        try{
            List<Event> events = eventDAO.getEventsByType(type);
            if (!events.isEmpty()) {
                System.out.println("List of Events filtered by Type:");
                System.out.println("-------------------------------------------------");
                for (Event event : events) {
                    System.out.println("ID: " + event.getId());
                    System.out.println("Title: "+ event.getTitle());
                    System.out.println("Description: " + event.getDescription());
                    System.out.println("Date: "+ event.getDate());
                    System.out.println("Location: "+ event.getLocation());
                    System.out.println("Type: "+ event.getType());
                    System.out.println("-------------------------------------------------");
                }
            } else {
                System.out.println("No events found.  :( ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void searchEventByDate() {
        System.out.print("Enter Date to search: ");
        String Date = scanner.nextLine();
        try{
            List<Event> events = eventDAO.getEventsByDate(Date);
            if (!events.isEmpty()) {
                System.out.println("List of Events filtered by Date:");
                System.out.println("-------------------------------------------------");
                for (Event event : events) {
                    System.out.println("ID: " + event.getId());
                    System.out.println("Title: "+ event.getTitle());
                    System.out.println("Description: " + event.getDescription());
                    System.out.println("Date: "+ event.getDate());
                    System.out.println("Location: "+ event.getLocation());
                    System.out.println("Type: "+ event.getType());
                    System.out.println("-------------------------------------------------");
                }
            } else {
                System.out.println("No events found.  :( ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void searchEventByLocation() {
        System.out.print("Enter location to search: ");
        String location = scanner.nextLine();
        try{
            List<Event> events = eventDAO.getEventsByLocalisation(location);
            if (!events.isEmpty()) {
                System.out.println("List of Events filtered by Localisation:");
                System.out.println("-------------------------------------------------");
                for (Event event : events) {
                    System.out.println("ID: " + event.getId());
                    System.out.println("Title: "+ event.getTitle());
                    System.out.println("Description: " + event.getDescription());
                    System.out.println("Date: "+ event.getDate());
                    System.out.println("Location: "+ event.getLocation());
                    System.out.println("Type: "+ event.getType());
                    System.out.println("-------------------------------------------------");
                }
            } else {
                System.out.println("No events found.  :( ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Event eventData() {
        System.out.print("Enter Event ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Title Input and Validation
        String title;
        while (true) {
            System.out.print("Enter Event title: ");
            title = scanner.nextLine();
            if (validateEvent.validateTitle(title)) {
                break;
            }
            System.out.println("Title not valid. Please enter a valid Title.");
        }

        // Description Input and Validation
        String desc;
        while (true) {
            System.out.print("Enter Event description: ");
            desc = scanner.nextLine();
            if (validateEvent.validateDescription(desc)) {
                break;
            }
            System.out.println("Description not valid. Please enter a valid Description.");
        }

        // Location Input and Validation
        String location;
        while (true) {
            System.out.print("Enter Event Location: ");
            location = scanner.nextLine();
            if (validateEvent.validateLocation(location)) {
                break;
            }
            System.out.println("Location not valid. Please enter a valid Location.");
        }

        // Date Input and Validation
        String date;
        while (true) {
            System.out.print("Enter Event Date: ");
            date = scanner.nextLine();
            if (validateEvent.validateDate(date)) {
                break;
            }
            System.out.println("Date not valid. Please enter a valid Date.");
        }

        // Type Input and Validation
        String type;
        while (true) {
            System.out.print("Enter Event Type: ");
            type = scanner.nextLine();
            if (validateEvent.isValidType(type)) {
                break;
            }
            System.out.println("Type not valid. Please enter a valid Type.");
        }

        return new Event(id, title, desc, location, date, type);
    }


    public static void main(String[] args) {
        EventsUI app = new EventsUI();
        app.run();
    }
}
