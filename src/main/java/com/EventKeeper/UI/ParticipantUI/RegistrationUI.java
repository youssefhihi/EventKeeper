package com.EventKeeper.UI.ParticipantUI;

import com.EventKeeper.DAO.EventDAO;
import com.EventKeeper.DAO.Implementation.EventDaoImpl;
import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;
import com.EventKeeper.service.RegistrationService;
import com.EventKeeper.service.impl.RegistrationServiceImpl;

import java.util.List;
import java.util.Scanner;

public class RegistrationUI {
    private Participant participant;
    private static final Scanner scanner = new Scanner(System.in);
    private static final EventDAO eventDAO = EventDaoImpl.getInstance();
    private static final RegistrationService registrationService = new RegistrationServiceImpl();

    public RegistrationUI(Participant participant){
        this.participant = participant;
    }

    public  void run(){
        int choice = 0;
        do{
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("            Welcome " + this.participant.getUsername() + "!"    );
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║  1  │  Get All Events                            ║");
            System.out.println("╠─────┼────────────────────────────────────────────╣");
            System.out.println("║  2  │  Search for Event                          ║");
            System.out.println("╠─────┼────────────────────────────────────────────╣");
            System.out.println("║  3  │  Register for an Event                     ║");
            System.out.println("╠─────┼────────────────────────────────────────────╣");
            System.out.println("║  4  │  Unregister from an Event                  ║");
            System.out.println("╠─────┼────────────────────────────────────────────╣");
            System.out.println("║  5  │  Display Registered Events                 ║");
            System.out.println("╠─────┼────────────────────────────────────────────╣");
            System.out.println("║  6  │  Displaying Registeration of Event         ║");
            System.out.println("╠─────┼────────────────────────────────────────────╣");
            System.out.println("║  7  │  Logout                                    ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            
            try {
                System.out.print("~~~> Please select an option: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){
                scanner.nextLine();
            }
            switch (choice){
                case 1:
                    showLoading("Fetching All Events");
                    showEvents();
                    break;
                case 2:
                    showLoading("Searching For An Event");
                    searchEvent();
                    break;
                case 3:
                   showLoading("Registering For An Event");
                    registerToEvent();
                    break;
                case 4:
                    showLoading("Unregistering From An Event");
                    unregisterFromEvent();
                    break;
                case 5:
                    showLoading("Displaying Registered Events");
                    displayRegisteredEvents();
                    break;
                case 6:
                    showLoading("Displaying Registeration of Event");
                    displayRegistration();
                    break;
                default:
                    System.out.println("[ERROR] Invalid choice. Please try again.");
            }
        }while(choice != 7);
    }

        public static void showEvents() {
            try {
                List<Event> events = eventDAO.getEvents();
                if (!events.isEmpty()) {
                    System.out.println("╔═══════════════════════════════════╗");
                    System.out.println("║    [SUCCESS] All Events Found     ║");
                    System.out.println("╚═══════════════════════════════════╝");
                    showEventsUI(events);
                } else {
                    System.out.println("╔═══════════════════════════════════════════════╗");
                    System.out.println("║   [INFO] No events found. Please try later.   ║");
                    System.out.println("╚═══════════════════════════════════════════════╝");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void registerToEvent(){
            int id;
            while(true){
                try {
                    System.out.print("~~> Enter ID for event you want to register: ");
                     id = scanner.nextInt();
                    scanner.nextLine();
                    break;
                }catch (Exception e){
                    scanner.nextLine();
                }
            }   
            Event event = eventDAO.getEvent(id);
            if (event != null) {
               boolean registred =  registrationService.register(event,this.participant);
               if (registred) {
                   System.out.println("╔═══════════════════════════════════════════════════╗");
                   System.out.println("║ [Success] Successfully registered to an event! :) ║");
                   System.out.println("╚═══════════════════════════════════════════════════╝");
               }else{
                System.out.println("╔═══════════════════════════════════════════════════╗");
                System.out.println("║ [Error] You already registered to this event! :(  ║");
                System.out.println("╚═══════════════════════════════════════════════════╝");
                   
               }
            }else {
                System.out.println("╔══════════════════════════════════════════╗");
                System.out.println("║  [Error] No event found with this ID :(  ║");
                System.out.println("╚══════════════════════════════════════════╝");
            }
        }
        public void unregisterFromEvent(){
            System.out.print("~~> Enter ID for event you want to unregister: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Event event = eventDAO.getEvent(id);
            if (event != null) {
                registrationService.unregister(event.getId(),this.participant.getParticipantID());
            }else{
                System.out.println("╔══════════════════════════════════════════╗");
                System.out.println("║  [Error] No event found with this ID :(  ║");
                System.out.println("╚══════════════════════════════════════════╝");
            }
        }

        public void displayRegisteredEvents(){
            List<Event> events = registrationService.registration(this.participant.getParticipantID());
            if (!events.isEmpty()) {
                System.out.println("╔═══════════════════════════════════╗");
                System.out.println("║    [SUCCESS] All Events Found     ║");
                System.out.println("╚═══════════════════════════════════╝");
                showEventsUI(events);
            } else {
                System.out.println("╔═══════════════════════════════════════════════════╗");
                System.out.println("║  [INFO] You have not registered for any event :(  ║");
                System.out.println("╚═══════════════════════════════════════════════════╝");
            }
        }



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
                    showLoading("Searching Events by Type");
                    searchEventByType();
                    break;
                case 2:
                    showLoading("Searching Events by Date");
                    searchEventByDate();
                    break;
                case 3:
                    showLoading("Searching Events by Location");
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
        System.out.print("~~> Enter Type to search: ");
        String type = scanner.nextLine();
        try{
            List<Event> events = eventDAO.getEventsByType(type);
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
            List<Event> events = eventDAO.getEventsByDate(Date);
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
            List<Event> events = eventDAO.getEventsByLocalisation(location);
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

    private static void displayRegistration() {
        int id;
            while(true){
                try {
                    System.out.print("~~> Enter ID for event you want to register: ");
                     id = scanner.nextInt();
                    scanner.nextLine();
                    break;
                }catch (Exception e){
                    System.out.println("[ERROR] Invalid input. Please enter a valid ID.");
                    scanner.nextLine();
                }
            }   
        List <Participant> participants = registrationService.getListOfParticpant(id);
        if (!participants.isEmpty()) {
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println(  "            " + participants.size() + " Participants registred to this Event");
            System.out.println("╚══════════════════════════════════════════════════════════╝");
            for (Participant participant : participants) {
                System.out.println("╔════════════════════════════════════════════════════════╗");
                System.out.println("║    Username :  " + participant.getUsername());
                System.out.println("╚════════════════════════════════════════════════════════╝");
                System.out.println(); 
            }
        } else {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║ [INFO]  No participants registred in this event  ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
        }

    }

    private static void showEventsUI(List<Event> events) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║               List of Events                   ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
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
