package com.springboot.Models;

import java.util.Map;
import java.util.Objects;

public class CurrencyData {
    public String base;
    public Map<String, Float> currecy;

    public CurrencyData(String base, Map<String, Float> currency) {
        this.base = base;
        this.currecy = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyData that = (CurrencyData) o;
        return base.equals(that.base) &&
                currecy.equals(that.currecy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, currecy);
    }

    @Override
    public String toString() {
        return "CurrencyData{" +
                "base='" + base + '\'' +
                ", currecy=" + currecy.toString() +
                '}';
    }
}
