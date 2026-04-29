package com.pluralsight.model;

import com.pluralsight.ui.Console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class Transactions {

    private String date;
    private String time; //for this import time
    private String description;
    private String vendor;
    private double amount;

    public static final String transactionFile = "src/main/java/com/pluralsight/data/transaction.csv";

    public Transactions(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setData(String data) {
        this.date = data;
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

    //make an arraylist that keeps track of all of the items in transactions.csv


    public static ArrayList<Transactions> fileContent = new ArrayList<>();

    public static ArrayList<Transactions> shortMemory(){

        try{

            FileReader fr = new FileReader(transactionFile);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();

            String line;

            while((line = br.readLine()) != null){
                String[] str = line.split("\\|");

                String date = str[0];
                String time = str[1];
                String desc = str[2];
                String vendor = str[3];
                double amount = Double.parseDouble(str[4]);

                fileContent.add(new Transactions(date, time, desc, vendor, amount));

        }}
        catch (IOException e){
            e.getMessage();
        }

        return fileContent;

    }


    public static void writeShortMemory(String date, String time, String description, String vendor, double amount)
    {
        fileContent.add(new Transactions(date, time, description, vendor, amount));
    }




    @Override
    public String toString() {
        return "Transactions{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }
}
