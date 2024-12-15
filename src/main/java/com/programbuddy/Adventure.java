package com.programbuddy;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.programbuddy.adventure.AdventureJFrame;
import com.programbuddy.adventure.AdventureMsging;
import com.programbuddy.adventure.AdventureProgressBar;
import com.programbuddy.adventure.CheckVSCode;
import com.programbuddy.adventure.DeathHandler;
import com.programbuddy.adventure.FinishRunMsg;
import com.programbuddy.adventure.RunSelector;

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
    RunSelector menu = new RunSelector();
    AdventureJFrame AdventureFrame = new AdventureJFrame();
    AdventureMsging adventureMsging;
    AdventureProgressBar progressBar;
    AdventureProgressBar health;
    DeathHandler deathHandler = new DeathHandler();
    FinishRunMsg finishMsg = new FinishRunMsg();
    IdleMessage idleMessage = new IdleMessage();
    CheckVSCode checkVSCode = new CheckVSCode();
    Character character;

    long startTime;
    long elapsedTime;
    int tempIdleCounter;
    int run;
    int runTime;
    float dmgToTake;
    float healthPool;
    boolean idleMouse;
    boolean idleKeyboard;
    int timeIdle;
    float dmgTaken;
    int expGained;

    @SuppressWarnings("CallToPrintStackTrace")

    public Character runAdventure(Character c) {
        //create instances of JFrame components
        progressBar = new AdventureProgressBar();
        health = new AdventureProgressBar();
        adventureMsging = new AdventureMsging();

        run = menu.runMenu();
        System.err.println("User selected: " + run);
        if (run == 7) {
            System.err.println("User backed out of adventure");
            return c;
        }

        character = c;
        intialize();

        //adventure loop
        while (elapsedTime < runTime) {
            elapsedTime = (new Date()).getTime() - startTime;
            System.out.println(elapsedTime);

            idleMouse = this.mouseChecker.checkPointer();
            idleKeyboard = this.keyboardChecker.getIdleTracker();

            if (idleMouse && idleKeyboard) {
                tempIdleCounter++;
                System.err.println("idle for " + tempIdleCounter + " seconds");
            } else {
                tempIdleCounter = 0;
                idleMessage.hideError();
            }

            if (tempIdleCounter >= 5 || !checkVSCode.isVSCodeRunning()) {
                System.err.println("User has gone idle for five seconds");
                idleMessage.throwError();
                timeIdle += 1;
                healthPool = character.takeDamage(dmgToTake);
                dmgTaken += dmgToTake;
                //it's okay that health progress bar is not recieveing a float. It just handles the graphics does not need exact calcuations
                health.updateBar((long) healthPool);
                System.err.println("Charcter's health is now " + character.getHealth());
                adventureMsging.updateText(c.getName(), dmgTaken, timeIdle);
            }
            if (healthPool == 0) {
                deathHandler.intialize();
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
        expGained = ((30 * run) + (((run - 1) * 5) * run));
        finishMsg.intialize(run, expGained);
        exitAdventure();
        character.gainExp(expGained);
        return character;
    }

    private void intialize() {
        System.err.println("Adventure Initalized");
        deathHandler.dispose();
        finishMsg.dispose();
        idleMessage.dispose();
        runTime = (run * 60 * 30 * 1000) - ((15 * character.getSpecificStat("dex")) * 1000);
        System.err.println("Adventure RunTime = " + runTime + " milliseconds");
        tempIdleCounter = 0;
        elapsedTime = 0;
        timeIdle = 0;
        dmgTaken = 0;
        healthPool = character.getHealth();
        dmgToTake = 30 - (float) (.15 * character.getSpecificStat("str"));
        System.err.println("C will take " + dmgToTake + " on idle");
        idleMouse = false;
        idleKeyboard = false;
        adventureMsging.intialize();
        progressBar.initalize(0, runTime, 0, "HORIZONTAL", "Adventure Progress");
        health.initalize(0, (int) character.getHealth(), (int) character.getHealth(), "VERTICAL", character.getName() + "'s Health");
        AdventureFrame.initalize();
        AdventureFrame.getContentPane().add(progressBar, BorderLayout.NORTH);
        AdventureFrame.getContentPane().add(health, BorderLayout.WEST);
        AdventureFrame.getContentPane().add(adventureMsging, BorderLayout.CENTER);
        //https://www.youtube.com/watch?v=JEI-fcfnFkc

        AdventureFrame.setVisible(true);

        GlobalScreen.addNativeKeyListener(keyboardChecker);
        startTime = System.currentTimeMillis();
    }

    private void exitAdventure() {
        AdventureFrame.dispose();
        character.fullHeal();
    }
}
