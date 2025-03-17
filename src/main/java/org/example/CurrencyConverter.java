package org.example;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CurrencyConverter {

    //Exchange rates as constants
    private static final double SEK_TO_USD = 0.097851038;
    private static final double USD_TO_SEK = 10.2189;
    private static final double SEK_TO_EURO = 0.089541932;
    private static final double EURO_TO_SEK = 11.168;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while (running) {
            displayMenu();
            int choice = getUserChoice(scanner);

            if (choice == 0) {
                running = false;
                System.out.println("Exiting the program. ");
            } else {
                double amount = getAmount(scanner);
                handleConversion(choice, amount);
            }

        }
        scanner.close();
    }

    //Method to display the menu
    private static void displayMenu() {
        System.out.println("\nCurrency Converter App:");
        System.out.println("--------------------------");
        System.out.println("1. Convert SEK to USD");
        System.out.println("2. Convert USD to SEK");
        System.out.println("3. Convert SEK to EURO");
        System.out.println("1. Convert EURO to SEK");
        System.out.println("0. EXIT");
        System.out.println(" Enter your choice:");

    }

    private static int getUserChoice(Scanner scanner) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                if (choice >= 0 && choice <= 4) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 0 and 4 :");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number:");
                scanner.next();
            }
        }
    }

    private static double getAmount(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Enter the amount to convert :");
                double amount = scanner.nextDouble();
                if (amount >= 0) {
                    return amount;
                } else {
                    System.out.println("Amount cannot be negative ,please enter a positive number : ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. please enter a numeric value :");
                scanner.next();
            }
        }
    }


    private static void handleConversion(int choice, double amount) {
        double result = 0;
        String fromCurrency = "";
        String toCurrency = "";

        switch (choice) {

            case 1:
                result = amount * SEK_TO_USD;
                fromCurrency = "SEK";
                toCurrency = "USD";
                break;
            case 2:
                result = amount * USD_TO_SEK;
                fromCurrency = "USD";
                toCurrency = "SEK";
                break;
            case 3:
                result = amount * SEK_TO_EURO;
                fromCurrency = "SEK";
                toCurrency = "Euro";
                break;
            case 4:
                result = amount * EURO_TO_SEK;
                fromCurrency = "Euro";
                toCurrency = "SEK";
                break;

        }
        displayResult(amount, fromCurrency, result, toCurrency);

    }

    private static void displayResult(double amount, String fromCurrency, double convertedAmount, String toCurrency) {
        DecimalFormat df = new DecimalFormat("#.##");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("\nConversion Result:");
        System.out.println("------------------");
        System.out.println("From: " + df.format(amount) + " " + fromCurrency);
        System.out.println("To: " + df.format(convertedAmount) + " " + toCurrency);
        System.out.println("Date and Time: " + now.format(formatter));
        System.out.println("------------------\n");
    }
}
