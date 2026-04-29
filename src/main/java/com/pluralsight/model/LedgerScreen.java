package com.pluralsight.model;
import com.pluralsight.ui.Console;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static com.pluralsight.model.HomeScreen.transactionFile;
import static com.pluralsight.model.Reports.ReportsScreen;
import java.util.ArrayList;
import java.util.Collections;

public class LedgerScreen {

    static Console console = new Console();

    /**
     * we gotta do everything from the most recent to the old
     */


    //Ledger method(you can transfer this to a different class later

    // you will be checking the time of each entry and then showing the most recent ones first

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
                            "Type (H) to return back to Home Page");
            System.out.println();

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



    public static void displayPayment()
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


//    public static ObservableList<String> getRawEntries() {
//        ObservableList<String> entries = FXCollections.observableArrayList();
//        try (BufferedReader br = new BufferedReader(new FileReader(transactionFile))) {
//            br.readLine(); // Skip header
//            String line;
//            while ((line = br.readLine()) != null) {
//                entries.add(line); // Add each CSV line to the list
//            }
//        } catch (Exception e) {
//            System.out.println("Error reading ledger data.");
//        }
//        return entries;}

}
