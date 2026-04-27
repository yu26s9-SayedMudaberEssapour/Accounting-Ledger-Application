package com.pluralsight.model;

import static com.pluralsight.model.HomeScreen.homeScreen;

public class Reports {

    public void ReportsScreen()
    {
        boolean shouldContinue = true;
        do {
            System.out.println(
                    "Welcome to the Ledger-Screen!! \n" +
                            "Type (A) to display all entries: \n" +
                            "Type (D) to see deposit entries: \n" +
                            "Type (P) to see payment entries: \n" +
                            "Type (R) to go to the report page \n" +
                            "Type (H) to return back to Home Page");

            String input = console.promptForString("Please Enter one of the above options: ");

            switch(input){
                case "A" :
                case "a" :
                    displayAll();
                    break;
                case "P" :
                case "p" :
                    payment();
                    break;
                case "D" :
                case "d" :
                    displayDeposit();
                    break;
                case "R" :
                case "r" :
                    System.out.println("go to the report page");
                    break;
                case "H" :
                case "h" :
                    homeScreen();
                    shouldContinue = false;
                default :
                    break;
            }
        }

        while(shouldContinue);
    }
}
