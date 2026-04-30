package com.pluralsight;


import com.pluralsight.model.Transactions;

import java.time.LocalDateTime;

import static com.pluralsight.model.Transactions.*;
import static com.pluralsight.ui.HomeScreen.*;
import static com.pluralsight.ui.LedgerScreen.*;
import static com.pluralsight.ui.Reports.*;


public class MainApp {
    public static void main(String[] args){

        //searchByVendor();
        //customSearch();
        //prevYear();
        //yearTodate();
        //prevMonth();
        //monthToDate();


        //now testing ledger

        //displayAll();
        //displayPayment();
        //displayDeposit();
        //LocalDateTime noe = LocalDateTime.now();
        //System.out.println(noe);


//        for(Transactions e : shortMemory()){
//            System.out.println(e);
//        }






        //now testing methods in home screen

        //MakeDeposit();
        displayDeposit();


    }
}
