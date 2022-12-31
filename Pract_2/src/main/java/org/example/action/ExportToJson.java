package org.example.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DataBase;
import org.example.model.CurrencyExport;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.File;
import java.util.List;

public class ExportToJson extends AbstractAction {
    private ObjectMapper mapper= new ObjectMapper();
    private List<CurrencyExport> l = DataBase.getInstance().l;
    private final Component parent;
    private String file = "export.json";


    public ExportToJson(Component parent) {
        super("Export to JSON");
        this.parent = parent;
        putValue(MNEMONIC_KEY, KeyEvent.VK_J);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            mapper.writeValue(new File(file),l);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
