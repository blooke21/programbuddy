package com.programbuddy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class Main {

    private static final String CHAR_FILE = "character.ser";

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Welcome to\nBlake Warnock's Programming Buddy v0.001", "Welcome", JOptionPane.DEFAULT_OPTION);

        Character character = intializeCharacter();
        CharacterWrapper wrapper = new CharacterWrapper(character);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                saveCharacter(wrapper.getCharacter());
            }
        });

        int userInput;
        MainMenu mainMenu = new MainMenu();
        StatsMenu statsMenu = new StatsMenu();
        LevelUp levelUpMenu = new LevelUp();
        Adventure adventure = new Adventure();

        while (true) {
            userInput = mainMenu.runMenu(character);
            System.out.println("user inputed the following input " + userInput);
            switch (userInput) {
                case 1 -> {
                    //Display Adventure Menu
                    character = adventure.runAdventure(character);
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

    public static Character intializeCharacter() {
        File File = new File(CHAR_FILE);
        System.err.println("Initalizing Character");

        if (File.exists()) {
            System.err.println("File Found!");
            try (FileInputStream fis = new FileInputStream(CHAR_FILE); ObjectInputStream ois = new ObjectInputStream(fis)) {

                return (Character) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Found file but could not reterieve data");
            }
        }
        String characterName;
        Checker checker = new Checker();

        do {
            characterName = JOptionPane.showInputDialog(null, "Enter Character Name!", "Character Creation", JOptionPane.DEFAULT_OPTION);
        } while (!checker.checkString(characterName));
        return new Character(characterName);
    }

    public static void saveCharacter(Character c) {
        try (FileOutputStream fos = new FileOutputStream(CHAR_FILE); ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
