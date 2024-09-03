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
            System.out.println("╔══════════════════════════════════════════════════════════════════╗");
            System.out.println("              Welcome " + this.admin.getUsername() + " To Admin Management    ");
            System.out.println("╠══════════════════════════════════════════════════════════════════╣");
            System.out.println("║ 1. Events Management                                             ║");
            System.out.println("╠──────────────────────────────────────────────────────────────────╣");
            System.out.println("║ 2. Participants Management                                       ║");
            System.out.println("╠──────────────────────────────────────────────────────────────────╣");
            System.out.println("║ 3. Reports Management                                            ║");
            System.out.println("╠──────────────────────────────────────────────────────────────────╣");
            System.out.println("║ 4. Logout                                                        ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════╝");
            
            try {
                System.out.print("~~~> Please select an option: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    showLoading("Events Management");
                    EventsUI.run();
                    break;
                case 2:
                    showLoading("Participants Management");
                    participantUI.run();
                    break;
                case 3:
                    showLoading("Reports Management");
                    Reports.run();
                    break;
                case 4:
                    showLoading("Logging Out");
                    break;
                default:
                    System.out.println("[Error] Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
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
