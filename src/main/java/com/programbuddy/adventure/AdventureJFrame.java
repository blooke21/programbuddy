package com.programbuddy.adventure;

import javax.swing.JFrame;

public class AdventureJFrame extends JFrame {

    public void initalize() {
        setTitle("Adventure!");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    //TODO add msging about dmg taken
}
