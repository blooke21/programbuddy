package com.programbuddy.adventure;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdventureMsging extends JPanel {

    JLabel lab1 = new JLabel("All good so far!!", JLabel.CENTER);

    public void intialize() {
        setLayout(new FlowLayout());
        add(this.lab1);
    }

    public void updateText(String name, int dmgTaken, long timeIdle) {
        lab1.setText("<html>" + name + " has taken " + dmgTaken + " damage! Be more careful!<br>This was because you've been idle for " + timeIdle + " seconds!<html>");
    }
}
