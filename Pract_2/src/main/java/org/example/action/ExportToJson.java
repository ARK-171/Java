package org.example.action;

import org.example.Main;
import org.example.export.ExportInterface;
import org.example.export.ToJson;
import org.example.model.CurrencyExchange;
import org.example.util.JsonFileFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExportToJson extends AbstractAction {
    public static String DEFAULT_EXPORT_FILE_NAME = String.format("export_%s.json", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
    private  Component parent;
    private final ExportInterface<CurrencyExchange> jsonExport;
    private final CurrencyExchangeRepository currencyExchangeRepository;

    public ExportToJson(Component parent) {
        super("Export to JSON");
        this.parent = parent;
        this.jsonExport = new ToJson();
        this.currencyExchangeRepository = CurrencyExchangeRepositorySqliteImpl.getInstance();
        putValue(MNEMONIC_KEY, KeyEvent.VK_J);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser(Main.USER_HOME_PATH.toFile());
        fileChooser.setSelectedFile(Main.USER_HOME_PATH.resolve(DEFAULT_EXPORT_FILE_NAME).toFile());
        fileChooser.setFileFilter(new JsonFileFilter());
        File selectedFile = fileChooser.getSelectedFile();
        List<CurrencyExchange> allCurrencies = currencyExchangeRepository.findAll();
        String fileContent = jsonExport.export(allCurrencies);

        try {
            FileWriter writer = new FileWriter(selectedFile);
            writer.write(fileContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
