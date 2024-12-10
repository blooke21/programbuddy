package com.programbuddy;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Adventure {

    long startTime;
    long elapsedTime;
    MouseChecker mouseChecker = new MouseChecker();

    boolean idleMouse = false;
    boolean idleKeyboard = false;
    int idleCounter = 0;

    public Character runAdventure(Character c, int run) {
        startTime = System.currentTimeMillis();
        elapsedTime = 0;

        System.err.println("User selected: " + run * 30 * 1000);

        while (elapsedTime < run * 30 * 10000) {
            elapsedTime = (new Date()).getTime() - startTime;
            System.out.println(elapsedTime);
            //Adventure loop loops every sec

            idleMouse = this.mouseChecker.checkPointer();

            if (idleMouse || idleKeyboard) {
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
