package com.EventKeeper.UI;

import java.util.Scanner;
import com.EventKeeper.DAO.ParticipantDAO;
import com.EventKeeper.DAO.Implementation.ParticipantDaoImpl;
import com.EventKeeper.UI.ParticipantUI.RegistrationUI;
import com.EventKeeper.entity.Participant;
import com.EventKeeper.entity.User;
import com.EventKeeper.enums.role;
import com.EventKeeper.utility.ValidateUser;

public class EventKeeper {
    private static Scanner scanner = new Scanner(System.in);
    private static ParticipantDAO participantDAO = new ParticipantDaoImpl();
    private RegistrationUI registrationUI;
    private  Participant participantLogged;
    public void run(){
        int choice = 0 ;
        do{
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t====================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t||  Welcome To EventKeeper        ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t====================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|1|     Create new account        ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|2|     Login to your account     ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|3|            Exit               ||");
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
                    
                    break;
            
                default:
                    break;
            }
        }while(choice != 3);
    
    }

    public  void participantPart(){
        registrationUI = new RegistrationUI(this.participantLogged);
        registrationUI.run();
    }


    public static void createAccount(){
        System.out.print("\t\t\t\t\tEnter Participant ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String username;
        while (true) {
            System.out.print("\t\t\t\t\tEnter Username: ");
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
        Participant participant = new Participant(id,username, password, role.participant);
        boolean added = participantDAO.addParticipant(participant);
        if (added){
            System.out.println("\t\t\t\t\t############################################");
            System.out.println("\t\t\t\t\t##  Your Account created Successfully  :) ##");
            System.out.println("\t\t\t\t\t############################################");
        }else{
            System.out.println("\t\t\t\t\t##################################");
            System.out.println("\t\t\t\t\t##  Username already exists  :( ##");
            System.out.println("\t\t\t\t\t##################################");
        }
    }


    public  void login(){
        System.out.print("\t\t\t\t\tEnter Your Username : ");
        String username = scanner.nextLine();

        System.out.print("\t\t\t\t\tEnter Password: ");
        String password = scanner.nextLine();

        Participant participant = participantDAO.login(username, password);     
        if(participant != null){
            this.participantLogged = participant;
            System.out.println("\t\t\t\t\t#############################");
            System.out.println("\t\t\t\t\t##  Login Successfully  :) ##");
            System.out.println("\t\t\t\t\t#############################");
            participantPart();
        }else{
            System.out.println("\t\t\t\t\t########################################");
            System.out.println("\t\t\t\t\t##  Username or Password incorrect  :( ##");
            System.out.println("\t\t\t\t\t#########################################");
        }    
         
    }

    private static User userData(){
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
        return new User(id,username, password, role.participant);
    }
}
