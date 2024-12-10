package com.programbuddy;

public class Checker {

    int intValue;
    double doubleValue;

    // checks input to ensure it is an int
    boolean checkInt(String num) {

        System.out.println(String.format("Parsing int: \"%s\"", num));

        if (num == null || num.equals("")) {
            System.out.println("int cannot be parsed, it is null or empty.");
            return false;
        }
        try {
            intValue = Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

    // checks input to ensure it is an double
    boolean checkDouble(String num) {

        System.out.println(String.format("Parsing int: \"%s\"", num));

        if (num == null || num.equals("")) {
            System.out.println("double cannot be parsed, it is null or empty.");
            return false;
        }
        try {
            doubleValue = Double.parseDouble(num);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to double.");
        }
        return false;
    }

    // checks input to ensure it is a string
    boolean checkString(String string) {

        System.out.println(String.format("Parsing string: \"%s\"", string));

        if (string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }
        return true;
    }
}
