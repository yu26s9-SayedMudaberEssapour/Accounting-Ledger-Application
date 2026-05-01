package com.pluralsight.ui;
import com.pluralsight.model.Transactions;

import static com.pluralsight.model.Transactions.fileContent;
import static com.pluralsight.model.Transactions.listSort;
import static com.pluralsight.ui.Reports.ReportsScreen;

/**
 * This class represents the Ledger Screen of the application.
 * It allows users to view transactions and navigate between different options.
 */
public class LedgerScreen {

    /**
     * Displays the main ledger screen menu and handles user input.
     * Allows navigation to different transaction views and reports.
     */
    public static void LedgerScreens() {
        listSort(); //sorts on demand
        while (true) {

            System.out.println("""
                
                Welcome to the Ledger-Screen!! 
                Type (A) to display all entries: 
                Type (D) to display deposit entries: 
                Type (P) to display payment entries: 
                Type (R) to go to the report page 
                Type (H) to return back to Home Page 
                
                """);

            String input = Console.promptForString("Please Enter one of the above options: ").toUpperCase();

            switch (input) {

                case "A":
                    displayAll();
                    break;

                case "D":
                    displayDeposit();
                    break;

                case "P":
                    displayPayment();
                    break;

                case "R":
                    ReportsScreen();
                    break;

                case "H":
                    return; // ONLY goes back to HomeScreen

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }

    }


    //this does not sort when it displays
    /**
     * Displays all transactions from the file content.
     */
    public static void displayAll() {
        System.out.println("All the traction: ");
        for (Transactions e : fileContent) {
            System.out.print(e);
        }

    }

    /**
     * Displays only deposit transactions (amount > 0).
     */
    public static void displayDeposit() {
        System.out.println("Your Deposits");
        for (Transactions e : fileContent) {
            if (Double.parseDouble(String.valueOf(e.getAmount())) > 0) {
                System.out.print(e);
            }
        }

    }

    /**
     * Displays only payment transactions (amount < 0).
     */
    public static void displayPayment() {
        System.out.println("Your Payments");
        for (Transactions e : fileContent) {
            if (Double.parseDouble(String.valueOf(e.getAmount())) < 0) {
                System.out.print(e);
            }
        }
    }
}