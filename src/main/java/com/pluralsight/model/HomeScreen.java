package com.pluralsight.model;
import com.pluralsight.ui.Console;
import java.io.FileWriter;

import static com.pluralsight.model.LedgerScreen.LedgerScreen;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class HomeScreen{


    private String data;
    private String time; //for this import time
    private String description;
    private String vendor;
    private double amount;

    static Console console = new Console();

    public static final String transactionFile = "src/main/java/com/pluralsight/data/transaction.csv";


    //-------------------------------Methods------------------------------------------


    public static void homeScreen(){

        String input;
        do {
            System.out.println();
            System.out.println(
                    "Welcome to Accounting-Ledger-Application!! \n" +
                            "Type (D) to Add Deposit\n" +
                            "Type (P) to Make Payment\n" +
                            "Type (L) to go to Ledger Screen\n" +
                            "Type (X) to exit\n");


            input = console.promptForString("Please Enter one of the above options: ");

            switch(input.toUpperCase()){
                case "D" :
                    // Prompt for info here and pass it to the method
                    String dDesc = console.promptForString("Please enter the Description of your deposit: ");
                    String dVendor = console.promptForString("Please enter the Vendor name: ");
                    double dAmount = console.promptForDouble("Please enter the amount here: ");
                    saveTransaction(dDesc, dVendor, dAmount);
                    break;
                case "P" :
                    String pDesc = console.promptForString("Please enter the Description of your payment: ");
                    String PVendor = console.promptForString("Please enter the Vendor name: ");
                    double PAAmount = console.promptForDouble("Please enter the amount here: ");
                    MakePayment(pDesc, PVendor, PAAmount);
                    break;
                case "L" :
                    LedgerScreen();
                    break;
                case "X" :
                    System.out.println("You have exited the application");
                    break;
                default :
                    // Stay in loop for invalid entries
                    break;
            }
        }

        while(!input.equalsIgnoreCase("x"));

    }

    // This method now handles the actual file writing logic
    public static void saveTransaction(String description, String vendor, double amount) {
        try {
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            DateTimeFormatter time1 = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = time.format(time1);

            FileWriter writer = new FileWriter(transactionFile, true);

            // Formats the entry to match your CSV structure
            String amountInString = String.valueOf(Math.round(amount * 100.0) / 100.0);
            writer.write(date + "|" + formattedTime + "|" + description + "|" + vendor + "|" + amountInString + "\n");

            writer.close();
            System.out.println("Transaction saved successfully!");

        } catch (Exception e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }


    //MakePayment method
    public static void MakePayment(String description, String vendor, double amount){

        try{

            // We call saveTransaction with a negative value to record a payment
            saveTransaction(description, vendor, (amount * -1));

        }
        catch (Exception e){
            System.out.println("Sorry invalid entry, Please only enter a number");
            e.getMessage();
            homeScreen();
        }

    }




    @Override
    public String toString() {
        return "HomeScreen{" +
                "data='" + data + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }
    //hello everyone this is just a test
}