package com.pluralsight.ui;

import java.util.Scanner;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user for a double.
     * @param prompt to display the user
     * @param scanner to interact with user
     * @return the double the user selected.
     */
    public static double promptForDouble(String prompt) {

        System.out.print(prompt);
        double result =  scanner.nextDouble();
        scanner.nextLine();
        return result;

    }

    public static float promptForFloat(String prompt) {

        System.out.print(prompt);
        float result =  scanner.nextFloat();
        scanner.nextLine();
        return result;

    }

    /**
     * Prompts the user for a string.
     * @param prompt to display the user
     * @param scanner to interact with user
     * @return the string the user selected.
     */
    public static String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Prompts the user for an integer.
     * @param prompt to display the user
     * @param scanner to interact with user
     * @return the int the user selected.
     */
    public static int promptForInt(String prompt) {

        System.out.print(prompt);
        int result =  scanner.nextInt();
        scanner.nextLine();
        return result;

    }

    /**
     * Prompts the user for a Yes or No which is returned as a boolean.
     * @param prompt to display the user
     * @param scanner to interact with user
     * @return the boolean
     */
    public static boolean promptForYesNo(String prompt) {
        System.out.print(prompt);
        String userInput = scanner.nextLine();
        return userInput.equalsIgnoreCase("YES");
        //opportunity to enhance this with some error protection.
    }

    public static long promptForLong(String prompt){
        System.out.println(prompt);
        long userInput = scanner.nextLong();
        scanner.nextLine();

        return userInput;
    }

}