package com.programbuddy.adventure;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class DeathHandler extends JDialog {

    private JPanel myJPanel = null;
    private final String title = "YOU HAVE DIED";
    private final String message = """
            <html>
            F's in the chat<br>
            You died from going Idle too long?<br>
            Did you get distracted or are you just LAZY!!!<br>
            Get back in there and try again<br>
            <p></p>
            <html>
            """;

    Border border = BorderFactory.createTitledBorder(title);

    public DeathHandler() {
        super();
    }

    public void intialize() {
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
