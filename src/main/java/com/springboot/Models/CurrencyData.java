package com.springboot.Models;

import java.util.Map;

public class CurrencyData {
    public String base;
    public Map<String, Float> currecy;

    public CurrencyData(String base, Map<String, Float> currency) {
        this.base = base;
        this.currecy = currency;
    }

}
