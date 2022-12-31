package org.example.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DataBase;
import org.example.model.CurrencyCsv;
import org.example.model.CurrencyExchange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class ExportToCsv extends AbstractAction {
    private String file = "export.csv";
//    private ObjectMapper mapper= new ObjectMapper();
//    private File f = new File("file");
    Path path = Paths.get(file);
    private CurrencyCsv l;
    private Component parent;
    private List<CurrencyExchange> list = DataBase.getInstance().getCur();

    public ExportToCsv(Component parent) {
        super("Export to CSV");
        this.parent = parent;
        list = DataBase.getInstance().getValue();
        putValue(MNEMONIC_KEY, KeyEvent.VK_C);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

//        l = new CurrencyCsv(s);
        try {
            String s = "id, value , nominal , name , code , date \n";
            BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
            for(int i =0; i<34; i++){
                s = s + list.get(i).getId().toString() + " , " + list.get(i).getValue().toString() + " , " + list.get(i).getNominal().toString() + " , " + list.get(i).getCurrencyName() + " , " + list.get(i).getCurrencyCode() + " , " + list.get(i).getDate().toString() + "\n";
                bw.write(s);
            }
            System.out.println(s);
//            mapper.writeValue(new File(file),l);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

