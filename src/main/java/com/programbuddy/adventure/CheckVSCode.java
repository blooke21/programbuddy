package com.programbuddy.adventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckVSCode {

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
}
