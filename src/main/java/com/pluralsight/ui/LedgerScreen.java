package com.pluralsight.ui;
import java.io.BufferedReader;
import java.io.IOException;

import static com.pluralsight.ui.HomeScreen.readFileWithoutHeader;
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
        //read transaction.csv and only display the values
        try{
            BufferedReader br = readFileWithoutHeader(transactionFile);

            String line;

            ArrayList<String> lines = new ArrayList<>();

            while((line = br.readLine()) != null){
                lines.add(line);
            }

            Collections.reverse(lines);

            for(String L : lines){
                System.out.println(L.toString());
            }
        }
        catch (IOException e){
            e.getMessage();
        }

    }



    public static void displayPayment()
    {
        //check if the amount is negative then print it

        try{
            BufferedReader br = readFileWithoutHeader(transactionFile);

            String line;

            ArrayList<String> lines = new ArrayList<>();

            while((line = br.readLine()) != null){
                String[] str = line.split("\\|");
                double negativeAmount = Double.parseDouble(str[4]);
                if(negativeAmount < 0){
                    lines.add(line);
                }}

            Collections.reverse(lines);

            for(String L : lines){
                System.out.println(L.toString());
            }

        }
        catch (IOException e){
            e.getMessage();
        }
    }




    public static void displayDeposit()
    {

        try{
            BufferedReader br = readFileWithoutHeader(transactionFile);
            String line;
            ArrayList<String> lines = new ArrayList<String>();

            while((line = br.readLine()) != null){
                String[] str = line.split("\\|");
                double PosAmount = Double.parseDouble(str[4]);
                if(PosAmount > 0){
                    lines.add(line);
                }}

            Collections.reverse(lines);

            for(String L : lines){
                System.out.println(L.toString());
            }

        }
        catch (IOException e){
            e.getMessage();
        }
    }

}
