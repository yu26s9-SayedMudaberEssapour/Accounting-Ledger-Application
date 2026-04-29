package com.pluralsight.model.controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Accounting Ledger");

        // UI Elements
        Label header = new Label("Ledger Management System");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button btnDeposit = new Button("Add Deposit");
        Button btnPayment = new Button("Make Payment");
        Button btnLedger = new Button("View Ledger");
        Button btnExit = new Button("Exit");

        // Styling buttons for consistency
        String btnStyle = "-fx-pref-width: 200px; -fx-font-size: 14px;";
        btnDeposit.setStyle(btnStyle);
        btnPayment.setStyle(btnStyle);
        btnLedger.setStyle(btnStyle);
        btnExit.setStyle(btnStyle);

        // Layout
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.getChildren().addAll(header, btnDeposit, btnPayment, btnLedger, btnExit);

        // Simple Actions
        btnExit.setOnAction(e -> primaryStage.close());

        // This is where you will link your screens
        btnLedger.setOnAction(e -> System.out.println("Switching to Ledger View..."));

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}