package com.pluralsight.ui;
import com.pluralsight.model.Transactions;
import static com.pluralsight.model.Transactions.*;
import static com.pluralsight.ui.LedgerScreen.LedgerScreens;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class HomeScreen{

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


            input = Console.promptForString("Please Enter one of the above options: ");

            switch(input.toUpperCase()){
                case "" :
                    System.out.println("Please type a valid option or (X) to exit: ");
                    break;
                case "D" :
                    MakeDeposit();
                    break;
                case "P" :
                    MakePayment();
                    break;
                case "L" :
                    LedgerScreens();
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
    public static void MakeDeposit()
    {

        try{
            String description = Console.promptForString("Please enter the Description of your deposit: ");
            String vendor = Console.promptForString("Please enter the Vendor name: ");
            double amount = Console.promptForDouble("Please enter the amount here: ");
            LocalDate date = LocalDate.now();
            String date1 = String.valueOf(date);

            fileContent.add(new Transactions(date1, timeReturn(), description, vendor, (amount)));

        }catch (Exception e){
            System.out.println("Sorry invalid value, please enter only a number: ");

        }

    }


    //MakePayment method
    public static void MakePayment(){

        try{
            String description = Console.promptForString("Please enter the Description of your deposit: ");
            String vendor = Console.promptForString("Please enter the Vendor name: ");
            double amount = Console.promptForDouble("Please enter the amount here: ");
            LocalDate date = LocalDate.now();
            String date1 = String.valueOf(date);

            fileContent.add(new Transactions(date1, timeReturn(), description, vendor, (amount * -1)));

        }catch (Exception e){
            System.out.println("Sorry invalid value, please enter only a number: ");

        }



    }
    public static String timeReturn(){

        LocalTime time = LocalTime.now();
        DateTimeFormatter time1;
        time1 = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(time1);

        return formattedTime;
    }



}
