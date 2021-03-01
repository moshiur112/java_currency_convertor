package com.springboot.Resources;

import java.util.HashSet;
import java.util.Set;
/** This class holds all the configuration data
 * @author Moshiur Rahman
 */
public class Configuration {
    public static String baseURL = "https://api.exchangeratesapi.io/latest/";
    public static Set<String> currencies= new HashSet<String>(){{
        add("HRK");
        add("CHF");
        add("MXN");
        add("ZAR");
        add("INR");
        add("THB");
        add("CNY");
        add("AUD");
        add("ILS");
        add("KRW");
        add("JPY");
        add("PLN");
        add("GBP");
        add("IDR");
        add("HUF");
        add("PHP");
        add("TRY");
        add("RUB");
        add("HKD");
        add("ISK");
        add("DKK");
        add("CAD");
        add("USD");
        add("MYR");
        add("BGN");
        add("NOK");
        add("RON");
        add("SGD");
        add("CZK");
        add("SEK");
        add("NZD");
        add("BRL");
        add("EUR");
    }};

}


