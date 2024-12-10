package com.programbuddy;

import javax.swing.JOptionPane;

public class AdventureMenu extends AbstractMenu {
    //drives adeventure

    CheckVSCode vsCheck = new CheckVSCode();
    Adventure adventure = new Adventure();

    public Character runMenu(Character c) {
        while (true) {

            if (vsCheck.isVSCodeRunning()) {
                msgBuilder = "";
            } else {
                msgBuilder = "vsCode is required to be open and it has not been detected, enter 0 to recheck\n";
            }
            msgBuilder += """
            Time to set out on an ADVENTURE! Select how long you want to be out on your adventure. (how long do you plan on programming)\n
            But BEWARE!! You must stay vigaliant of DANAGER! (going idle from the computer will result in your character taking damage!)\n\n
            """;

            msgBuilder += """
             Select Run\n
             1. Half an Hour (30 exp)\n
             2. 1 Hour (70 exp)\n
             3. 1 1/2 Hours (120 exp)\n
             4. 2 Hours (180 exp)\n
             5. 2 1/2 Hours (250 exp)\n
             6. 3 Hours (330 exp)
            """;

            userInput = JOptionPane.showInputDialog(null, errorString + msgBuilder + "\nEnter 7. to go back");
            if (!checker.checkInt(userInput)) {
                errorString = "Wrong Input!!\n";
                continue;
            }
            selectedChoice = Integer.parseInt(userInput);
            if (selectedChoice < 0 || selectedChoice > 7) {
                errorString = "Wrong Input!!\n";
                continue;
            }

            c = adventure.runAdventure(c, selectedChoice);

            if (selectedChoice == 7) {
                return c;
            }
        }
    }
}