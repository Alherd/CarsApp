package com.example.alherd.carapp.utils;

public final class StringUtils {
    public static boolean foo (String line, String reg) {
        String[] strings = line.split("\\."); // делим строку на отдельные слова
        for (String word : strings) {
            if (word.matches(reg)) {  // проверяем в цикле каждое отдельное слово
                return true;
            }
        }
        return false;
    }
}
