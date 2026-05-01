package com.pluralsight.ui;
import com.pluralsight.model.Transactions;
import static com.pluralsight.model.Transactions.*;
import static com.pluralsight.ui.LedgerScreen.LedgerScreens;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the Home Screen of the Accounting Ledger Application.
 * It allows users to add deposits, make payments, and navigate to the ledger screen.
 */
public class HomeScreen {

    public static final String transactionFile = "src/main/java/com/pluralsight/data/transaction.csv";

    //-------------------------------Methods------------------------------------------

    /**
     * Displays the home screen menu and handles user input.
     * Allows navigation to deposit, payment, ledger screen, or exit.
     */
    public static void homeScreen() {

        while (true) {

            System.out.println("""
                
                Welcome to Accounting-Ledger-Application!! 
                Type (D) to Add Deposit
                Type (P) to Make Payment
                Type (L) to go to Ledger Screen
                Type (X) to exit
                
                """);

            String input = Console.promptForString("Please Enter one of the above options: ").toUpperCase();

            switch (input) {

                case "D":
                    MakeDeposit();
                    break;

                case "P":
                    MakePayment();
                    break;

                case "L":
                    LedgerScreens();
                    break;

                case "X":
                    System.out.println("You have exited the application");
                    return;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }

    }

    /**
     * Prompts the user to enter deposit information and adds it to the transaction list.
     * Handles invalid numeric input using a try-catch block.
     */
    public static void MakeDeposit() {

        try {
            String description = Console.promptForString("Please enter the Description of your deposit: ");
            String vendor = Console.promptForString("Please enter the Vendor name: ");
            double amount = Console.promptForDouble("Please enter the amount here: ");
            LocalDate date = LocalDate.now();
            String date1 = String.valueOf(date);

            fileContent.add(new Transactions(date1, timeReturn(), description, vendor, (amount)));

        } catch (Exception e) {
            System.out.println("Sorry invalid value, please enter only a number: ");
        }
    }

    /**
     * Prompts the user to enter payment information and adds it to the transaction list.
     * The amount is stored as a negative value.
     * Handles invalid numeric input using a try-catch block.
     */
    public static void MakePayment() {

        try {
            String description = Console.promptForString("Please enter the Description of your deposit: ");
            String vendor = Console.promptForString("Please enter the Vendor name: ");
            double amount = Console.promptForDouble("Please enter the amount here: ");
            LocalDate date = LocalDate.now();
            String date1 = String.valueOf(date);

            fileContent.add(new Transactions(date1, timeReturn(), description, vendor, (amount * -1)));

        } catch (Exception e) {
            System.out.println("Sorry invalid value, please enter only a number: ");
        }
    }

    /**
     * Returns the current time formatted as HH:mm:ss.
     * This is a helper function
     * @return formatted current time as a String
     */
    public static String timeReturn() {

        LocalTime time = LocalTime.now();
        DateTimeFormatter time1;
        time1 = DateTimeFormatter.ofPattern("HH:mm:ss");

        return time.format(time1);
    }
}