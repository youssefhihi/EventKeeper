package com.EventKeeper.UI.AdminUI;

import java.util.List;
import java.util.Scanner;

import com.EventKeeper.DAO.AdminDAO;
import com.EventKeeper.DAO.Implementation.AdminDaoImpl;
import com.EventKeeper.entity.Registration;
import com.EventKeeper.utility.ValidateUser;

public class Reports {
    private static Scanner scanner = new Scanner(System.in);
    private static AdminDAO adminDAO = new AdminDaoImpl();

    /**
     * Runs the reports management system, presenting the user with a menu to select
     * from various report options or to log out.
     *
     * @return          none
     */
    public static void run(){
        int choice = 0 ;
        do{
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║             Welcome To Reports Management        ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║ 1. Participants Report                           ║");
            System.out.println("╠──────────────────────────────────────────────────╣");
            System.out.println("║ 2. Events Report                                 ║");
            System.out.println("╠──────────────────────────────────────────────────╣");
            System.out.println("║ 3. Logout                                        ║");
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
                    showLoading("Participants Report");
                    showParticipantReport();
                    break;
                case 2:
                    showLoading("Events Report");
                    showEventReport();
                    break;
                case 3:
                    showLoading("Logging Out");
                    break;
                default:
                    System.out.println("[Error] Invalid choice. Please try again.");
                    break;
            }
        }while (choice != 3);
    }


    /**
     * Displays a report of the events a participant has joined.
     * 
     * Prompts the user to enter a username, validates the input, and then retrieves 
     * the participant's event registrations. If the participant has joined events, 
     * their details are displayed in a formatted table. Otherwise, a message 
     * indicating no participation is shown.
     * 
     * @return None
     */
    private static void showParticipantReport() {
        String username;
        while (true) {
            System.out.print("~~~> Enter Username: ");
            username = scanner.nextLine();
            if (ValidateUser.validateUsername(username)) {
                break;
            }
            System.out.println("[ERROR] Username not valid. Please enter a valid username.");
        }
        List <Registration> registrations =   adminDAO.getReportOfParticipant(username);
        if(!registrations.isEmpty()){
            
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println( registrations.get(0).getParticipant().getUsername() +" has joined " +registrations.size() + " events");
            System.out.println("╚════════════════════════════════════════════════╝");
            
            for (Registration registration : registrations) {
                System.out.println("╔═════════════════════════════════════════════╗");
                System.out.println("║ID        : " + registration.getEvent().getId());
                System.out.println("║Title     : " + registration.getEvent().getTitle());
                System.out.println("║Description: " + registration.getEvent().getDescription());
                System.out.println("║Date      : " + registration.getEvent().getDate());
                System.out.println("║Location  : " + registration.getEvent().getLocation());
                System.out.println("║Type      : " + registration.getEvent().getType());
                System.out.println("╚═════════════════════════════════════════════╝");
            }
            
            System.out.println("════════════════════════════════════════════════");
        
        }else{
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println(    username+ " has not participated in any event                            ║");
            System.out.println("╚════════════════════════════════════════════════╝");
        }
    }


    /**
     * Displays a report of participants for a given event.
     *
     * @return none
     */
    private static void showEventReport() {
        int id;
        while (true) {
            try {
                System.out.print("~~~> Enter Event ID: ");            
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            }catch (Exception e){
                scanner.nextLine();
            }
        }
        List <Registration> registrations = adminDAO.getReportOfEvent(id);
        if(!registrations.isEmpty()){

                System.out.println("╔══════════════════════════════════════════════════════════╗");
                System.out.println("   "+ registrations.size() +" Participants Joined In " + registrations.get(0).getEvent().getTitle());
                System.out.println("╚══════════════════════════════════════════════════════════╝");
                for (Registration registration : registrations) {
                    System.out.println("╔════════════════════════════════════════════════════════╗");
                    System.out.println("║  Participant ID :  "+ registration.getParticipant().getParticipantID());
                    System.out.println("║  Username :  " + registration.getParticipant().getUsername());
                    System.out.println("╚════════════════════════════════════════════════════════╝");
                    System.out.println(); 
                }
        }else{
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println("║  No participants joined in this event                    ║");
            System.out.println("╚══════════════════════════════════════════════════════════╝");

        }
    }


    private static void showLoading(String message) {
        System.out.print(message);
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(600);
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

}

