package com.programbuddy;

import java.util.Map;

import javax.swing.JOptionPane;

public class LevelUp extends AbstractMenu {

    int counter;

    public Character runMenu(Character c) {

        while (true) {
            counter = 0;

            msgBuilder = "Which skill do you want to level up";

            for (Map.Entry<String, Integer> en : c.getCharStats().entrySet()) {
                counter++;
                msgBuilder = msgBuilder + "\n" + counter + ". " + en.getKey() + " : " + en.getValue();
            }

            // userInput = JOptionPane.showInputDialog(null, msgBuilder);
            counter++;

            userInput = JOptionPane.showInputDialog(null, errorString + msgBuilder + "\nEnter " + counter + " to go back" + "\nAvaliable Level Ups (" + c.getPendingLvl() + ")");
            if (!checker.checkInt(userInput)) {
                errorString = "Wrong Input\n";
                continue;
            }
            selectedChoice = Integer.parseInt(userInput);
            if (selectedChoice < 0 || selectedChoice > counter) {
                errorString = "Wrong Input\n";
                continue;
            }
            System.err.println("correct!");

            switch (selectedChoice) {
                case 1 ->
                    c.levelUpStat("str");
                case 2 ->
                    c.levelUpStat("con");
                case 3 ->
                    c.levelUpStat("dex");
                case 4 -> {
                    return c;
                }

                default ->
                    throw new AssertionError();
            }

            if (c.getPendingLvl() <= 0) {
                return c;
            }

        }

    }

}
