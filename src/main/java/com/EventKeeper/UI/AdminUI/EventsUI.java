package com.EventKeeper.UI.AdminUI;

import com.EventKeeper.utility.ValidateEvent;
import com.EventKeeper.DAO.EventDAO;
import com.EventKeeper.DAO.Implementation.EventDaoImpl;
import com.EventKeeper.entity.Event;
import java.util.List;
import java.util.Scanner;

public class EventsUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EventDAO eventDAO = EventDaoImpl.getInstance();
    private static final ValidateEvent validateEvent = new ValidateEvent();

    public static void run() {
        int choice = 0;

        do {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t====================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t||  Welcome To Events Management  ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t====================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|1|    Get All Events             ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|2|    Search for Event           ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|3|    Add New Event              ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|4|    Update Event               ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|5|    Delete Event               ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|6|    Exit                       ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t====================================");
            try {
                System.out.print("\t\t\t\t\tEnter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    System.out.println("\t\t\t\t\tFetching all events...");
                        showEvents();
                    break;
                case 2:
                    System.out.println("\t\t\t\t\tSearching for an event...");
                        searchEvent();
                    break;
                case 3:
                    System.out.println("\t\t\t\t\tAdding a new event...");
                        addEvent();
                    break;
                case 4:
                    System.out.println("\t\t\t\t\tUpdating an event...");
                        updateEvent();
                    break;
                case 5:
                    System.out.println("\t\t\t\t\tDeleting an event...");
                        deleteEvent();
                    break;
                case 6:
                    System.out.println("\t\t\t\t\tExiting...");
                    break;
                default:
                    System.out.println("\t\t\t\t\tInvalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);
    }

    public static void showEvents() {
        try {
            List<Event> events = eventDAO.getEvents();
            if (!events.isEmpty()) {
                System.out.println("\t\t\t\t\tList of Events:");
               showEventsUI(events);
            } else {
                System.out.println("\t\t\t\t\t###########################");
                System.out.println("\t\t\t\t\t##   No events found :(  ##");
                System.out.println("\t\t\t\t\t###########################");

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
            System.out.println("\t\t\t\t\t###################################");
            System.out.println("\t\t\t\t\t##  Event Added Successfully :)  ##");
            System.out.println("\t\t\t\t\t###################################");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void updateEvent() {
        Event event = eventData();
        try {
            int id;
            while(true){
                try {
                    System.out.print("\t\t\t\t\tEnter Event ID: ");
                     id = scanner.nextInt();
                    scanner.nextLine();
                    break;
                }catch (Exception e){
                    scanner.nextLine();
                }
            }   
            boolean updated = eventDAO.updateEvent(id,event);
            if (updated){
                System.out.println("\t\t\t\t\t###################################");
                System.out.println("\t\t\t\t\t##  Event Added Successfully :)  ##");
                System.out.println("\t\t\t\t\t###################################");
            }else{
                System.out.println("\t\t\t\t\t#####################################");
                System.out.println("\t\t\t\t\t##  No Event Found With This ID :( ##");
                System.out.println("\t\t\t\t\t#####################################");
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
               System.out.println("\t\t\t\t\t###################################");
               System.out.println("\t\t\t\t\t##  Event Added Successfully :)  ##");
               System.out.println("\t\t\t\t\t###################################");
           }else{
               System.out.println("\t\t\t\t\t#####################################");
               System.out.println("\t\t\t\t\t##  No Event Found With This ID :( ##");
               System.out.println("\t\t\t\t\t#####################################");
           }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static  void searchEvent() {
        int choice = 0;
        do{
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t====================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t||    Search for Events Menu       ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t====================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|1|    Search By Type              ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|--------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|2|    Search By Date              ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|--------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|3|    Search By Location          ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|--------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|4|    Exit                        ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t====================================");
            try {
                System.out.print("\t\t\t\t\tEnter your choice: ");
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
                    System.out.println("\t\t\t\t\tInvalid choice. Please try again.");
                    break;
            }

        }while (choice != 4);
    }

    public static void searchEventByType() {
        System.out.print("\t\t\t\t\tEnter Type to search: ");
        String type = scanner.nextLine();
        try{
            List<Event> events = eventDAO.getEventsByType(type);
            if (!events.isEmpty()) {
                System.out.println("\t\t\t\t\tList of Events filtered by Type:");
                showEventsUI(events);
            } else {
                System.out.println("\t\t\t\t\t#########################");
                System.out.println("\t\t\t\t\t##  No Event Found  :( ##");
                System.out.println("\t\t\t\t\t#########################");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void searchEventByDate() {
        System.out.print("\t\t\t\t\tEnter Date to search: ");
        String Date = scanner.nextLine();
        try{
            List<Event> events = eventDAO.getEventsByDate(Date);
            if (!events.isEmpty()) {
                System.out.println("\t\t\t\t\tList of Events filtered by Date:");
               showEventsUI(events);
            } else {
                System.out.println("No events found.  :( ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void searchEventByLocation() {
        System.out.print("\t\t\t\t\tEnter location to search: ");
        String location = scanner.nextLine();
        try{
            List<Event> events = eventDAO.getEventsByLocalisation(location);
            if (!events.isEmpty()) {
                System.out.println("\t\t\t\t\tList of Events filtered by Localisation:");
                showEventsUI(events);
            } else {
                System.out.println("\t\t\t\t\t#########################");
                System.out.println("\t\t\t\t\t##  No Event Found  :( ##");
                System.out.println("\t\t\t\t\t#########################");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Event eventData() {
       
        // Title Input and Validation
        String title;
        while (true) {
            System.out.print("\t\t\t\t\tEnter Event title: ");
            title = scanner.nextLine();
            if (validateEvent.validateTitle(title)) {
                break;
            }
            System.out.println("\t\t\t\t\tTitle not valid. Please enter a valid Title.");
        }

        // Description Input and Validation
        String desc;
        while (true) {
            System.out.print("\t\t\t\t\tEnter Event description: ");
            desc = scanner.nextLine();
            if (validateEvent.validateDescription(desc)) {
                break;
            }
            System.out.println("\t\t\t\t\tDescription not valid. Please enter a valid Description.");
        }

        // Location Input and Validation
        String location;
        while (true) {
            System.out.print("\t\t\t\t\tEnter Event Location: ");
            location = scanner.nextLine();
            if (validateEvent.validateLocation(location)) {
                break;
            }
            System.out.println("\t\t\t\t\tLocation not valid. Please enter a valid Location.");
        }

        // Date Input and Validation
        String date;
        while (true) {
            System.out.print("\t\t\t\t\tEnter Event Date: ");
            date = scanner.nextLine();
            if (validateEvent.validateDate(date)) {
                break;
            }
            System.out.println("\t\t\t\t\tDate not valid. Please enter a valid Date.");
        }

        // Type Input and Validation
        String type;
        while (true) {
            System.out.print("\t\t\t\t\tEnter Event Type: ");
            type = scanner.nextLine();
            if (validateEvent.isValidType(type)) {
                break;
            }
            System.out.println("\t\t\t\t\tType not valid. Please enter a valid Type.");
        }

        return new Event(title, desc, location, date, type);
    }


    private static void showEventsUI(List<Event> events){
        System.out.println("\t\t\t\t\t-------------------------------------------------");
        for (Event event : events) {
            System.out.println("\t\t\t\t\t\tID: " + event.getId());
            System.out.println("\t\t\t\t\t\tTitle: "+ event.getTitle());
            System.out.println("\t\t\t\t\t\tDescription: " + event.getDescription());
            System.out.println("\t\t\t\t\t\tDate: "+ event.getDate());
            System.out.println("\t\t\t\t\t\tLocation: "+ event.getLocation());
            System.out.println("\t\t\t\t\t\tType: "+ event.getType());
            System.out.println("\t\t\t\t\t-------------------------------------------------");
        }
    }

   
}
