package org.example.controler;

import org.example.model.CurrencyExchange;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyExchangeController {
    List<CurrencyExchange> updateCurrency(LocalDate currencyExchangeDate);

    List<CurrencyExchange> getAllCurrencyExchanges();

}
