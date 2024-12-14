package com.programbuddy;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.programbuddy.adventure.AdventureJFrame;
import com.programbuddy.adventure.AdventureMenu;
import com.programbuddy.adventure.AdventureProgressBar;
import com.programbuddy.adventure.DeathHandler;

public class Adventure {

    Adventure() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
    }

    //checks if the user goes idle
    MouseChecker mouseChecker = new MouseChecker();
    KeyboardChecker keyboardChecker = new KeyboardChecker();
    AdventureMenu menu = new AdventureMenu();
    AdventureJFrame AdventureFrame = new AdventureJFrame();
    AdventureProgressBar progressBar;
    AdventureProgressBar health;
    DeathHandler deathHandler = new DeathHandler();
    IdleMessage idleMessage = new IdleMessage();
    Character character;

    long startTime;
    long elapsedTime;
    int idleCounter;
    long run;
    int healthPool;
    boolean idleMouse;
    boolean idleKeyboard;

    @SuppressWarnings("CallToPrintStackTrace")
    public Character runAdventure(Character c) {
        deathHandler.dispose();
        idleMessage.dispose();
        progressBar = new AdventureProgressBar();
        health = new AdventureProgressBar();
        character = c;
        intialize();

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
                idleMessage.hideError();
            }

            if (idleCounter >= 5) {
                System.err.println("User has gone idle for five seconds");
                idleMessage.throwError();
                healthPool = character.takeDamage(5);
                health.updateBar(healthPool);
            }
            if (healthPool == 0) {
                exitAdventure();
                return character;
            }

            progressBar.updateBar(elapsedTime);

            //has loop sleep for 1 second
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return character;
    }

    public void intialize() {
        run = menu.runMenu();
        if (run == 7) {
            exitAdventure();
        }
        run = run * 60 * 30 * 1000;
        idleCounter = 0;
        elapsedTime = 0;
        healthPool = character.getHealth();
        idleMouse = false;
        idleKeyboard = false;
        progressBar.initalize(0, (int) run, 0, "HORIZONTAL", "Adventure Progress");
        health.initalize(0, character.getHealth(), character.getHealth(), "VERTICAL", character.getName() + "'s Health");
        AdventureFrame.initalize();
        AdventureFrame.getContentPane().add(progressBar, BorderLayout.NORTH);
        AdventureFrame.getContentPane().add(health, BorderLayout.WEST);
        //https://www.youtube.com/watch?v=JEI-fcfnFkc

        AdventureFrame.setVisible(true);

        GlobalScreen.addNativeKeyListener(keyboardChecker);
        startTime = System.currentTimeMillis();

        System.err.println("User selected: " + run);
    }

    public void exitAdventure() {
        deathHandler.intialize();
        AdventureFrame.dispose();
        character.fullHeal();
    }
}
