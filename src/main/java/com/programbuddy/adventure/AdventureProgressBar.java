package com.programbuddy.adventure;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class AdventureProgressBar extends JPanel {
    //Jpanel with progress bar

    JProgressBar pbar;

    public void initalize(int min_value, int max_value, int intital_value, String orientation, String msg) {
        if (orientation.equals("HORIZONTAL")) {
            pbar = new JProgressBar(JProgressBar.HORIZONTAL);
        } else {
            pbar = new JProgressBar(JProgressBar.VERTICAL);

        }
        pbar.setMinimum(min_value);
        pbar.setMaximum(max_value);
        pbar.setValue(intital_value);
        pbar.setStringPainted(true);
        pbar.setString(msg);
        pbar.setBounds(50, 50, 300, 50);
        add(pbar);
    }

    public void updateBar(long elapsedTime) {
        pbar.setValue((int) elapsedTime);
    }
}
