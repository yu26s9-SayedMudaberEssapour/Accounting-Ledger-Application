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


    /**
     * Report Screen Method
     * The method is responsible for prompting the user for 7 different options.
     * Options 1 - 4 are displaying by different dates.
     * Option 5 - 6 are asking the user's input on what to display
     * Option 0 will be returning you back to the ledger page
     */
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
                    yearToDate();
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

//------------------------My Helper Methods---------------------------------
    /**
     * monthFormatter is a helper Method that formats my current months into yyyy-MM
     * @return a formatted month so that users could access them and use them.
     */
    public static String monthFormatter() {
        //This will give the current date of my local area
        LocalDate date = LocalDate.now();
        DateTimeFormatter date1;
        date1 = DateTimeFormatter.ofPattern("yyyy-MM");
        return date.format(date1);
    }

    /**
     * This helper Method prompts the user for an option to stay on the same screen or to leave.
     * @return the input of the user.
     */
    public static String promptToStay(){
        return Console.promptForString(
                "Type (yes) to stay on this Screen, Type (no) to go to ledger Screen: ");

    }

    /**
     * This helper method is similar to the monthFormater, but it formats the year
     * @return an int of formatted year that looks like such "yyyy"
     */
    public static int yearFormater() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter date1;
        date1 = DateTimeFormatter.ofPattern("yyyy");
        return Integer.parseInt(date.format(date1));
    }

    //------------------------Report Screen Methods---------------------------------
    /**
     * This method calls the monthFormatter and uses the formatted month to then compare to all the other months in the CSV file.
     * Once the comparison is done, if it is a similar month then it gets printed out to the user.
     */
    public static void monthToDate() {

        String yearMonth = monthFormatter();
        System.out.println("Your Month to Date report: ");
        for(Transactions e : fileContent){
            String[] splitDate = e.getDate().split("-");
            String year = splitDate[0];
            String month = splitDate[1];
            String yearAndMonth = year + "-" + month;

            if (yearAndMonth.equalsIgnoreCase(yearMonth)) {
                System.out.print(e);
            }
        }
        System.out.println();
    }


    /**
     * This method will be printing out the all the transactions from the previous month.
     * It first takes the return of monthFormatter() and then splits it into year month
     * It then stores the current year and current month in variables
     * It searches the fileContent to see if there are transactions for the prior month and prints them.
     */
    public static void prevMonth() {
        String[] splYearmonth = monthFormatter().split("-");
        String yearNow = splYearmonth[0];
        int monthNow = Integer.parseInt(splYearmonth[1]);
        System.out.println("Previous Month report: ");

        for(Transactions e : fileContent){
            String[] splitDate = String.valueOf(e.getDate()).split("-");
            String yearInFile = splitDate[0];
            int monthInFile = Integer.parseInt(splitDate[1]);

            int prevMonth = monthNow - 2;

            if ((yearInFile.equalsIgnoreCase(yearNow)) && ((monthInFile > prevMonth) && (monthInFile < monthNow))) {
                System.out.print(e);}
        }
        System.out.println();}

    /**
     * This method takes the return of the yearFormater and looks through fileContent to find all the transactions that
     * were year to date, and then it simply prints it to console.
     */
    public static void yearToDate() {

        int yearNow = yearFormater();
        System.out.println("Year to Date report: ");
        for(Transactions e : fileContent){
            String[] splitDate = String.valueOf(e.getDate()).split("-");
            int yearsInFile = Integer.parseInt(splitDate[0]);
            if (yearsInFile == yearNow){
                System.out.print(e);}
        }
        System.out.println();}

    /**
     * This method calls yearFormater and gets the current year.
     * It then uses the current year to compare it to all the transactions and print the previous year's transactions.
     */
    public static void prevYear() {
        int yearNow = yearFormater();
        System.out.println("Previous year report: ");
        for(Transactions e : fileContent){

            String[] splitDate = String.valueOf(e.getDate()).split("-");
            int year = Integer.parseInt(splitDate[0]);
            int prevYear = yearNow - 2;
            if ((year > prevYear) && (year < yearNow)) {
                System.out.print(e);
            }}
        System.out.println();
    }


    /**
     * This method takes a vendor name from the user and prints the transaction if it exists.
     */
    public static void searchByVendor() {
        String nameOfVendor = Console.promptForString("Please enter the name of the vendor: ");
        System.out.println();

        int vendorCounter = 0;
        System.out.println("Your Vendor Search Result: ");

        for(Transactions e : fileContent){
            String vendor = e.getVendor();
            if (vendor.equalsIgnoreCase(nameOfVendor)){
                System.out.print(e);
                vendorCounter ++;}}
        System.out.println();

        if(vendorCounter == 0){
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


