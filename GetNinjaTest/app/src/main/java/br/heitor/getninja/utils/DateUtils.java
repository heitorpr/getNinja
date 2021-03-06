package br.heitor.getninja.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat df_api;
    private static SimpleDateFormat df_api_short;

    @SuppressLint("SimpleDateFormat")
    public static Date parseDateObject(Object value) {
        if (value == null) return null;

        if (df_api == null || df_api_short == null) {
            reloadDateOptions();
        }

        Date parsedDate = null;

        try {
            if (value.getClass().equals(long.class) || value.getClass().equals(Long.class)) {
                parsedDate = new Date();
                parsedDate.setTime(Long.parseLong(String.valueOf(value)));
                return parsedDate;

            }

            if (value.getClass().equals(Date.class)) {
                parsedDate = (Date) value;
                return parsedDate;

            }

            if (value.getClass().equals(String.class)) {
                parsedDate = df_api.parse((String) value);
                return parsedDate;
            }

        } catch (ParseException parseDateException) {
            ErrorHandler.logError(parseDateException);

            try {
                parsedDate = new Date();
                parsedDate = df_api_short.parse((String) value);
            } catch (ParseException parseShortException) {
                ErrorHandler.logError(parseShortException);
            }
        }

        return parsedDate;
    }

    @SuppressLint("SimpleDateFormat")
    private static void reloadDateOptions() {
        df_api = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df_api_short = new SimpleDateFormat("yyyy-MM-dd");
    }

    public static String format(Date date) {
        if (date == null) return "";
        return DateFormat.getDateInstance().format(date);
    }
}
