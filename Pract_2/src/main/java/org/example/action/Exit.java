package org.example.action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class Exit extends AbstractAction {

    public Exit() {
        super("Exit");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window w = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
        if (w != null) {
            WindowEvent closing = new WindowEvent(w, WindowEvent.WINDOW_CLOSING);
            w.dispatchEvent(closing);
        }
    }
}
