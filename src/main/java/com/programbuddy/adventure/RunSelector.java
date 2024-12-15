package com.programbuddy.adventure;

import javax.swing.JOptionPane;

import com.programbuddy.AbstractMenu;
import com.programbuddy.Checker;

public class RunSelector extends AbstractMenu {

    CheckVSCode vsCheck = new CheckVSCode();
    Checker checker = new Checker();

    public int runMenu() {
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

            return selectedChoice;

        }
    }
}
