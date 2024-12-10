package com.programbuddy;

import java.util.HashMap;

public abstract class Character {

    protected String name;
    protected int health;
    protected int lvl;
    protected int exp;
    protected int expToNextLvl;
    protected int pendingLvl;
    protected boolean avalLvl;
    protected int timeDied;
    HashMap<String, Integer> charStats = new HashMap<>();
    HashMap<String, Integer> runStats = new HashMap<>();

    public Character(String name) {
        this.name = name;
        this.health = 400;
        //DOTO make this so the levelUp switch can't have errors
        this.charStats.put("str", 0);
        this.charStats.put("dex", 0);
        this.charStats.put("con", 0);
        this.lvl = 1;
        this.exp = 0;
        this.expToNextLvl = 20;
        this.pendingLvl = 0;
        this.timeDied = 0;
        this.runStats.put("runOne", 0);
        this.runStats.put("runTwo", 0);
        this.runStats.put("runThree", 0);
        this.runStats.put("runFour", 0);
        this.runStats.put("runFive", 0);
        this.runStats.put("runSix", 0);
        this.avalLvl = false;
    }

    public int takeDamage(int dmg) {
        if (dmg >= health) {
            timeDied += 1;
            return 99999;
        }
        health = health - dmg;
        return health;
    }

    public void finishRun(int time, String runType) {
        gainExp(time);
        runStats.replace(runType, (runStats.get(runType) + 1));
    }

    public void gainExp(int toGain) {
        exp += toGain;
        while (exp >= expToNextLvl) {
            avalLvl = true;
            exp = exp - expToNextLvl;
            pendingLvl += 1;
            expToNextLvl += 10;
        }
    }

    public void levelUpStat(String stat) {
        charStats.replace(stat, (charStats.get(stat) + 1));
        if (stat.equals("con")) {
            health = health + (charStats.get(stat) * 10);
        }
        toggleAvalLvl();
        reducePendingLvl();
    }

    private void toggleAvalLvl() {
        this.avalLvl = !avalLvl;
    }

    private void reducePendingLvl() {
        if (pendingLvl > 0) {
            this.pendingLvl = pendingLvl - 1;
        } else {
            throw new Error("What happen? How did you get to this point");
        }
    }

    //getters
    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getCharStats() {
        return charStats;
    }

    public int getHealth() {
        return health;
    }

    public int getLvl() {
        return lvl;
    }

    public int getExp() {
        return exp;
    }

    public int getExpToNextLvl() {
        return expToNextLvl;
    }

    public int getPendingLvl() {
        return pendingLvl;
    }

    public boolean getAvalLvl() {
        return avalLvl;
    }

    public int getTimeDied() {
        return timeDied;
    }

    public HashMap<String, Integer> getRunStats() {
        return runStats;
    }
}
