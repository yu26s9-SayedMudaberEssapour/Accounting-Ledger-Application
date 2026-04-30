package com.pluralsight.ui;
import com.pluralsight.model.Transactions;
import static com.pluralsight.model.Transactions.shortMemory;
import static com.pluralsight.ui.Reports.ReportsScreen;


public class LedgerScreen {

    static Console console = new Console();


    public static void LedgerScreen()
    {
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

            String input = console.promptForString("Please Enter one of the above options: ");

            switch(input.toUpperCase()){
                case "A" :
                    displayAll();
                    break;
                case "P" :
                    displayPayment();
                    break;
                case "D" :
                    displayDeposit();
                    break;
                case "R" :
                    ReportsScreen();
                    break;
                case "H" :
                    return;
                default :
                    shouldContinue = false;
                    break;
            }
        }

        while(shouldContinue);
    }




    //works very well(reverse read the values)
    public static void displayAll()
    {
        for(Transactions e: shortMemory()){
            System.out.println(e);
        }

    }



    public static void displayPayment()
    {
        for(Transactions e: shortMemory()){
//
            if(Double.parseDouble(String.valueOf(e.getAmount())) < 0){
                System.out.println(e);

        }}

    }



    public static void displayDeposit()
    {
        for(Transactions e: shortMemory()){
//
            if(Double.parseDouble(String.valueOf(e.getAmount())) > 0){
                System.out.println(e);

            }}

        }}

