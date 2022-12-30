package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.dto.ValCurs;
import org.example.dto.Valute;
import org.example.http.CbrService;
import org.example.Date;
import org.example.model.CurrencyExchange;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static String USER_HOME = System.getProperty("user.home");
    public static Path USER_HOME_PATH = Paths.get(USER_HOME != null ? USER_HOME : "./");

    public static void main(String[] args) throws IOException {

    }
}
