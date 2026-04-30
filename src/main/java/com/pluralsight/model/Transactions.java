package com.pluralsight.model;

import com.pluralsight.ui.Console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Transactions {

    private String date;
    private String time; //for this import time
    private String description;
    private String vendor;
    private double amount;
    private LocalDateTime localDateTime;

    public static final String transactionFile = "src/main/java/com/pluralsight/data/transaction.csv";

    public Transactions(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

        this.localDateTime = LocalDateTime.parse(date + "T" + time);
    }

    public String getDate() {
        return date;
    }

    public void setData(String date) {
        this.date = date;
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

    public LocalDateTime getLocalDT() {
        return this.localDateTime;
    }



    //make an arraylist that keeps track of all of the items in transactions.csv
    public static ArrayList<Transactions> fileContent = new ArrayList<>();


    //always at runtime your array should be sorted

    public static ArrayList<Transactions> shortMemory()
    {
        try {

            FileReader fr = new FileReader(transactionFile);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();

            String line;

            while ((line = br.readLine()) != null)
            {
                String[] str = line.split("\\|");

                String date1 = str[0];
                String time1 = str[1];
                String desc1 = str[2];
                String vendor1 = str[3];
                double amount1 = Double.parseDouble(str[4]);

                fileContent.add(new Transactions(date1, time1, desc1, vendor1, amount1));

            }
        }

        catch (IOException e)
        {
            e.getMessage();
        }

        fileContent.sort(Comparator.comparing(Transactions::getLocalDT));

        Collections.reverse(fileContent);



        return fileContent;

    }





    //____________________________________writing_____________________________________


    public static void writeShortMemory(String date, String time, String description, String vendor, double amount)
    {
        fileContent.add(new Transactions(date, time, description, vendor, amount));
    }



    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%.2f", date, time, description, vendor, amount);
    }
}
