package com.pluralsight.model;
import com.pluralsight.ui.Console;
import java.io.FileWriter;

import static com.pluralsight.model.LedgerScreen.LedgerScreen;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class HomeScreen{

    public static void main(String args[]){


    }


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
                        deposit();
                        break;
                    case "P" :
                        MakePayment();
                        break;
                        //break;
                    case "L" :
                        LedgerScreen();
                        break;
                    case "X" :
                        System.out.println("You have exited the application");
                        break;
                    default :
                        return;
                }
            }

            while(!input.equalsIgnoreCase("x"));

    }



    //take care of it so that it takes care of times when an invalid option is entered.
    public static void deposit()
    {
        //get users deposit information and save it to .csv
        //things to change here (the date) (the time)


        try{
            String description = console.promptForString("Please enter the Description of your deposit: ");
            String vendor = console.promptForString("Please enter the Vendor name: ");
            double amount = console.promptForDouble("Please enter the amount here: ");


            LocalDate date = LocalDate.now();

            LocalTime time = LocalTime.now();
            DateTimeFormatter time1;
            time1 = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = time.format(time1);


            FileWriter writer = new FileWriter(transactionFile, true);

            StringBuilder userInfo = new StringBuilder();


            String amountInString = String.valueOf(Math.round(amount * 100.0) / 100.0);
            userInfo.append(date + "|" + formattedTime + "|" + description + "|" + vendor + "|" + amountInString + "\n");
            writer.write(String.valueOf(userInfo));

            writer.close();


        }catch (Exception e){
            System.out.println("Sorry invalid value, please enter only a number: ");
            e.getMessage();
            homeScreen();

        }

    }


    //MakePayment method
    public static void MakePayment(){

        try{
            String description = console.promptForString("Please enter the Description of your payment: ");
            String vendor = console.promptForString("Please enter the Vendor name: ");
            double amount = console.promptForDouble("Please enter the amount here: ");


            LocalDate date = LocalDate.now();

            LocalTime time = LocalTime.now();
            DateTimeFormatter time1;
            time1 = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = time.format(time1);


            FileWriter writer = new FileWriter(transactionFile, true);

            StringBuilder userInfo = new StringBuilder();


            String amountInString = String.valueOf((Math.round(amount * 100.0) / 100.0) * -1);
            userInfo.append(date + "|" + formattedTime + "|" + description + "|" + vendor + "|" + amountInString + "\n");
            writer.write(String.valueOf(userInfo));

            writer.close();

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
}
