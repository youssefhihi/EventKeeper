package com.EventKeeper.UI.AdminUI;


import com.EventKeeper.entity.Participant;
import com.EventKeeper.enums.role;
import com.EventKeeper.service.ParticipantService;
import com.EventKeeper.service.impl.ParticipantServiceImpl;
import com.EventKeeper.utility.ValidateUser;

import java.util.List;
import java.util.Scanner;

public class ParticipantUI {
    private static Scanner scanner = new Scanner(System.in);
    private static ParticipantService participantService = new ParticipantServiceImpl();
    /**
     * Runs the participant management system, providing a menu for the Admin to 
     * interact with and perform various operations such as getting all participants, 
     * adding, updating, and deleting participants.
     *
     * @return none
     */
    public  void run(){
        int choice = 0;
        do{
            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("║        Welcome To Participants Management     ║");
            System.out.println("╠═══════════════════════════════════════════════╣");
            System.out.println("║  1  │  Get All Participants                   ║");
            System.out.println("╠─────┼─────────────────────────────────────────╣");
            System.out.println("║  2  │  Add a participant                      ║");
            System.out.println("╠─────┼─────────────────────────────────────────╣");
            System.out.println("║  3  │  Update a participant                   ║");
            System.out.println("╠─────┼─────────────────────────────────────────╣");
            System.out.println("║  4  │  Delete a participant                   ║");
            System.out.println("╠─────┼─────────────────────────────────────────╣");
            System.out.println("║  5  │  Exit                                   ║");
            System.out.println("╚═══════════════════════════════════════════════╝");
            
            try {
                System.out.print("~~~> Please select an option: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){
                scanner.nextLine();
            }
            switch (choice){
                case 1:
                    showLoading("Fetching all Participants");
                    showParticipant();
                    break;
                case 2:
                    showLoading("Adding for an Participant");
                    addParticipant();
                    break;
                case 3:
                    showLoading("Updating an Participant");
                    updateParticipant();
                    break;
                case 4:
                    showLoading("Deleting an Participant");
                    deleteParticipant();
                    break;
                case 5:
                    showLoading("Exiting");
                    break;
                default:
                    System.out.println("[ERROR] Invalid choice. Please try again.");
            }
        }while(choice != 5);
    }

    private void showParticipant() {
        try {
            List<Participant> participants = participantService.getParticipants();
            
            if (!participants.isEmpty()) {
                System.out.println("╔══════════════════════════════════════════════════════════╗");
                System.out.println("║                   List of Participants                   ║");
                System.out.println("╚══════════════════════════════════════════════════════════╝");
                for (Participant participant : participants) {
                    System.out.println("╔════════════════════════════════════════════════════════╗");
                    System.out.println("║  Participant ID :  "+ participant.getParticipantID());
                    System.out.println("║  Username :  " + participant.getUsername());
                    System.out.println("╚════════════════════════════════════════════════════════╝");
                    System.out.println(); 
                }
            } else {
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║   [INFO]    No Participants Found      ║");
                System.out.println("╚════════════════════════════════════════╝");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private void addParticipant(){
        Participant participant = ParticipantData();
        boolean added = participantService.addParticipant(participant);
        if (added){
            System.out.println("╔═════════════════════════════════════════════════════╗");
            System.out.println("║  [SUCCESS]    Participant Added  Successfully! :)   ║");
            System.out.println("╚═════════════════════════════════════════════════════╝");
            
        }else{
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║   [ERROR]   username already exists     ║");
            System.out.println("╚═════════════════════════════════════════╝");
        }

    }
    private void updateParticipant(){
        int id;
        while(true){
            try {
                System.out.print("~~~> 	Enter Participant ID: ");
                 id = scanner.nextInt();
                scanner.nextLine();
                break;
            }catch (Exception e){
                scanner.nextLine();
            }
        }

        Participant participant = ParticipantData();

        boolean updated = participantService.updateParticipant(id, participant);
        if (updated){
            System.out.println("╔════════════════════════════════════════════════════╗");
            System.out.println("║  [SUCCESS]    Participant Updated  Successfully    ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
        }else{
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║   [ERROR]   Username OR ID Invalid      ║");
            System.out.println("╚═════════════════════════════════════════╝");
        }
    }
    private void deleteParticipant(){
        System.out.print("~~~> 	Enter Participant ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        participantService.deleteParticipant(id);
    }

    private Participant ParticipantData(){
        // Username Input and Validation
        String username;
        while (true) {
            System.out.print("~~~> 	Enter Username: ");
            username = scanner.nextLine();
            if (ValidateUser.validateUsername(username)) {
                break;
            }
            System.out.println("[Error] Username not valid. Please enter a valid username.");
        }

        String password;
        while (true) {
            System.out.print("~~~> 	Enter Password: ");
            password = scanner.nextLine();
            if (ValidateUser.validatePassword(password)) {
                break;
            }
            System.out.println("[Error] Password not valid. Please enter a valid password.");
        }
        return new Participant(username, password, role.participant);
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
