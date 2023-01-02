package org.example.http;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;


public class CB {

    private CB() {
        throw new IllegalStateException();
    }

    public static CbrService newClient() {

        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint("https://www.cbr.ru/")
                .setConverter(new JacksonConverter(new XmlMapper()))
                .build();


        return retrofit.create(CbrService.class);
    }
}
