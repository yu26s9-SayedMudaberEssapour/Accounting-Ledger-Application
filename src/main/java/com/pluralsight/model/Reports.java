package com.pluralsight.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import static com.pluralsight.model.HomeScreen.*;
import static com.pluralsight.model.HomeScreen.transactionFile;

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
            String input = console.promptForString("Please Enter one of the above options: ");

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
            BufferedReader br = readFileWithoutHeader(transactionFile);

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
            BufferedReader br = readFileWithoutHeader(transactionFile);
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
            BufferedReader br = readFileWithoutHeader(transactionFile);

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
            BufferedReader br = readFileWithoutHeader(transactionFile);
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
        String nameOfVendor = console.promptForString("Please enter the name of the vendor: ");

        try{
            BufferedReader br = readFileWithoutHeader(transactionFile);
            String line;
            while((line = br.readLine()) != null){
                String[] spl = line.split("\\|");

                String vendor = spl[3];

                if(vendor.equalsIgnoreCase(nameOfVendor)){
                    System.out.println(line.toString());
                }
            }

        }
        catch (IOException e){
            e.getMessage();
        }




}



public static void customSearch()

    {
        try{
            String StartDate = console.promptForString("Please enter a start date: (yyyy-MM-DD): ");
            String EndDate = console.promptForString("Please enter an end date: (yyyy-MM-DD): ");
            LocalDate sDate = LocalDate.parse(StartDate);
            LocalDate eDate = LocalDate.parse(EndDate);

            String Description = console.promptForString("Please enter a description ");
            String vendor = console.promptForString("Vendor: ");
            double amount = console.promptForDouble("Amount: ");

            BufferedReader br = readFileWithoutHeader(transactionFile);
            String line;
            while((line = br.readLine()) != null) {

                String[] str = line.split("\\|");

                String date = str[0];
                String desc = str[2];
                String vendors = str[3];
                double amounts = Double.parseDouble(str[4]);

                LocalDate dates = LocalDate.parse(date);


                if ((dates.isAfter(sDate) || date.equals(sDate)) && (dates.isBefore(eDate) || date.equals(eDate))) {
                    System.out.println(line);
                } else if (Description.equalsIgnoreCase(desc)) {
                    System.out.println(line);
                } else if (vendor.equalsIgnoreCase(vendors)) {
                    System.out.println(line);
                } else if (amounts == amount) {
                    System.out.println(line);
                }


            }}
        catch (Exception e){
            System.out.println("Sorry invalid value please try again: ");
            e.getMessage();

        }


    }



}


