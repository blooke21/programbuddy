package com.programbuddy.adventure;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class FinishRunMsg extends JDialog {

    private JPanel myJPanel = null;
    private final String title = "YOU'VE COMPLETED YOUR ADVENTURE";
    private String message;
    Border border = BorderFactory.createTitledBorder(title);

    public FinishRunMsg() {
        super();
    }

    public void intialize(int run, int exp) {
        this.message = String.format("""
                <html>
                Congrats!! 
                You completed the %d minute run!
                You gained %d exp!
                </html>
                """, (run * 30), exp);
        myJPanel = new JPanel();
        getContentPane().add(myJPanel);
        myJPanel.add(new JLabel(this.message));
        //since jpanel doesn't have a set title you have to build a custom border, weird ik.ik..
        myJPanel.setBorder(border);
        setPreferredSize(new Dimension(600, 150));
        pack();

        setLocation(100, 100);
        setVisible(true);
    }
}
