package com.programbuddy;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.programbuddy.adventure.AdventureJFrame;
import com.programbuddy.adventure.AdventureMenu;
import com.programbuddy.adventure.AdventureProgressBar;

public class Adventure {

    //checks if the user goes idle
    MouseChecker mouseChecker = new MouseChecker();
    KeyboardChecker keyboardChecker = new KeyboardChecker();
    AdventureMenu menu = new AdventureMenu();
    AdventureJFrame JFrame = new AdventureJFrame();
    AdventureProgressBar progressBar = new AdventureProgressBar();
    AdventureProgressBar health = new AdventureProgressBar();

    long startTime;
    long elapsedTime;
    int idleCounter;
    long run;
    boolean idleMouse;
    boolean idleKeyboard;

    @SuppressWarnings("CallToPrintStackTrace")
    public Character runAdventure(Character c) {
        run = menu.runMenu() * 60 * 30 * 1000;
        idleCounter = 0;
        elapsedTime = 0;
        idleMouse = false;
        idleKeyboard = false;
        progressBar.initalize(0, (int) run, 0, "HORIZONTAL", "Adventure Progress");
        health.initalize(0, c.getHealth(), c.getHealth(), "VERTICAL", "Character's Health");
        JFrame.initalize();
        JFrame.getContentPane().add(progressBar, BorderLayout.NORTH);
        JFrame.getContentPane().add(health, BorderLayout.WEST);
        //TODO rework this so AdventureFrame just handles all of this including the progress bars. they should not be there own class becuase they dont deserve to be their own class
        //https://www.youtube.com/watch?v=JEI-fcfnFkc

        JFrame.setVisible(true);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(keyboardChecker);
        startTime = System.currentTimeMillis();

        System.err.println("User selected: " + run);

        //adventure loop
        while (elapsedTime < run) {
            elapsedTime = (new Date()).getTime() - startTime;
            System.out.println(elapsedTime);

            idleMouse = this.mouseChecker.checkPointer();
            idleKeyboard = this.keyboardChecker.getIdleTracker();

            if (idleMouse && idleKeyboard) {
                idleCounter++;
                System.err.println("idle for " + idleCounter + " seconds");
            } else {
                idleCounter = 0;
            }

            if (idleCounter >= 5) {
                System.err.println("User has gone idle for five seconds");
                c.reduceHealth();
                health.updateBar(c.getHealth());
            }

            progressBar.updateBar(elapsedTime);

            //has loop sleep for 1 second
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return c;
    }
}
