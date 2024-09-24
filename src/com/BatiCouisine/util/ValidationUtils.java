package com.BatiCouisine.util;

import java.util.Scanner;

public class ValidationUtils {
    public static int getValidInput(Scanner input) {

        while (!input.hasNextInt()) {

            System.out.println("Invalid input. Please enter a number.");
            input.next();
            input.nextLine();
        }
        return input.nextInt();
    }
}
