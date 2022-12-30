package org.example.export;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.model.CurrencyExchange;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;

public class ToCsv implements ExportInterface<CurrencyExchange> {
    private static String COLUMN_ID = "id";
    private static String COLUMN_VALUE = "value";
    private static String COLUMN_NOMINAL = "nominal";
    private static String COLUMN_CURRENCY_NAME = "currency_name";
    private static String COLUMN_CURRENCY_CODE = "currency_code";
    private static String COLUMN_DATE = "date";

    private CSVFormat csvFormat;

    public ToCsv(CSVFormat csvFormat) {
        Objects.requireNonNull(csvFormat);

        this.csvFormat = csvFormat;
    }

    public ToCsv() {
        this(CSVFormat.DEFAULT);
    }

    private final List<String> header = List.of(COLUMN_ID, COLUMN_VALUE, COLUMN_NOMINAL, COLUMN_CURRENCY_NAME, COLUMN_CURRENCY_CODE, COLUMN_DATE);

    @Override
    public String export( List<CurrencyExchange> currencyExchange) {
//        Objects.requireNonNull(currencyExchange);

        StringWriter writer = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            printer.printRecord(header);
            for (CurrencyExchange cur : currencyExchange) {
                printer.printRecord(
                        cur.getId(),
                        cur.getValue(),
                        cur.getNominal(),
                        cur.getCurrencyName(),
                        cur.getCurrencyCode(),
                        cur.getDate()
                );
            }
        } catch (IOException e){
        }
        return writer.toString().replaceAll("\\r\\n?", "\n");
    }
}
