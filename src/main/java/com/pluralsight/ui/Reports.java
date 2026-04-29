package com.pluralsight.ui;

import com.pluralsight.model.Transactions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import static com.pluralsight.model.Transactions.shortMemory;
import static com.pluralsight.ui.HomeScreen.*;
import static com.pluralsight.ui.HomeScreen.transactionFile;

public class Reports {

    public static void ReportsScreen()
    {
        boolean shouldContinue = true;
        do {
            System.out.println();
            System.out.println(
                    "Welcome to the Report-Screen!! \n" +
                            "Type (1) to display Month to date Report: \n" +
                            "Type (2) to display Previous month: \n" +
                            "Type (3) to display Year to date: \n" +
                            "Type (4) to display previous year \n" +
                            "Type (5) to Search by vendor\n" +
                            "Type (6) to do a custom Search\n" +
                            "Type (0) to go back to the ledger page");
            System.out.println();
            String input = Console.promptForString("Please Enter one of the above options: ");

            switch(input){
                case "1" :
                    monthToDate();
                    break;
                case "2" :
                    prevMonth();
                    break;
                case "3" :
                    yearTodate();
                    break;
                case "4" :
                    prevYear();
                    break;
                case "5" :
                    searchByVendor();
                    break;
                case "6" :
                    customSearch();
                    break;
                case "0" :
                    return;
                default :
                    shouldContinue = false;
                    break;
            }
        }

        while(shouldContinue);
    }


