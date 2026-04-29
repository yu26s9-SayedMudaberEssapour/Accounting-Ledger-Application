package com.pluralsight.ui;
import com.pluralsight.model.Transactions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.pluralsight.model.Transactions.shortMemory;
import static com.pluralsight.ui.HomeScreen.transactionFile;
import static com.pluralsight.ui.Reports.ReportsScreen;
import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<String> lines = new ArrayList<>();

        for(Transactions e : shortMemory()){
            lines.add(e.getDate() + "|" + e.getTime() + "|" + e.getDescription() + "|" + e.getVendor() + "|" + e.getAmount());
        }

        Collections.reverse(lines);

        for(String L : lines){
            System.out.println(L.toString());
        }

    }



    public static void displayPayment()
    {

        ArrayList<String> lines = new ArrayList<>();

        for(Transactions e : shortMemory()){
            if(e.getAmount() < 0){
                lines.add(e.getDate() + "|" + e.getTime() + "|" + e.getDescription() + "|" + e.getVendor() + "|" + e.getAmount());
            }

        }

        Collections.reverse(lines);

        for(String L : lines){
            System.out.println(L.toString());
        }
    }



    public static void displayDeposit()
    {
        ArrayList<String> lines = new ArrayList<>();
        for(Transactions e : shortMemory()){
            if(e.getAmount() > 0){
                lines.add(e.getDate() + "|" + e.getTime() + "|" + e.getDescription() + "|" + e.getVendor() + "|" + e.getAmount());
            }
        }
        Collections.reverse(lines);
        for(String L : lines){
            System.out.println(L.toString());
        }
    }

}
