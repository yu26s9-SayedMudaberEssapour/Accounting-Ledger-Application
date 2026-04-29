package com.pluralsight.model;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class AppLauncher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage window; //reference to the main window

    @Override
    public void start(Stage primaryStage) {
        this.window = primaryStage;
        window.setTitle("Accounting Ledger Application");


        primaryStage.setTitle("Accounting Ledger Application");

        // Welcome Label
        Label welcomeLabel = new Label("Welcome to the Home-Page!");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Menu Buttons
        Button btnDeposit = new Button("Add Deposit");
        Button btnPayment = new Button("Make Payment");
        Button btnLedger = new Button("View Ledger");
        Button btnExit = new Button("Exit");

        // Set button widths for a uniform look
        btnDeposit.setPrefWidth(150);
        btnPayment.setPrefWidth(150);
        btnLedger.setPrefWidth(150);
        btnExit.setPrefWidth(150);

        // Add Actions (We will build these methods next)
        btnDeposit.setOnAction(e -> handleDeposit());
        //button functionality for payment
        btnPayment.setOnAction(e -> handlePayment());



        // Layout container
        VBox menuLayout = new VBox(15); // 15px spacing between elements
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(welcomeLabel, btnDeposit, btnPayment, btnLedger, btnExit);

        Scene scene = new Scene(menuLayout, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

        VBox homeLayout = new VBox(15);
        homeLayout.setAlignment(Pos.CENTER);

        //now add action so when it says view ledger it should go to ledger page
        btnPayment.setOnAction(e ->showLedgerScreen());

        homeLayout.getChildren().add(btnLedger);
        window.setScene(new Scene(homeLayout, 400,350));
        window.show();

        btnExit.setOnAction(e -> primaryStage.close());
    }


    private void handlePayment() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Payment");
        dialog.setHeaderText("Enter Deposit Details (Format: Description|Vendor|Amount)");
        dialog.setContentText("Details:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> {
            try {
                String[] parts = input.split("\\|");
                String desc = parts[0];
                String vendor = parts[1];
                double amount = Double.parseDouble(parts[2]);

                // Call your existing logic!
                HomeScreen.saveTransaction(desc, vendor, amount);
                System.out.println("Saved successfully!");
            } catch (Exception ex) {
                System.out.println("Invalid format. Use: Description|Vendor|Amount");
            }
        });
    }





    private void handleDeposit() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Deposit");
        dialog.setHeaderText("Enter Deposit Details (Format: Description|Vendor|Amount)");
        dialog.setContentText("Details:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> {
            try {
                String[] parts = input.split("\\|");
                String desc = parts[0];
                String vendor = parts[1];
                double amount = Double.parseDouble(parts[2]);

                // Call your existing logic!
                HomeScreen.saveTransaction(desc, vendor, amount);
                System.out.println("Saved successfully!");
            } catch (Exception ex) {
                System.out.println("Invalid format. Use: Description|Vendor|Amount");
            }
        });






}


    private void showLedgerScreen(){
        VBox ledgerLaout = new VBox(10);
        ledgerLaout.setAlignment((Pos.CENTER));

        Button btnBack = new Button("Back to Home");
        btnBack.setOnAction(e -> start(window));//goes back home

        ledgerLaout.getChildren().addAll(new javafx.scene.control.Label("Ledger Screen"), btnBack);


        //switch the scene
        window.setScene(new Scene(ledgerLaout, 600, 400));


    }
}