    /**
     * it works to how I intend it to work.
     */
    public static void monthToDate(){

        //This will give the current date of my local area
        LocalDate date = LocalDate.now();

        DateTimeFormatter date1;
        date1 = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedTime = date.format(date1);

        String yearMonth = formattedTime;

        //I can split yearMonth and compare the values

        //then get all of the other ones in the same month

        try{
            FileReader fr = new FileReader(transactionFile);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();

            String line;

            ArrayList<String> lines = new ArrayList<>();

            while((line = br.readLine()) != null){

                //split by category
                String[] splittingLine = line.split("\\|");

                //split by date
                String[] splitDate = splittingLine[0].split("-");


                String year = splitDate[0];
                String month = splitDate[1];
                String yearAndMonth = year + "-" + month;

                if(yearAndMonth.equalsIgnoreCase(yearMonth)){
                    lines.add(line);
                }

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

    public static void prevMonth()
    {
        //This will give the current date of my local area
        LocalDate date = LocalDate.now();

        DateTimeFormatter date1;
        date1 = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedTime = date.format(date1);

        String[] splYearmonth = formattedTime.split("-");

        String yearNow = splYearmonth[0];
        int monthNow = Integer.parseInt(splYearmonth[1]);



        //find a way to get the month and year by themselves


        try{
            FileReader fr = new FileReader(transactionFile);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            ArrayList<String> lines = new ArrayList<>();

            while((line = br.readLine()) != null){

                //split by category
                String[] splittingLine = line.split("\\|");

                //split by date
                String[] splitDate = splittingLine[0].split("-");


                String year = splitDate[0];
                int month = Integer.parseInt(splitDate[1]);

                int prevmonth = monthNow - 2;
                if((year.equalsIgnoreCase(yearNow)) && ( (month > prevmonth) && (month < monthNow))){
                    lines.add(line);
                }

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


    public static void yearTodate()
    {
        //This will give the current date of my local area
        LocalDate date = LocalDate.now();

        DateTimeFormatter date1;
        date1 = DateTimeFormatter.ofPattern("yyyy");
        String formattedTime = date.format(date1);

        String year = formattedTime;

        try{
            FileReader fr = new FileReader(transactionFile);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();

            String line;

            ArrayList<String> lines = new ArrayList<>();

            while((line = br.readLine()) != null){

                //split by category
                String[] splittingLine = line.split("\\|");

                //split by date
                String[] splitDate = splittingLine[0].split("-");

                String yearsInFile = splitDate[0];

                if(yearsInFile.equalsIgnoreCase(year)){
                    lines.add(line);
                }
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


    public static void prevYear()
    {
        //This will give the current date of my local area
        LocalDate date = LocalDate.now();

        DateTimeFormatter date1;
        date1 = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedTime = date.format(date1);

        String[] splYearmonth = formattedTime.split("-");

        int yearNow = Integer.parseInt(splYearmonth[0]);

        try{
            FileReader fr = new FileReader(transactionFile);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            ArrayList<String> lines = new ArrayList<>();

            while((line = br.readLine()) != null){

                //split by category
                String[] splittingLine = line.split("\\|");

                //split by date
                String[] splitDate = splittingLine[0].split("-");


                int year = Integer.parseInt(splitDate[0]);

                int prevYear = yearNow - 2;
                if((year > prevYear) && (year < yearNow)){
                    lines.add(line);
                }

            }
            //fix this so that it works with all not just a sorted list
            Collections.reverse(lines);

            for(String L : lines){
                System.out.println(L.toString());
            }
        }
        catch (IOException e){
            e.getMessage();
        }
    }


    public static void searchByVendor()
    {
        String nameOfVendor = Console.promptForString("Please enter the name of the vendor: ");


        for(Transactions e : shortMemory()){
            if(e.getVendor().equalsIgnoreCase(nameOfVendor)){
                System.out.println(e);
            }
        }




}



//public static void customSearch()
//
//    {
//        try{
//            String StartDate = Console.promptForString("Please enter a start date: (yyyy-MM-DD): ");
//            String EndDate = Console.promptForString("Please enter an end date: (yyyy-MM-DD): ");
//            LocalDate sDate = LocalDate.parse(StartDate);
//            LocalDate eDate = LocalDate.parse(EndDate);
//
//            String Description = Console.promptForString("Please enter a description ");
//            String vendor = Console.promptForString("Vendor: ");
//            double amount = Console.promptForDouble("Amount: ");
//
//            FileReader fr = new FileReader(transactionFile);
//            BufferedReader br = new BufferedReader(fr);
//            br.readLine();
//            String line;
//            while((line = br.readLine()) != null) {
//
//                String[] str = line.split("\\|");
//
//                String date = str[0];
//                String desc = str[2];
//                String vendors = str[3];
//                double amounts = Double.parseDouble(str[4]);
//
//                LocalDate dates = LocalDate.parse(date);
//
//
//                if ((dates.isAfter(sDate) || date.equals(sDate)) && (dates.isBefore(eDate) || date.equals(eDate))) {
//                    System.out.println(line);
//                } else if (Description.equalsIgnoreCase(desc)) {
//                    System.out.println(line);
//                } else if (vendor.equalsIgnoreCase(vendors)) {
//                    System.out.println(line);
//                } else if (amounts == amount) {
//                    System.out.println(line);
//                }
//
//
//            }}
//        catch (Exception e){
//            System.out.println("Sorry invalid value please try again: ");
//            e.getMessage();
//
//        }


    //}

    public static void customSearch(){
        try{
            String StartDate = Console.promptForString("Please enter a start date: (yyyy-MM-DD): ");
            String EndDate = Console.promptForString("Please enter an end date: (yyyy-MM-DD): ");
            LocalDate sDate = LocalDate.parse(StartDate);
            LocalDate eDate = LocalDate.parse(EndDate);

            String Description = Console.promptForString("Please enter a description ");
            String vendor = Console.promptForString("Vendor: ");
            double amount = Console.promptForDouble("Amount: ");

            for(Transactions e : shortMemory()){

                LocalDate dates = LocalDate.parse(e.getDate());
                if ((dates.isAfter(sDate) || e.getDate().equals(sDate)) && (dates.isBefore(eDate) || e.getDate().equals(eDate))) {
                    System.out.println(e.getDate() + "|" + e.getTime() + "|" +  e.getDescription() + "|" + e.getVendor() + "|" +  e.getAmount());
                } else if (Description.equalsIgnoreCase(e.getDescription())) {
                    System.out.println(e.getDate() + "|" + e.getTime() + "|" +  e.getDescription() + "|" + e.getVendor() + "|" +  e.getAmount());
                } else if (vendor.equalsIgnoreCase(e.getVendor())) {
                    System.out.println(e.getDate() + "|" + e.getTime() + "|" +  e.getDescription() + "|" + e.getVendor() + "|" +  e.getAmount());
                } else if (e.getAmount() == amount) {
                    System.out.println(e.getDate() + "|" + e.getTime() + "|" +  e.getDescription() + "|" + e.getVendor() + "|" +  e.getAmount());
                }

            }}
        catch (Exception e){
            System.out.println("Sorry invalid value please try again: ");
            e.getMessage();

        }
    }





}


