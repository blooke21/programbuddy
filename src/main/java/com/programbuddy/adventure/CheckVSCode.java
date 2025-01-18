package com.programbuddy.adventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import com.programbuddy.Checker;

public class CheckVSCode {

    Checker checker = new Checker();

    public boolean isVSCodeRunning() {
        ProcessBuilder processBuilder = new ProcessBuilder("ps", "-ax");

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("Visual Studio Code")) {
                    return true; // VSCode is running
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // VSCode is not running
    }

    public int initalizeVSCode() {

        String userInput;
        String msgBuilder;
        String errorMsg = "";
        int selectedChoice;

        if (isVSCodeRunning()) {
            return 1;
        }
        while (!isVSCodeRunning()) {
            msgBuilder = "Code editor not detected\n1. Try again\n2. Back out";
            //inform user about error and prompt to either go back or try again
            userInput = JOptionPane.showInputDialog(null, errorMsg + msgBuilder);
            errorMsg = "";
            if (!checker.checkInt(userInput)) {
                errorMsg = "\nWrong Input!!\n\n";
                continue;
            }
            selectedChoice = Integer.parseInt(userInput);
            if (selectedChoice == 2) {
                return 2;
            }
        }

        return 0;
    }

}
