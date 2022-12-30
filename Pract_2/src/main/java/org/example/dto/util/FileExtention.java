package org.example.dto.util;

import java.io.File;
import java.util.Objects;

public class FileExtention {
    private FileExtention() {
    }

    public static String getExtention(File file) {

        String ex;
        final int i = file.getName().lastIndexOf('.');
        if (i > 0 && i < file.getName().length() - 1) {
            ex = file.getName().substring(i + 1).toLowerCase();
        } else {
            ex = "";
        }
        return ex;
    }
}