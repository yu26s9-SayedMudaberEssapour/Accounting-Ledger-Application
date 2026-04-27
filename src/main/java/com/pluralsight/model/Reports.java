package com.pluralsight.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.pluralsight.model.HomeScreen.*;

public class Reports {

    public void ReportsScreen()
    {
        boolean shouldContinue = true;
        do {
            System.out.println(
                    "Welcome to the Ledger-Screen!! \n" +
                            "Type (1) to display Month to date Report: \n" +
                            "Type (2) to display Previous month: \n" +
                            "Type (3) to display Year to date: \n" +
                            "Type (4) to display previous year \n" +
                            "Type (5) to Search by vendor\n" +
                            "Type (0) to go back to the ledger page");

            String input = console.promptForString("Please Enter one of the above options: ");

            switch(input){
                case "1" :
                    System.out.println("monnth to date");;
                    break;
                case "2" :
                    System.out.println("prev month");;
                    break;
                case "3" :
                    System.out.println("year to date");
                    break;
                case "4" :
                    System.out.println("prev year");
                    break;
                case "5" :
                    System.out.println("serach by vendor");
                    break;
                case "0" :
                    System.out.println("go back to ledger page");
                    shouldContinue = false;
                    break;
                default :
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

            //this will be skipping the header in the file
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






















    //prev month

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

            //this will be skipping the header in the file
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

}
