package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.dto.ValCurs;
import org.example.dto.Valute;
import org.example.dto.util.DayOfWeekService;
import org.example.http.CB;
import org.example.http.CbrService;
import org.example.model.CurrencyExchange;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;
import org.example.Date;

public class DataBase {
    private List<CurrencyExchange> cur = new ArrayList<>();
    private String date = new Date().getDate();
    private String get;
    private static Connection co;
    private String in = "INSERT INTO CurrencyExchange (id, value, nominal, currency_name, currency_code, date) " +
                        "VALUES";

    private String del = "DELETE FROM CurrencyExchange";

    public static Connection getConnection() {
        if (co == null){
            try {
                co = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Alexey\\Uniorcod\\SQLiteStudio\\Valute.db");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return co;
    }

    public void insert(){
        ValCurs exchange = CB.newClient().getExchange(date);
        for(Valute value: exchange.getValuteList()) {
            String s = in + value.toString();

            try {
                Statement st = co.createStatement();
                st.executeUpdate(s);
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

    public

    public void getValue(){
        try {
            Statement st = co.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM Valute");
            while(result.next()){
                // Integer id, Double value, Integer nominal, String currencyName, String currencyCode, LocalDate date
                cur.add(new CurrencyExchange(result.getInt("id"), result.getDouble("value"), result.getInt("nominal"), result.getString("currency_name"), result.getString("currency_code"), new DayOfWeekService().getLastWeekdayDate(LocalDate.now())));
            }
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
