package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;

import org.example.action.ExportToCsv;
import org.example.action.ExportToJson;
import org.example.action.Exit;
import org.example.action.About;
import org.example.controler.CBCurrencyController;
import org.example.controler.CurrencyExchangeController;
import org.example.model.CurrencyExchange;
import org.example.model.CurrencyExchangeTableModel;

public class GUI extends JFrame
{
    private JPanel Panel;
    private JTable Table;
    private JMenuBar MenuBar;
    private JMenu file;
    private JMenuItem Csv;
    private JMenuItem Json;
    private JMenuItem exit;
    private JMenu help;
    private JMenuItem about;
    private JButton update;


//    private CurrencyExchangeController currencyExchangeController;

    public GUI(String title) {
        super(title);

//        currencyExchangeController = CBCurrencyController.getInstance();

        Panel = new JPanel(new BorderLayout());

        MenuBar = new JMenuBar();
        file = new JMenu("File");
        MenuBar.add(file);
        Csv = new JMenuItem("Export to CSV");
        Csv.setAction(new ExportToCsv(this));
        file.add(Csv);
        Json = new JMenuItem("Export to JSON");
        Json.setAction(new ExportToJson(this));
        file.add(Json);
        exit = new JMenuItem("Exit");
        exit.setAction(new Exit());
        file.addSeparator();
        file.add(exit);

        help = new JMenu("Help");
        MenuBar.add(help);
        about = new JMenuItem("About");
        about.setAction(new About(this));
        help.add(about);

        Table = new JTable();
        Table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(Table);
        Panel.add(scrollPane, BorderLayout.CENTER);

        update = new JButton("Update");
        update.addActionListener(this::onUpdateButtonClick);
        JPanel updatePanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(updatePanel, BoxLayout.LINE_AXIS);
        updatePanel.setLayout(boxLayout);
        updatePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 10000));
        updatePanel.add(Box.createHorizontalGlue());
        updatePanel.add(update);
        updateTableContent();
        Panel.add(updatePanel, BorderLayout.NORTH);

        this.setJMenuBar(MenuBar);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(Panel);
        this.pack();
        this.setSize(500, 700);
    }

    public void onUpdateButtonClick(ActionEvent e) {
        currencyExchangeController.updateCurrency(LocalDate.now());
        updateTableContent();
    }

    public void updateTableContent() {
        List<CurrencyExchange> allCurrencyExchanges = currencyExchangeController.getAllCurrencyExchanges();
        Table.setModel(new CurrencyExchangeTableModel(allCurrencyExchanges));
    }
}
