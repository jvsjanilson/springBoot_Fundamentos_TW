package br.com.varela.erp.core.utils;

public class StringUtils {
    public static String formatPhone(String phone) {
        return phone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
    }

    public static String cleanPhone(String phone) {
        return phone.replaceAll("[^0-9]", "");
    }
}


