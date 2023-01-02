package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.example.dto.ValCurs;
import org.example.dto.Valute;
import org.example.dto.util.DayOfWeekService;
import org.example.http.CB;
import org.example.http.CbrService;
import org.example.model.CurrencyExchange;
import org.example.model.CurrencyExport;

public class DataBase {
    private List<CurrencyExchange> cur = new ArrayList<>();
    private static DataBase instance;
    private String date = new Date().getDate();
    public List<CurrencyExport> l = new ArrayList<>();
    public static String db_Path = "C:\\Users\\Alexey\\Uniorcod\\SQLiteStudio\\Valute.db";
    private static String url = "jdbc:sqlite:" + db_Path;
    private static Connection co;
    private String in = "INSERT INTO CurrencyExchange (id, value, nominal, currency_name, currency_code, date) " +
                        "VALUES ";

    private String del = "DELETE FROM CurrencyExchange ";

    public Connection getConnection() {
        if (co == null){
            try {
                co = DriverManager.getConnection(url);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return co;
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public void insert(){
        ValCurs exchange = CB.newClient().getExchange(date);
        for(Valute value: exchange.getValuteList()) {
            String s = in + value.toString();
            System.out.println(s);
            try {
                Statement st = co.createStatement();
                st.execute(s);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void delete(){
        try {
            Statement st = co.createStatement();
            st.executeUpdate(del);
            cur.clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CurrencyExchange> getCur(){
        return cur;
    }
    public List<CurrencyExchange> getValue(){
        try {
            if(cur.isEmpty()) {
                Statement st = co.createStatement();
                ResultSet result = st.executeQuery("SELECT * FROM CurrencyExchange ");
                while (result.next()) {
                    // Integer id, Double value, Integer nominal, String currencyName, String currencyCode, LocalDate date
                    cur.add(new CurrencyExchange(result.getInt("id"), result.getDouble("value"), result.getInt("nominal"), result.getString("currency_name"), result.getString("currency_code"), new DayOfWeekService().getLastWeekdayDate(LocalDate.now())));
                    l.add(new CurrencyExport(result.getInt("id"), result.getDouble("value"), result.getInt("nominal"), result.getString("currency_name"), result.getString("currency_code"), (new DayOfWeekService().getLastWeekdayDate(LocalDate.now())).toString()));
                }
            }
            return cur;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void CloseConnection(){
        try {
            co.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
