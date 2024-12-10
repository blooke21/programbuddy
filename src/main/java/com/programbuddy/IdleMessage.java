package com.programbuddy;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class IdleMessage extends JDialog {

    private JPanel myJPanel = null;
    private final String title = "IDLE ERROR";
    private final String message = "\nUSER HAS GONE IDLE";
    Border border = BorderFactory.createTitledBorder(title);

    public IdleMessage() {
        super();
    }

    public void throwError() {
        myJPanel = new JPanel();
        getContentPane().add(myJPanel);
        myJPanel.add(new JLabel(this.message));
        //since jpanel doesn't have a set title you have to build a custom border, weird ik.ik..
        myJPanel.setBorder(border);
        setPreferredSize(new Dimension(300, 100));
        pack();

        setLocation(100, 100);
        setVisible(true);
    }

}
