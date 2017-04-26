package br.heitor.getninja.utils;

public class Utils {
    public static boolean isEmptyOrNull(CharSequence text) {
        return text == null || text.length() == 0;
    }
}
