package org.example.action;

import org.example.DataBase;
import org.example.model.CurrencyExchange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class ExportToCsv extends AbstractAction {
    private String file = "export.csv";
    Path path = Paths.get(file);
    private Component parent;
    private List<CurrencyExchange> list;

    public ExportToCsv(Component parent) {
        super("Export to CSV");
        this.parent = parent;
                list = DataBase.getInstance().getCur();
        putValue(MNEMONIC_KEY, KeyEvent.VK_C);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String s = "id, value , nominal , name , code , date \n";
        for(int i =0; i<34; i++){
            s = s + list.get(i).getId().toString() + " , " + list.get(i).getValue().toString() + " , " + list.get(i).getNominal().toString() + " , " + list.get(i).getCurrencyName() + " , " + list.get(i).getCurrencyCode() + " , " + list.get(i).getDate().toString() + " \n ";
        }
        try (FileWriter writer = new FileWriter(path.toFile())){
            writer.write(s);
            /*
            BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
            for(int i =0; i<34; i++){
                s = s + list.get(i).getId().toString() + " , " + list.get(i).getValue().toString() + " , " + list.get(i).getNominal().toString() + " , " + list.get(i).getCurrencyName() + " , " + list.get(i).getCurrencyCode() + " , " + list.get(i).getDate().toString() + " \n ";
                bw.write(s);
            }
*/
            System.out.println(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

