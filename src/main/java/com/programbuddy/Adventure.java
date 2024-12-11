package com.programbuddy;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class Adventure {

    //checks if the user goes idle
    MouseChecker mouseChecker = new MouseChecker();
    KeyboardChecker keyboardChecker = new KeyboardChecker();
    AdventureMenu menu = new AdventureMenu();

    long startTime;
    long elapsedTime;
    int idleCounter;
    int run;
    boolean idleMouse;
    boolean idleKeyboard;

    public Character runAdventure(Character c) {
        run = menu.runMenu();
        idleCounter = 0;
        idleMouse = false;
        idleKeyboard = false;

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(keyboardChecker);
        startTime = System.currentTimeMillis();
        elapsedTime = 0;

        System.err.println("User selected: " + run * 30 * 1000);

        while (elapsedTime < run * 30 * 10000) {
            elapsedTime = (new Date()).getTime() - startTime;
            System.out.println(elapsedTime);
            //Adventure loop loops every sec

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
            }

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
