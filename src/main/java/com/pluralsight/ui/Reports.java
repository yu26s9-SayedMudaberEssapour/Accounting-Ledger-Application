package com.pluralsight.ui;

import com.pluralsight.model.Transactions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.jar.JarOutputStream;

import static com.pluralsight.model.Transactions.fileContent;
import static com.pluralsight.ui.LedgerScreen.displayAll;


public class Reports {

    public static void ReportsScreen() {
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
                            "Type (0) to go back to the ledger page\n");

            String input = Console.promptForString("Please Enter one of the above options:");
            System.out.println();
            switch (input) {
                case "" :
                    System.out.println("Please type a valid option or (0) to return to Ledger screen: ");
                    break;
                case "1":
                    monthToDate();
                    if(promptToStay().equalsIgnoreCase("yes")){
                        break;
                    }
                    else{
                        return;
                    }
                case "2":
                    prevMonth();
                    if(promptToStay().equalsIgnoreCase("yes")){
                        break;
                    }
                    else{
                        return;
                    }
                case "3":
                    yearTodate();
                    if(promptToStay().equalsIgnoreCase("yes")){
                        break;
                    }
                    else{
                        return;
                    }
                case "4":
                    prevYear();
                    if(promptToStay().equalsIgnoreCase("yes")){
                        break;
                    }
                    else{
                        return;
                    }
                case "5":
                    searchByVendor();
                    if(promptToStay().equalsIgnoreCase("yes")){
                        break;
                    }
                    else{
                        return;
                    }
                case "0":
                    return;
                default:
                    shouldContinue = false;
                    break;
            }
        }
        while (shouldContinue);
    }

    public static String monthFormatter() {
        //This will give the current date of my local area
        LocalDate date = LocalDate.now();
        DateTimeFormatter date1;
        date1 = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedTime = date.format(date1);

        return formattedTime;
    }

    public static String promptToStay(){
        return Console.promptForString(
                "Type (yes) to stay on this Screen, Type (no) to go to ledger Screen: ");

    }


    /**
     * it works to how I intend it to work.
     */
    public static void monthToDate() {

        String yearMonth = monthFormatter();
        System.out.println("Your Month to Date report: ");
        for(Transactions e : fileContent){
            String[] splitDate = e.getDate().split("-");
            String year = splitDate[0];
            String month = splitDate[1];
            String yearAndMonth = year + "-" + month;

            //fix this later
            if (yearAndMonth.equalsIgnoreCase(yearMonth)) {
                System.out.print(e);
            }
        }
        System.out.println();

    }


    //not printing anything
    public static void prevMonth() {
        String[] splYearmonth = monthFormatter().split("-");
        //month and year right now
        String yearNow = splYearmonth[0];
        int monthNow = Integer.parseInt(splYearmonth[1]);
        System.out.println("Previous Month report: ");
        for(Transactions e : fileContent){

            String[] splitDate = String.valueOf(e.getDate()).split("-");
            String yearInFile = splitDate[0];
            int monthInFile = Integer.parseInt(splitDate[1]);

            int prevmonth = monthNow - 2;

            if ((yearInFile.equalsIgnoreCase(yearNow)) && ((monthInFile > prevmonth) && (monthInFile < monthNow))) {
                System.out.print(e);
            }

        }
        System.out.println();

    }


    //___________________________year_____________________________________

    public static String yearFormater() {
        //This will give the current date of my local area
        LocalDate date = LocalDate.now();

        DateTimeFormatter date1;
        date1 = DateTimeFormatter.ofPattern("yyyy");
        String formattedTime = date.format(date1);
        return formattedTime;
    }




    //this method is also working
    public static void yearTodate() {

        String year = yearFormater();
        System.out.println("Year to Date report: ");
        for(Transactions e : fileContent){
            String[] splitDate = String.valueOf(e.getDate()).split("-");
            String yearsInFile = splitDate[0];
            if (yearsInFile.equalsIgnoreCase(year)){
                System.out.print(e);
        }
        }
        System.out.println();

    }

    //alhamdullilah it is working
    //refactored just need to fix the reversing problem
    public static void prevYear() {

        String[] splYearmonth = yearFormater().split("-");
        //this takes the year right now
        int yearNow = Integer.parseInt(splYearmonth[0]);
        System.out.println("Previous year report: ");
        for(Transactions e : fileContent){
            String[] splitDate = String.valueOf(e.getDate()).split("-");

            int year = Integer.parseInt(splitDate[0]);
            int prevYear = yearNow - 2;
            if ((year > prevYear) && (year < yearNow)) {
                System.out.print(e);
            }

        }
        System.out.println();
    }


    //if something does not exist it needs to repromt the user
    public static void searchByVendor() {
        String nameOfVendor = Console.promptForString("Please enter the name of the vendor: ");
        System.out.println();

        StringBuilder exist = new StringBuilder();
        System.out.println("Your Vendor Search Result: ");
        for(Transactions e : fileContent){
            String vendor = e.getVendor();
            if (vendor.equalsIgnoreCase(nameOfVendor)){
                System.out.print(e);
                exist.append("exist");

            }

        }
        System.out.println();
        if(exist.isEmpty()){
            System.out.println("Sorry your item does not exist or you have not entered anything: ");
        }

        }

    }


    //still needs to account for empty values, add try and catch method wherever needed
//    public static void customSearch(){
//            String StartDate = Console.promptForString("Please enter a start date: (yyyy-MM-DD): ");
//            String EndDate = Console.promptForString("Please enter an end date: (yyyy-MM-DD): ");
//            LocalDate sDate = LocalDate.parse(StartDate);
//            LocalDate eDate = LocalDate.parse(EndDate);
//
//            String Description = Console.promptForString("Please enter a description ");
//            String vendor = Console.promptForString("Vendor: ");
//            double amount = Console.promptForDouble("Amount: ");
//
//            try {
//                for (Transactions e : shortMemory()) {
//
//                    LocalDate dates = LocalDate.parse(e.getDate());
//                    if (dates.isAfter(sDate) && dates.isBefore(eDate) && !StartDate.equalsIgnoreCase("")) {
//                        lines.add(shortMemory().getFirst() + "|" + shortMemory().get(1) + "|" + shortMemory().get(2) + "|" + shortMemory().get(3) + "|" + shortMemory().get(4));
//                    } else if (Description.equalsIgnoreCase(e.getDescription()) && !(StartDate.equalsIgnoreCase(""))) {
//                        System.out.println(e.getDate() + "|" + e.getTime() + "|" + e.getDescription() + "|" + e.getVendor() + "|" + e.getAmount());
//
//                    } else if (vendor.equalsIgnoreCase(e.getVendor()) && !(StartDate.equalsIgnoreCase(""))) {
//                        System.out.println(e.getDate() + "|" + e.getTime() + "|" + e.getDescription() + "|" + e.getVendor() + "|" + e.getAmount());
//
//                    } else if (e.getAmount() == amount) {
//                        System.out.println(e.getDate() + "|" + e.getTime() + "|" + e.getDescription() + "|" + e.getVendor() + "|" + e.getAmount());
//                    }
//                }
//
//            }
//            catch (Exception e){
//                e.getMessage();
//            }
//
//
//
//
//
//
//}}


