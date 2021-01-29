package com.epsi.egostyleapp;

public class Utils {
    private static String CONNECTION_STRING = "http://192.168.56.1/android_connect/api_all_coupons.php";

    public static String getConnectionString() {
        return CONNECTION_STRING;
    }

    public static String getConnectionStringPhrase() {
        return CONNECTION_STRING + "?phrase=";
    }
}
