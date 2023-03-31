package com.eddoubled.cbrviewer.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
@Slf4j
public class DateUtils {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat NORMAL_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static Date parse(String date) throws ParseException {
        return FORMAT.parse(date);
    }

    public static Date parseNormal(String date) {
        try {
            return NORMAL_FORMAT.parse(date);
        } catch (ParseException pe) {
            log.error(pe.getMessage(), pe);
        }

        return null;
    }

    public static String format(Date date) {
        return FORMAT.format(date);
    }
}
