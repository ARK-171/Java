package org.example.export;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.model.CurrencyExchange;
import org.jetbrains.annotations.NotNull;

public class ToJson implements ExportInterface<CurrencyExchange> {
    private ObjectMapper Mapper;

    public ToJson() {
        this.Mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setDateFormat(new StdDateFormat().withColonInTimeZone(false));
    }

    @Override
    public @NotNull String export(@NotNull List<CurrencyExchange> currencyExchange) {
        Objects.requireNonNull(currencyExchange);

        try {
            return Mapper.writerWithDefaultPrettyPrinter().writeValueAsString(currencyExchange);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}