package com.EventKeeper.UI.AdminUI;

import com.EventKeeper.utility.ValidateEvent;
import com.EventKeeper.entity.Event;
import com.EventKeeper.service.EventService;
import com.EventKeeper.service.impl.EventServiceImpl;

import java.util.List;
import java.util.Scanner;

public class EventsUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EventService eventService = new EventServiceImpl();
    private static final ValidateEvent validateEvent = new ValidateEvent();

    /**
     * Runs the events management UI, presenting the user with a menu of options to 
     * interact with events, including getting all events, searching for an event, 
     * adding a new event, updating an event, deleting an event, and exiting the UI.
     *
     * @return none
     */
    public static void run() {
        int choice = 0;

        do {
            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("║        Welcome To Events Management           ║");
            System.out.println("╠═══════════════════════════════════════════════╣");
            System.out.println("║  1  │  Get All Events                         ║");
            System.out.println("╠─────┼─────────────────────────────────────────╣");
            System.out.println("║  2  │  Search For An Event                    ║");
            System.out.println("╠─────┼─────────────────────────────────────────╣");
            System.out.println("║  3  │  Add A New Events                       ║");
            System.out.println("╠─────┼─────────────────────────────────────────╣");
            System.out.println("║  4  │  Update an Event                        ║");
            System.out.println("╠─────┼─────────────────────────────────────────╣");
            System.out.println("║  5  │  Delete an Event                        ║");
            System.out.println("╠─────┼─────────────────────────────────────────╣");
            System.out.println("║  6  │  Exit                                   ║");
            System.out.println("╚═══════════════════════════════════════════════╝");
            try {
                System.out.print("~~~> Please select an option: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    showLoading("Fetching all events");
                        showEvents();
                    break;
                case 2:
                    showLoading("Searching for an event");
                        searchEvent();
                    break;
                case 3:
                    showLoading("Adding a new event");
                        addEvent();
                    break;
                case 4:
                    showLoading("Updating an event");
                        updateEvent();
                    break;
                case 5:
                    showLoading("Deleting an event");
                        deleteEvent();
                    break;
                case 6:
                    showLoading("Exiting");
                    break;
                default:
                    System.out.println("[ERROR] Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);
    }

    /**
     * Displays a list of events to the console. If no events are found, it prints a message indicating that no events were found.
     *
     * @throws Exception if an error occurs while retrieving the list of events from the eventDAO
     */
    public static void showEvents() {
        try {
            List<Event> events = eventService.getEvents();
            if (!events.isEmpty()) {
                System.out.println("╔════════════════════════════════════════════════════╗");
                System.out.println("║                   List of Events                   ║");
                System.out.println("╚════════════════════════════════════════════════════╝");
               showEventsUI(events);
            } else {
                System.out.println("╔══════════════════════════════════╗");
                System.out.println("║   [INFO]    No Events Found      ║");
                System.out.println("╚══════════════════════════════════╝");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Adds an event 
     * @return          none
     */
    public static void addEvent() {
        Event  event = eventData();
        try {
            eventService.addEvent(event);
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║  [SUCCESS]    Event Added  Successfully    ║");
            System.out.println("╚════════════════════════════════════════════╝");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Updates an existing event.
     *
     * @return none
     */
    public static void updateEvent() {
        try {
            int id;
            while(true){
                try {
                    System.out.print("~~~>  Enter Event ID To Update: ");
                     id = scanner.nextInt();
                    scanner.nextLine();
                    break;
                }catch (Exception e){
                    scanner.nextLine();
                }
            }   
        Event event = eventData();
            boolean updated = eventService.updateEvent(id,event);
            if (updated){
                System.out.println("╔════════════════════════════════════════════╗");
                System.out.println("║  [SUCCESS]    Event Updated  Successfully  ║");
                System.out.println("╚════════════════════════════════════════════╝");
            }else{
                System.out.println("╔════════════════════════════════════════════╗");
                System.out.println("║  [ERROR]    No Event Found With This ID    ║");
                System.out.println("╚════════════════════════════════════════════╝");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Deletes an existing event based on the event ID.
     *
     * @return none
     */
    public static void deleteEvent() {
        System.out.print("~~~> 	Enter Event ID To Delete: ");
        int  id = scanner.nextInt();
        try{
           boolean deleted =  eventService.deleteEvent(id);
           if (deleted){
                System.out.println("╔════════════════════════════════════════════╗");
                System.out.println("║  [SUCCESS]    Event Deleted  Successfully  ║");
                System.out.println("╚════════════════════════════════════════════╝");
           }else{
                System.out.println("╔════════════════════════════════════════════╗");
                System.out.println("║  [ERROR]    No Event Found With This ID    ║");
                System.out.println("╚════════════════════════════════════════════╝");
           }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Searches for events based on user selection.
     *
     * @return none
     */
    public static  void searchEvent() {
        int choice = 0;
        do{
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║           Welcome to EventKeeper      ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║  1  │  Search By Type                 ║");
            System.out.println("╠─────┼─────────────────────────────────╣");
            System.out.println("║  2  │  Search By Date                 ║");
            System.out.println("╠─────┼─────────────────────────────────╣");
            System.out.println("║  3  │  Search By Location             ║");
            System.out.println("╠─────┼─────────────────────────────────╣");
            System.out.println("║  4  │  Exit                           ║");
            System.out.println("╚═══════════════════════════════════════╝");
            try {
                System.out.print("~~~> Please select an option: ");
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
                    System.out.println("[ERROR] Invalid choice. Please try again.");
                    break;
            }

        }while (choice != 4);
    }

    public static void searchEventByType() {
        System.out.print("~~> Enter Type to search: ");
        String type = scanner.nextLine();
        try{
            List<Event> events = eventService.getEventsByType(type);
            if (!events.isEmpty()) {
                System.out.println("╔════════════════════════════════════════════╗");
                System.out.println("║ [SUCCESS] List of Events filtered by Type  ║");
                System.out.println("╚════════════════════════════════════════════╝");
                showEventsUI(events);
            } else {
                System.out.println("╔════════════════════════════════════════════╗");
                System.out.println("║  [INFO] No events found with this type :(  ║");
                System.out.println("╚════════════════════════════════════════════╝");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void searchEventByDate() {
        System.out.print("~~> Enter Date to search: ");
        String Date = scanner.nextLine();
        try{
            List<Event> events = eventService.getEventsByDate(Date);
            if (!events.isEmpty()) {
                System.out.println("╔════════════════════════════════════════════╗");
                System.out.println("║ [SUCCESS] List of Events filtered by Date  ║");
                System.out.println("╚════════════════════════════════════════════╝");
                showEventsUI(events);
            } else {
                System.out.println("╔══════════════════════════════════════════╗");
                System.out.println("║  [INFO] No events found on this date :(  ║");
                System.out.println("╚══════════════════════════════════════════╝");}
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void searchEventByLocation() {
        System.out.print("~~> Enter location to search: ");
        String location = scanner.nextLine();
        try{
            List<Event> events = eventService.getEventsByLocalisation(location);
            if (!events.isEmpty()) {
                System.out.println("╔════════════════════════════════════════════════╗");
                System.out.println("║ [SUCCESS] List of Events filtered by Location  ║");
                System.out.println("╚════════════════════════════════════════════════╝");
                showEventsUI(events);
            } else {
                System.out.println("╔══════════════════════════════════════════════╗");
                System.out.println("║  [INFO] No events found in this location :(  ║");
                System.out.println("╚══════════════════════════════════════════════╝");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This function is used to create a new Event object by prompting the user to input the event's details.
     * It validates the user's input for title, description, location, date, and type.
     * 
     * @return  A new Event object with the user's input details.
     */
    private static Event eventData() {
       
        String title;
        while (true) {
            System.out.print("~~> 	Enter Event title: ");
            title = scanner.nextLine();
            if (validateEvent.validateTitle(title)) {
                break;
            }
            System.out.println("[ERROR] Title not valid. Please enter a valid Title.");
        }

        String desc;
        while (true) {
            System.out.print("~~> 	Enter Event description: ");
            desc = scanner.nextLine();
            if (validateEvent.validateDescription(desc)) {
                break;
            }
            System.out.println("[ERROR] Description not valid. Please enter a valid Description.");
        }

        // Location Input and Validation
        String location;
        while (true) {
            System.out.print("~~> 	Enter Event Location: ");
            location = scanner.nextLine();
            if (validateEvent.validateLocation(location)) {
                break;
            }
            System.out.println("[ERROR] Location not valid. Please enter a valid Location.");
        }

        // Date Input and Validation
        String date;
        while (true) {
            System.out.print("~~> 	Enter Event Date: ");
            date = scanner.nextLine();
            if (validateEvent.validateDate(date)) {
                break;
            }
            System.out.println("[ERROR] Date not valid. Please enter a valid Date.");
        }

        // Type Input and Validation
        String type;
        while (true) {
            System.out.print("~~> 	Enter Event Type: ");
            type = scanner.nextLine();
            if (validateEvent.isValidType(type)) {
                break;
            }
            System.out.println("[ERROR] Type not valid. Please enter a valid Type.");
        }

        return new Event(title, desc, location, date, type);
    }


    /**
     * Displays a list of events .
     *
     * @param  events	a list of Event objects to be displayed
     * @return        	none
     */
    private static void showEventsUI(List<Event> events) {       
        for (Event event : events) {
            System.out.println("╔═════════════════════════════════════════════╗");
            System.out.println("║ID        : " + event.getId());
            System.out.println("║Title     : " + event.getTitle());
            System.out.println("║Description: " + event.getDescription());
            System.out.println("║Date      : " + event.getDate());
            System.out.println("║Location  : " + event.getLocation());
            System.out.println("║Type      : " + event.getType());
            System.out.println("╚═════════════════════════════════════════════╝");
        }
        
        System.out.println("════════════════════════════════════════════════");
    }

    public static void showLoading(String message) {
        System.out.print(message);
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(600);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
