package com.pluralsight;

import static com.pluralsight.model.Transactions.*;
import static com.pluralsight.ui.HomeScreen.*;

public class MainApp {
    public static void main(String[] args){

        loader(); //will load the arraylist
        homeScreen(); //will call the method in home screen to start the application
        listSort(); //sorts the list
        loadCSVFile(); //loads the csv file with the transactions

    }


}
