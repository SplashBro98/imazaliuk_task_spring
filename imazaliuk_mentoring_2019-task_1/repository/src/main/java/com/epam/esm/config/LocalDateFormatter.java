package com.epam.esm.config;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * class which helps spring convert LocalDate to json and from json
 */
public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String s, Locale locale) throws ParseException {
        return LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String print(LocalDate date, Locale locale) {
        return DateTimeFormatter.ISO_DATE.format(date);
    }
}
