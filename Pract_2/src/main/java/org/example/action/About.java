package org.example.action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class About extends AbstractAction {

    private final Component Panel;

    public About(Component Panel) {
        super("About");
        this.Panel = Panel;
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(Panel, """
                Кургин Алексей 
                БСБО-05-21
                """);
    }
}
