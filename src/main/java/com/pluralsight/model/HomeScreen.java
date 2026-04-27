package com.pluralsight.model;

import com.pluralsight.ui.Console;

import javax.swing.text.DateFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class HomeScreen {


    private String data;
    private String time; //for this import time
    private String description;
    private String vendor;
    private double amount;

    static Console console = new Console();

    public static final String transactionFile = "src/main/java/com/pluralsight/data/transaction.csv";


    //-----------------------------Getters and setters----------------------
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    //-------------------------------Methods------------------------------------------


    //the home screen should welcome the user and then prompt them for the
    //4 options


    public static void homeScreen(){

        boolean shouldContinue = true;
        do {
            System.out.println(
                    "Welcome to Accounting-Ledger-Application!! \n" +
                            "Type (D) to Add Deposit\n" +
                            "Type (P) to Make Payment\n" +
                            "Type (L) to go to Ledger Screen\n" +
                            "Type (X) to exit");

            String input = console.promptForString("Please Enter one of the above options: ");

            switch(input){
                case "D" :
                case "d" :
                    deposit();
                    break;
                case "P" :
                case "p" :
                    MakePayment();
                    break;
                case "L" :
                case "l" :
                    System.out.println("call the ledger method");
                    break;
                case "X" :
                case "x" :
                    System.out.println("exit the application");
                    shouldContinue = false;
                    break;
                default :
                    break;
            }
        }

        while(shouldContinue);


    }


    //then make other methods that will support home screen

    //Deposit method works as intended

    public static void deposit()
    {
        //get users deposit information and save it to .csv
        //things to change here (the date) (the time)

        String description = console.promptForString("Please enter the Description of your deposit: ");
        String vendor = console.promptForString("Please enter the Vendor name: ");
        double amount = console.promptForDouble("Please enter the amount here: ");


        LocalDate date = LocalDate.now();

        LocalTime time = LocalTime.now();
        DateTimeFormatter time1;
        time1 = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(time1);

        try{
            FileWriter writer = new FileWriter(transactionFile, true);

            StringBuilder userInfo = new StringBuilder();


            String amountInString = String.valueOf(Math.round(amount * 100.0) / 100.0);
            userInfo.append(date + "|" + formattedTime + "|" + description + "|" + vendor + "|" + amountInString + "\n");
            writer.write(String.valueOf(userInfo));

            writer.close();

        }
        catch (IOException e){
            e.getMessage();
        }

    }




    //MakePayment method
    public static void MakePayment(){
        //this is when the money is leaving your account

        //get users deposit information and save it to .csv
        //things to change here (the date) (the time)

        String description = console.promptForString("Please enter the Description of your payment: ");
        String vendor = console.promptForString("Please enter the Vendor name: ");
        double amount = console.promptForDouble("Please enter the amount here: ");


        LocalDate date = LocalDate.now();

        LocalTime time = LocalTime.now();
        DateTimeFormatter time1;
        time1 = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(time1);

        try{
            FileWriter writer = new FileWriter(transactionFile, true);

            StringBuilder userInfo = new StringBuilder();


            String amountInString = String.valueOf((Math.round(amount * 100.0) / 100.0) * -1);
            userInfo.append(date + "|" + formattedTime + "|" + description + "|" + vendor + "|" + amountInString + "\n");
            writer.write(String.valueOf(userInfo));

            writer.close();

        }
        catch (IOException e){
            e.getMessage();
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
