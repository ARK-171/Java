package org.example.controler;

import org.example.dto.ValCurs;
import org.example.dto.Valute;
import org.example.dto.util.DayOfWeekService;
import org.example.http.CB;
import org.example.http.CbrService;
import org.example.model.CurrencyExchange;
import org.example.repository.CurrencyExchangeRepository;
import org.example.repository.impl.CurrencyExchangeRepositorySqliteImpl;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CBCurrencyController implements CurrencyExchangeController {
    private static CurrencyExchangeController instance;

    private final CurrencyExchangeRepository currencyExchangeRepository;

    private final Converter<CurrencyExchange, Valute> currencyExchangeValuteConverter;

    private final DayOfWeekService dayOfWeekService;

    private CBCurrencyController() {
        currencyExchangeRepository = CurrencyExchangeRepositorySqliteImpl.getInstance();
        currencyExchangeValuteConverter = new CurrencyExchangeValuteConverter();
        dayOfWeekService = new DayOfWeekService();
    }

    public static CurrencyExchangeController getInstance() {
        if (instance == null) {
            instance = new CBCurrencyController();
        }
        return instance;
    }


    @Override
    public List<CurrencyExchange> updateCurrency(LocalDate currencyExchangeDate) {
        Objects.requireNonNull(currencyExchangeDate);

        final List<CurrencyExchange> convertedValute = requestCurrencyExchangeFromServer(currencyExchangeDate);
        if (currencyExchangeRepository.existsWithDate(convertedValute.stream().findAny().orElseThrow().getDate())) {
            List<CurrencyExchange> allByDate = currencyExchangeRepository.findAllByDate(currencyExchangeDate);
            for (CurrencyExchange currencyExchangeInDatabase : allByDate) {
                final CurrencyExchange receivedExchange = convertedValute.stream()
                        .filter(cur -> cur.getCurrencyCode().equals(currencyExchangeInDatabase.getCurrencyCode()))
                        .findFirst()
                        .orElse(null);
                if (receivedExchange == null) {
                    continue;
                }
                currencyExchangeInDatabase.setValue(receivedExchange.getValue());
                currencyExchangeRepository.update(currencyExchangeInDatabase);
            }

        } else {
            for (CurrencyExchange currencyExchange : convertedValute) {
                currencyExchangeRepository.insert(currencyExchange);
            }
        }
        return currencyExchangeRepository.findAllByDate(currencyExchangeDate);
    }

    private List<CurrencyExchange> requestCurrencyExchangeFromServer(LocalDate currencyExchangeDate) {
        final CbrService centralBankOfRussianService = CB.newClient();
        final String centralBankOfRussiaDateFormatPattern = "dd/MM/yyyy";
        final String requestedCurrencyExchangeOfDate = DateTimeFormatter
                .ofPattern(centralBankOfRussiaDateFormatPattern)
                .format(currencyExchangeDate);
        final ValCurs valCursList;
        try {
            valCursList = centralBankOfRussianService.getExchange(requestedCurrencyExchangeOfDate);
        }catch (Exception e) {
            return Collections.emptyList();
        }
        final Date currencyExchangeResponseDate = valCursList.getDate();
        final List<CurrencyExchange> convertedValute = new ArrayList<>();
        for (Valute valute : valCursList.getValuteList()) {
            final CurrencyExchange currencyExchange = currencyExchangeValuteConverter.toDomain(valute);
            currencyExchange.setDate(currencyExchangeResponseDate.toInstant().atZone(ZoneOffset.UTC).toLocalDate());
            convertedValute.add(currencyExchange);
        }
        return convertedValute;
    }

    @Override
    public List<CurrencyExchange> getAllCurrencyExchanges() {
        return currencyExchangeRepository.findAll();
    }
}
