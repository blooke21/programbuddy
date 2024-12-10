package com.programbuddy;

import java.util.Map;
import javax.swing.JOptionPane;

public class StatsMenu extends AbstractMenu {
//TODO add exp to next level to msg

    public Character runMenu(Character c) {
        msgBuilder = "Here is " + c.getName() + "'s stats!\nLevel: " + c.getLvl() + "\nMax Health: " + c.getHealth();
        System.err.println(c.getCharStats());
        for (Map.Entry<String, Integer> en : c.getCharStats().entrySet()) {
            msgBuilder = msgBuilder + "\n" + en.getKey() + ": " + en.getValue();
        }
        msgBuilder += """
                \n
                Strength(str) reduces damage taken
                Constitution(con) increases health
                Dexterity(dex) decrease amount of time needed to clear an adventure
                """;

        while (true) {
            userInput = JOptionPane.showInputDialog(null, errorString + msgBuilder + "\nEnter 4 to go back");
            if (!checker.checkInt(userInput)) {
                errorString = "Wrong Input\n";
                continue;
            }
            selectedChoice = Integer.parseInt(userInput);
            if (selectedChoice != 4) {
                errorString = "Wrong Input\n";
                continue;
            }
            return c;

        }

    }
}
