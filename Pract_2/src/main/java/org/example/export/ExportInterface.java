package org.example.export;

import java.util.List;

public interface ExportInterface<T> {
    String export(List<T> currencyExchange);
}
