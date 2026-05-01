package com.pluralsight;

import static com.pluralsight.model.Transactions.*;
import static com.pluralsight.ui.HomeScreen.*;

public class MainApp {
    public static void main(String[] args){

        //right now it stays at the bottom of the file

        //it does not add to the top when d or p
        loader();
        homeScreen();
        listSort();
        loadCSVFile();

      //whevenever a new one is being added it should be added to the top


    }


}
