package com.yeyeck.yeblog.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class KeyUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String KEY_ARTICLE_VIEWS = "ARTICLE-%s-%d";
    private static final String KEY_ARTICLE_EDITING = "ARTICLE-EDITING";


    private KeyUtils () {}

    public static String getKeyOfArticleViews(Integer id) {
        return String.format(KEY_ARTICLE_VIEWS, today(), id);
    }

    public static String today() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    public static String yesterday() { return LocalDate.now().plusDays(-1).format(DATE_FORMATTER); }

    public static String keyOfEmailCode(String keyFormat, String email) {
        return String.format(keyFormat, email);

    }

    public static String keyOfArticleInEditing() {
        return KEY_ARTICLE_EDITING;
    }



}
