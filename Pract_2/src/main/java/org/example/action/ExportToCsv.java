package org.example.action;

import org.example.Main;
import org.example.export.ExportInterface;
import org.example.export.ToCsv;
import org.example.model.CurrencyExchange;

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

public class ExportToCsv extends AbstractAction {
    public static String DEFAULT_EXPORT_FILE_NAME = String.format("currency_%s.csv", LocalDate.now().format(DateTimeFormatter.ISO_DATE));

    private Component parent;
    private ExportInterface<CurrencyExchange> CsvExport;
    private CurrencyExchangeRepository currencyExchangeRepository;

    public ExportToCsv(Component parent) {
        super("Export to CSV");
        this.parent = parent;
        this.CsvExport = new ToCsv();
        this.currencyExchangeRepository = CurrencyExchangeRepositorySqliteImpl.getInstance();
        putValue(MNEMONIC_KEY, KeyEvent.VK_C);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser(Main.USER_HOME_PATH.toFile());
        fileChooser.setFileFilter();
        fileChooser.setSelectedFile(Main.USER_HOME_PATH.resolve(DEFAULT_EXPORT_FILE_NAME).toFile());
        File selectedFile = fileChooser.getSelectedFile();
        List<CurrencyExchange> allCurrencies = currencyExchangeRepository.findAll();
        String fileContent = CsvExport.export(allCurrencies);
        try {
            FileWriter writer = new FileWriter(selectedFile);
            writer.write(fileContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

