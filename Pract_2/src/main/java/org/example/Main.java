package org.example;


import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;


public class Main {
    private static DataBase db = DataBase.getInstance();

    public static void main(String[] args) {
        onStart();
        JFrame win = new GUI("Практика 2");
        win.setVisible(true);
    }

    public static void onStart() {
        db.getConnection();
        db.insert();
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
        }
    }

}
