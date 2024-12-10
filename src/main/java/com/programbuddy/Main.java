package com.programbuddy;

import javax.swing.JOptionPane;

public class Main {

    KeyboardChecker keyboardChecker = new KeyboardChecker();

    public static void main(String[] args) {

        Checker checker = new Checker();
        int userInput;
        String characterName;

        JOptionPane.showMessageDialog(null, "Welcome to\nBlake Warnock's Programming Buddy v0.001", "Welcome", JOptionPane.DEFAULT_OPTION);
        //TODO add functionality to save information to computer and add check here is user already has a character!!!
        do {
            characterName = JOptionPane.showInputDialog(null, "Enter Character Name!", "Character Creation", JOptionPane.DEFAULT_OPTION);
        } while (!checker.checkString(characterName));

        Character character = new Character(characterName) {
        };

        MainMenu mainMenu = new MainMenu();
        StatsMenu statsMenu = new StatsMenu();
        LevelUpMenu levelUpMenu = new LevelUpMenu();
        AdventureMenu adventureMenu = new AdventureMenu();

        while (true) {
            userInput = mainMenu.runMenu(character);
            System.out.println("user inputed the following input " + userInput);
            switch (userInput) {
                case 1 -> {
                    //Display Adventure Menu
                    adventureMenu.runMenu(character);
                }
                case 2 -> {
                    //Display Character Stats
                    System.err.println("Ran Character Stat Menu");
                    statsMenu.runMenu(character);
                }
                case 3 -> {
                    //Display Level Up Screen
                    System.err.println("Ran Level Up Screen");
                    levelUpMenu.runMenu(character);
                }
                case 4 -> {
                    System.exit(0);
                    //Exit
                }
                case 777 -> {
                    character.gainExp(character.getExpToNextLvl());
                }
                default ->
                    throw new AssertionError();
            }
        }

    }
}
