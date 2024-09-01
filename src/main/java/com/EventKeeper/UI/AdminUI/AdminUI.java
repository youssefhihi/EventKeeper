package com.EventKeeper.UI.AdminUI;

import java.util.Scanner;

import com.EventKeeper.entity.Admin;

public class AdminUI {
    private static Scanner scanner = new Scanner(System.in);
    private static ParticipantUI participantUI = new ParticipantUI();
    private Admin admin;
    public AdminUI(Admin admin) {
        this.admin = admin;
    }
    public  void run() {
        int choice = 0;

        do {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t============================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t||  Welcome "+this.admin.getUsername()+" To Admin Management   ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t==========================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|1|           Events Management         ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|2|        Participants Management      ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|-|-------------------------------------||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t|3|                Logout               ||");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t==========================================");
            try {
                System.out.print("\t\t\t\t\tEnter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    EventsUI.run();
                    break;
                case 2:
                    participantUI.run();
                    break;
                case 3:
                    System.out.println("\t\t\t\t\tLogging out...");
                    break;
                default:
                    System.out.println("\t\t\t\t\tInvalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }


    
}
