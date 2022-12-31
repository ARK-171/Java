package org.example;

import javax.swing.*;;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.action.ExportToCsv;
import org.example.action.ExportToJson;
import org.example.action.Exit;
import org.example.action.About;
import org.example.model.CurrencyExchange;

public class GUI extends JFrame
{
    private String[][] db_data = new String[34][6];
    private String[] columNames = {"id", "value", "nominal", "name", "code", "date"};
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

    private DataBase database;

    public GUI(String title) {
        super(title);

        database = DataBase.getInstance();

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

        Table = new JTable(db_data, columNames);
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
        Panel.add(updatePanel, BorderLayout.NORTH);

        this.setJMenuBar(MenuBar);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(Panel);
        this.pack();
        this.setSize(500, 700);
    }

    public void onUpdateButtonClick(ActionEvent e) {
        updateTableContent();
    }

    public void updateTableContent(){
        database.getConnection();
        database.delete();
        database.insert();
        database.getValue();
        Data();
        Table.updateUI();
    }

    public void Data(){
        List<CurrencyExchange> c = database.getCur();
        for (int i = 0; i < 34; i++){
            db_data[i][0] = c.get(i).getId().toString();
            db_data[i][1] = c.get(i).getValue().toString();
            db_data[i][2] = c.get(i).getNominal().toString();
            db_data[i][3] = c.get(i).getCurrencyName();
            db_data[i][4] = c.get(i).getCurrencyCode();
            db_data[i][5] = c.get(i).getDate().toString();
        }
    }

    public void json(ActionEvent e){
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < 34; i++){
            try {
                mapper.writeValueAsString(database.getCur().get(i));
            } catch (JsonProcessingException a) {
                throw new RuntimeException(a);
            }
        }

    }
}
