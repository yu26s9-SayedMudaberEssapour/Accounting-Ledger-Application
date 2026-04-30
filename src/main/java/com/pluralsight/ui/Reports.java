package com.pluralsight.ui;

import com.pluralsight.model.Transactions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import static com.pluralsight.model.Transactions.shortMemory;

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
                            "Type (0) to go back to the ledger page");
            System.out.println();
            String input = Console.promptForString("Please Enter one of the above options: ");

            switch (input) {
                case "1":
                    monthToDate();
                    break;
                case "2":
                    prevMonth();
                    break;
                case "3":
                    yearTodate();
                    break;
                case "4":
                    prevYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "6":
                    //customSearch();
                    break;
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


    /**
     * it works to how I intend it to work.
     * collection.reverse only works in the list is sorted and if it is not it will not work
     */
    public static void monthToDate() {

        String yearMonth = monthFormatter();

        ArrayList<String> lines = new ArrayList<>();
        for(int i = 0; i <= shortMemory().size(); i++){
            String[] splitDate = String.valueOf(shortMemory().getFirst()).split("-");

            String year = splitDate[0];
            String month = splitDate[1];
            String yearAndMonth = year + "-" + month;

            if (yearAndMonth.equalsIgnoreCase(yearMonth)) {
                lines.add(shortMemory().getFirst() + "|" + shortMemory().get(1) + "|" + shortMemory().get(2) + "|" + shortMemory().get(3) + "|" + shortMemory().get(4));
            }
        }

        Collections.reverse(lines);

        for (String L : lines) {
            System.out.println(L.toString());
        }


    }


    public static void prevMonth() {
        String[] splYearmonth = monthFormatter().split("-");

        String yearNow = splYearmonth[0];
        int monthNow = Integer.parseInt(splYearmonth[1]);

        ArrayList<String> lines = new ArrayList<>();

        for(int i = 0; i <= shortMemory().size(); i++){
            String[] splitDate = String.valueOf(shortMemory().getFirst()).split("-");


            String year = splitDate[0];
            int month = Integer.parseInt(splitDate[1]);

            int prevmonth = monthNow - 2;
            if ((year.equalsIgnoreCase(yearNow)) && ((month > prevmonth) && (month < monthNow))) {
                lines.add(shortMemory().getFirst() + "|" + shortMemory().get(1) + "|" + shortMemory().get(2) + "|" + shortMemory().get(3) + "|" + shortMemory().get(4));
            }

        }

        Collections.reverse(lines);

        for (String L : lines) {
            System.out.println(L.toString());
        }

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


    public static void yearTodate() {


        String year = yearFormater();

        ArrayList<String> lines = new ArrayList<>();

        for(int i = 0; i <= shortMemory().size(); i++){
            String[] splitDate = String.valueOf(shortMemory().getFirst()).split("-");;

            String yearsInFile = splitDate[0];

            if (yearsInFile.equalsIgnoreCase(year)) {
                lines.add(shortMemory().getFirst() + "|" + shortMemory().get(1) + "|" + shortMemory().get(2) + "|" + shortMemory().get(3) + "|" + shortMemory().get(4));
            }
        }

        Collections.reverse(lines);

        for (String L : lines) {
            System.out.println(L.toString());
        }


    }


    //refactored just need to fix the reversing problem
    public static void prevYear() {

        String[] splYearmonth = yearFormater().split("-");

        int yearNow = Integer.parseInt(splYearmonth[0]);

        ArrayList<String> lines = new ArrayList<>();

        for(int i = 0; i <= shortMemory().size(); i++){
            String[] splitDate = String.valueOf(shortMemory().getFirst()).split("-");

            int year = Integer.parseInt(splitDate[0]);
            int prevYear = yearNow - 2;

            if ((year > prevYear) && (year < yearNow)) {
                lines.add(shortMemory().getFirst() + "|" + shortMemory().get(1) + "|" + shortMemory().get(2) + "|" + shortMemory().get(3) + "|" + shortMemory().get(4));
            }
        }
        //fix this part of the code
        Collections.reverse(lines);

        for (String L : lines) {
            System.out.println(L.toString());
        }
    }


    public static void searchByVendor() {
        String nameOfVendor = Console.promptForString("Please enter the name of the vendor: ");


        for(int i = 0; i <= shortMemory().size(); i++){
            if (String.valueOf(shortMemory().get(3)).equalsIgnoreCase(nameOfVendor)) {
                System.out.println(shortMemory().getFirst() + "|" + shortMemory().get(1) + "|" + shortMemory().get(2) + "|" + shortMemory().get(3) + "|" + shortMemory().get(4));
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


}