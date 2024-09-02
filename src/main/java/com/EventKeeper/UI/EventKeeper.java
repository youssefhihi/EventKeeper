package com.EventKeeper.UI;

import java.util.Scanner;

import com.EventKeeper.DAO.AdminDAO;
import com.EventKeeper.DAO.ParticipantDAO;
import com.EventKeeper.DAO.Implementation.ParticipantDaoImpl;
import com.EventKeeper.UI.AdminUI.AdminUI;
import com.EventKeeper.UI.ParticipantUI.RegistrationUI;
import com.EventKeeper.entity.Admin;
import com.EventKeeper.entity.Participant;
import com.EventKeeper.enums.role;
import com.EventKeeper.DAO.Implementation.AdminDaoImpl;
import com.EventKeeper.utility.ValidateUser;

public class EventKeeper {
    private static Scanner scanner = new Scanner(System.in);
    private static ParticipantDAO participantDAO = ParticipantDaoImpl.getInstance();
    private static AdminDAO adminDAO = new AdminDaoImpl();
    private RegistrationUI registrationUI;
    public static void run(){
        int choice = 0 ;
        System.out.println("███████╗██╗   ██╗███████╗███╗   ██╗████████╗    ██╗  ██╗███████╗███████╗██████╗ ███████╗██████╗ ");
        System.out.println("██╔════╝██║   ██║██╔════╝████╗  ██║╚══██╔══╝    ██║ ██╔╝██╔════╝██╔════╝██╔══██╗██╔════╝██╔══██╗");
        System.out.println("█████╗  ██║   ██║█████╗  ██╔██╗ ██║   ██║       █████╔╝ █████╗  █████╗  ██████╔╝█████╗  ██████╔╝");
        System.out.println("██╔══╝  ╚██╗ ██╔╝██╔══╝  ██║╚██╗██║   ██║       ██╔═██╗ ██╔══╝  ██╔══╝  ██╔═══╝ ██╔══╝  ██╔══██╗");
        System.out.println("███████╗ ╚████╔╝ ███████╗██║ ╚████║   ██║       ██║  ██╗███████╗███████╗██║     ███████╗██║  ██║");
        System.out.println("╚══════╝  ╚═══╝  ╚══════╝╚═╝  ╚═══╝   ╚═╝       ╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚══════╝╚═╝  ╚═╝");
                                                                                                        
        

        do{
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║           Welcome to EventKeeper      ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║  1  │  Create New Account             ║");
            System.out.println("╠─────┼─────────────────────────────────╣");
            System.out.println("║  2  │  Login                          ║");
            System.out.println("╠─────┼─────────────────────────────────╣");
            System.out.println("║  3  │  Exit                           ║");
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
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                default:
                    System.out.println("[ERROR] Invalid choice. Please try again.");
                    break;
            }
        }while(choice != 3);
    
    }

    public  void participantPart(Participant participantAuth){
        registrationUI = new RegistrationUI(participantAuth);
        registrationUI.run();
    }
    public  void adminPart(Admin adminAuth){
        AdminUI adminUI = new AdminUI(adminAuth);
        adminUI.run();
    }
    public static void createAccount(){
        String username;
        while (true) {
            System.out.print("~~~> Enter Username: ");
            username = scanner.nextLine();
            if (ValidateUser.validateUsername(username)) {
                break;
            }
            System.out.println("[ERROR] Username not valid. Please enter a valid username.");
        }
    
        String password;
        while (true) {
            System.out.print("~~~> Enter Password: ");
            password = scanner.nextLine();
            if (ValidateUser.validatePassword(password)) {
                break;
            }
            System.out.println("[ERROR] Password not valid. Please enter a valid password.");
        }
    
        System.out.print("Creating account");
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        Participant participant = new Participant(username, password, role.participant);
        boolean added = participantDAO.addParticipant(participant);
        if (added) {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║ [Success] Your Account Created Successfully! :)  ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
        } else {
            System.out.println("╔═══════════════════════════════════════════════════════╗");
            System.out.println("║ [ERROR] Username already exists. Please try again. :( ║");
            System.out.println("╚═══════════════════════════════════════════════════════╝");
        }
    }
    
    public static void login(){
        System.out.print("~~~> Enter Your Username: ");
        String username = scanner.nextLine();
    
        System.out.print("~~~> Enter Password: ");
        String password = scanner.nextLine();
    
        Admin admin = adminDAO.login(username, password);
        Participant participant = participantDAO.login(username, password);  
        System.out.print("Logging in");
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (admin != null) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║   [Success]       Welcome Admin! :)            ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            EventKeeper eventKeeper = new EventKeeper();
            eventKeeper.adminPart(admin);
    
        } else if (participant != null) {
            System.out.println("╔════════════════════════════════════════════════╗"); 
            System.out.println("║    [Success]    Login Successfully! :)         ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            EventKeeper eventKeeper = new EventKeeper();
            eventKeeper.participantPart(participant);
    
        } else {
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║ [ERROR] Username or Password Incorrect. :(   ║");
            System.out.println("╚══════════════════════════════════════════════╝");
        }  
    }
}    