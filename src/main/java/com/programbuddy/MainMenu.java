package com.programbuddy;

import javax.swing.JOptionPane;

public class MainMenu extends AbstractMenu {

    public int runMenu(Character c) {
        while (true) {
            msgBuilder = "1. Go on an Adventure\n2. View Stats";
            if (c.getAvalLvl()) {
                msgBuilder = msgBuilder + "\n3. Level Up!";
            } else {
                msgBuilder = msgBuilder + "\n3. Level Up!(Unavaliable)";
            }
            msgBuilder = msgBuilder + "\n4. Exit";

            userInput = JOptionPane.showInputDialog(null, errorString + "\n" + msgBuilder);
            errorString = "";

            if (!checker.checkInt(userInput)) {
                msgBuilder = "\nWrong Input!!\n\n" + msgBuilder;
                continue;
            }
            selectedChoice = Integer.parseInt(userInput);

            if (selectedChoice == 777) {
                return 777;
            }

            if (selectedChoice > 4 || selectedChoice < 0) {
                msgBuilder = "\nWrong Input!!\n\n" + msgBuilder;
                continue;
            }
            if (selectedChoice == 3 && !c.getAvalLvl()) {
                errorString = "Level up not avaliable!\n";
                continue;
            }
            return selectedChoice;
        }
    }

}
