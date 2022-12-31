package org.example;


import java.io.IOException;

import com.formdev.flatlaf.FlatIntelliJLaf;


import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Main {
    private static String USER_HOME = System.getProperty("user.home");
    public static Path USER_HOME_PATH = Paths.get(USER_HOME != null ? USER_HOME : "./");
    private static DataBase db = DataBase.getInstance();

    public static void main(String[] args) {
        onStart();
        JFrame win = new GUI("Практика 2");
        win.setVisible(true);
    }

    public static void onStart() {
        db.getConnection();
        db.delete();
        db.insert();
        if (!Files.exists(Path.of(db.db_Path))) {
            try {
                Files.createDirectory(Path.of(db.db_Path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
        }
    }

}
