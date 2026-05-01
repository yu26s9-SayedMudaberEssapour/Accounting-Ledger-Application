package com.pluralsight.ui;
import com.pluralsight.model.Transactions;

import static com.pluralsight.model.Transactions.fileContent;
import static com.pluralsight.ui.HomeScreen.homeScreen;
import static com.pluralsight.ui.Reports.ReportsScreen;
import static com.pluralsight.ui.Reports.promptToStay;


public class LedgerScreen {

    static Console console = new Console();


    public static void LedgerScreens() {
        boolean shouldContinue = true;
        do {
            System.out.println();
            System.out.println(
                    "Welcome to the Ledger-Screen!! \n" +
                            "Type (A) to display all entries: \n" +
                            "Type (D) to see deposit entries: \n" +
                            "Type (P) to see payment entries: \n" +
                            "Type (R) to go to the report page \n" +
                            "Type (H) to return back to Home Page \n");

            String input = Console.promptForString("Please Enter one of the above options: ");

            switch (input.toUpperCase()) {
                case "":
                    System.out.println("Please type a valid option or (H) to return to Home Screen: ");
                    break;
                case "A":
                    displayAll();
                    if(promptToStay().equalsIgnoreCase("yes")){
                        break;
                    }
                    else {
                        return;
                    }
                case "P":
                    displayPayment();
                    if(promptToStay().equalsIgnoreCase("yes")){
                        break;
                    }
                    else {
                        return;
                    }
                case "D":
                    displayDeposit();
                    if(promptToStay().equalsIgnoreCase("yes")){
                        break;
                    }
                    else {
                        return;
                    }
                case "R":
                    ReportsScreen();
                    if(promptToStay().equalsIgnoreCase("yes")){
                        break;
                    }
                    else {
                        return;
                    }
                case "H":
                    homeScreen();
                    return;
                default:
                    shouldContinue = false;
                    break;
            }
        }while(shouldContinue);
    }


        //works very well(reverse read the values)
        public static void displayAll ()
        {
            System.out.println("All the traction: ");
            for (Transactions e : fileContent) {
                System.out.print(e);
            }

        }


        public static void displayPayment ()
        {
            System.out.println("Your Payments");
            for (Transactions e : fileContent) {
                if (Double.parseDouble(String.valueOf(e.getAmount())) < 0) {
                    System.out.print(e);

                }
            }

        }


        public static void displayDeposit ()
        {
            System.out.println("Your Deposits");
            for (Transactions e : fileContent) {

                if (Double.parseDouble(String.valueOf(e.getAmount())) > 0) {
                    System.out.print(e);

                }
            }

        }

    }

