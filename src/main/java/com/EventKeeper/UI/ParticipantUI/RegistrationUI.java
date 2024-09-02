package com.EventKeeper.UI.ParticipantUI;

import com.EventKeeper.DAO.EventDAO;
import com.EventKeeper.DAO.Implementation.EventDaoImpl;
import com.EventKeeper.DAO.Implementation.RegistrationDaoImp;
import com.EventKeeper.DAO.RegistrationDAO;
import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;
import java.util.List;
import java.util.Scanner;

public class RegistrationUI {
    private Participant participant;
    private static final Scanner scanner = new Scanner(System.in);
    private static final EventDAO eventDAO = EventDaoImpl.getInstance();
    private static final RegistrationDAO registrationDAO = new RegistrationDaoImp();

    public RegistrationUI(Participant participant){
        this.participant = participant;
    }

    public  void run(){
        int choice = 0;
        do{
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t====================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t||  Welcome  "+ this.participant.getUsername() +" ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t====================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|1|         Get All Events        ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|2|        Search for Event       ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|3|      register to an event     ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|4|     unregister to an event    ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|5|   display registered event    ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|6|            Logout             ||");
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
                    System.out.println("\t\t\t\t\tFetching all events...");
                    showEvents();
                    break;
                case 2:
                    System.out.println("\t\t\t\t\tSearching for an event...");
                    searchEvent();
                    break;
                case 3:
                    registerToEvent();
                    break;
                case 4:
                    unregisterFromEvent();
                    break;
                case 5:
                    displayRegisteredEvents();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("\t\t\t\t\tInvalid choice. Please try again.");
            }
        }while(choice != 6);
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

        public void registerToEvent(){
            System.out.print("\t\t\t\t\tEnter ID for event you want to register: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Event event = eventDAO.getEvent(id);
            if (event != null) {
               boolean registred =  registrationDAO.register(event,this.participant);
               if (registred) {
                   System.out.println("\t\t\t\t\tSuccessfully registered to an event");
               }else{
                   System.out.println("\t\t\t\t\tYou already registered to this event");
               }
            }else {
                System.out.println("\t\t\t\t\t######################################");
                System.out.println("\t\t\t\t\t##  No Event Found with this ID  :( ##");
                System.out.println("\t\t\t\t\t######################################");
            }
        }
        public void unregisterFromEvent(){
            System.out.print("\t\t\t\t\tEnter ID for event you want to unregister: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Event event = eventDAO.getEvent(id);
            if (event != null) {
                registrationDAO.unregister(event.getId(),this.participant.getId());
            }else{
                System.out.println("\t\t\t\t\t######################################");
                System.out.println("\t\t\t\t\t##  No Event Found with this ID  :( ##");
                System.out.println("\t\t\t\t\t######################################");
            }
        }

        public void displayRegisteredEvents(){
            List<Event> events = registrationDAO.registration(this.participant.getId());
            if (!events.isEmpty()) {
                System.out.println("\t\t\t\t\tList of Registered Events:");
                showEventsUI(events);
            } else {
                System.out.println("\t\t\t\t\t############################################");
                System.out.println("\t\t\t\t\t##  You have not registered any event  :( ##");
                System.out.println("\t\t\t\t\t############################################");
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

        private static void showEventsUI(List<Event> events){
            System.out.println("\t\t\t\t\t-------------------------------------------------");
            for (Event event : events) {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tID: " + event.getId());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTitle: "+ event.getTitle());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDescription: " + event.getDescription());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDate: "+ event.getDate());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tLocation: "+ event.getLocation());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tType: "+ event.getType());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t-------------------------------------------------");
            }
        }


}
