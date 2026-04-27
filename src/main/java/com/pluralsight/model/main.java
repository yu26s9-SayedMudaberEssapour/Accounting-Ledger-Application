package com.pluralsight.model;

public class main {
    public static void main(String args[]){
        HomeScreen hs = new HomeScreen();

        //hs.homeScreen();

        //hs.deposit();
        //hs.MakePayment();

        //soon they will be merged together.
        LedgerScreen ls = new LedgerScreen();
        System.out.println("displaying all");
        ls.displayAll();

        System.out.println("displaying payments");
        ls.payment();

        System.out.println("displaying deposits");

        ls.displayDeposit();

    }
}
