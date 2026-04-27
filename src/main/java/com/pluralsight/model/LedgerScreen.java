package com.pluralsight.model;
import com.pluralsight.ui.Console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.pluralsight.model.HomeScreen.homeScreen;
import static com.pluralsight.model.HomeScreen.transactionFile;


import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LedgerScreen {

    Console console = new Console();

    /**
     * we gotta do everything from the most recent to the old
     */


    //Ledger method(you can transfer this to a different class later

    // you will be checking the time of each entry and then showing the most recent ones first

    public void LedgerScreen()
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




    //works very well(reverse read the values)
    public static void displayAll()
    {
        //read transaction.csv and only display the values
        try{
            FileReader fr = new FileReader(transactionFile);
            BufferedReader br = new BufferedReader(fr);

            //this will be skipping the header in the file
            br.readLine();

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



    public static void payment()
    {
        //check if the amount is negative then print it

        try{
            FileReader fr = new FileReader(transactionFile);
            BufferedReader br = new BufferedReader(fr);

            //this will be skipping the header in the file
            br.readLine();

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
        //check if the amount is negative then print it

        try{
            FileReader fr = new FileReader(transactionFile);
            BufferedReader br = new BufferedReader(fr);

            //this will be skipping the header in the file
            br.readLine();

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
