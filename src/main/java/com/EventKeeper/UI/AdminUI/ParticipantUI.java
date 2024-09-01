package com.EventKeeper.UI.AdminUI;

import com.EventKeeper.DAO.Implementation.ParticipantDaoImpl;
import com.EventKeeper.DAO.ParticipantDAO;
import com.EventKeeper.entity.Participant;
import com.EventKeeper.enums.role;
import com.EventKeeper.utility.ValidateUser;

import java.util.List;
import java.util.Scanner;

public class ParticipantUI {
    private static Scanner scanner = new Scanner(System.in);
    private static ParticipantDAO participantDAO = new ParticipantDaoImpl();
    private static final ValidateUser validateUser = new ValidateUser();

    public  void run(){
        int choice = 0;
        do{
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t========================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|| Welcome To Participants Management ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t========================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|1|       Get All Participants        ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-----------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|2|        Add a participant          ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-----------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|3|      Update a participant         ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-----------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|4|      Delete a participant         ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-----------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|5|            Exit                   ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t========================================");
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
                    showParticipant();
                    break;
                case 2:
                    System.out.println("\t\t\t\t\tSearching for an event...");
                    addParticipant();
                    break;
                case 3:
                    updateParticipant();
                    break;
                case 4:
                    deleteParticipant();
                    break;
                case 5:
                    System.out.println("\t\t\t\t\tExiting...");
                    break;
                default:
                    System.out.println("\t\t\t\t\tInvalid choice. Please try again.");
            }
        }while(choice != 5);
    }

    private void showParticipant(){
        try{
          List<Participant> participants = participantDAO.getParticipants();
          if(!participants.isEmpty()){
              for(Participant participant : participants){
                  System.out.println("\t\t\t\t\t-------------------------------------------");
                  System.out.println("\t\t\t\t\tID: " + participant.getId());
                  System.out.println("\t\t\t\t\tUsername: " + participant.getUsername());
                  System.out.println("\t\t\t\t\t-------------------------------------------");
              }
          }else{
              System.out.println("\t\t\t\t\tNo Participants Found");
          }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addParticipant(){
        System.out.println("\t\t\t\t\tAdding a new participant...");
        Participant participant = ParticipantData();
        boolean added = participantDAO.addParticipant(participant);
        if (added){
            System.out.println("\t\t\t\t\t#########################################");
            System.out.println("\t\t\t\t\t##  Participant Added Successfully  :) ##");
            System.out.println("\t\t\t\t\t#########################################");
        }else{
            System.out.println("\t\t\t\t\t##################################");
            System.out.println("\t\t\t\t\t##  Username already exists  :( ##");
            System.out.println("\t\t\t\t\t##################################");
        }

    }
    private void updateParticipant(){
        System.out.println("\t\t\t\t\tUpdate an existing participant...");
        Participant participant = ParticipantData();
        boolean updated = participantDAO.updateParticipant(participant);
        if (updated){
            System.out.println("\t\t\t\t\t#########################################");
            System.out.println("\t\t\t\t\t##  Participant updated Successfully  :) ##");
            System.out.println("\t\t\t\t\t#########################################");
        }else{
            System.out.println("\t\t\t\t\t###################################################################");
            System.out.println("\t\t\t\t\t##  Username already exists OR ID participant does not exist  :( ##");
            System.out.println("\t\t\t\t\t###################################################################");
        }
    }
    private void deleteParticipant(){
        System.out.println("\t\t\t\t\tDeleting an existing participant...");
        System.out.print("\t\t\t\t\tEnter Participant ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        participantDAO.deleteParticipant(id);
    }

    private Participant ParticipantData(){
        System.out.print("\t\t\t\t\tEnter Participant ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Username Input and Validation
        String username;
        while (true) {
            System.out.print("\t\t\t\t\tEnter Event title: ");
            username = scanner.nextLine();
            if (ValidateUser.validateUsername(username)) {
                break;
            }
            System.out.println("\t\t\t\t\tUsername not valid. Please enter a valid username.");
        }

        String password;
        while (true) {
            System.out.print("\t\t\t\t\tEnter Password: ");
            password = scanner.nextLine();
            if (ValidateUser.validatePassword(password)) {
                break;
            }
            System.out.println("\t\t\t\t\tPassword not valid. Please enter a valid password.");
        }
        return new Participant(id,username, password, role.participant);
    }
}
