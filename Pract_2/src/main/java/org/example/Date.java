package org.example;

import org.example.dto.util.DayOfWeekService;

import java.time.*;

public class Date {

    private LocalDate date = new DayOfWeekService().getLastWeekdayDate(LocalDate.now());

    public String getDate(){
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        StringBuilder writer = new StringBuilder();
        writer.append(day).append("/").append(month).append("/").append(year);
        return writer.toString();
    }
    public String getDat(){
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        StringBuilder writer = new StringBuilder();
        writer.append("\'").append(year).append("-").append(month).append("-").append(day).append("\'");
        return writer.toString();
    }
}
