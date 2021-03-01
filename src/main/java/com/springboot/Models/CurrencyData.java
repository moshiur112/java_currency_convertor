package com.springboot.Models;

import java.util.Map;
import java.util.Objects;

/** This class stores all the currency data
 * @author Moshiur Rahman
 */

public class CurrencyData {
    public String base;
    public Map<String, Float> currecy;

    public CurrencyData(String base, Map<String, Float> currency) {
        this.base = base;
        this.currecy = currency;
    }

    /**
     * This method check whether two CurrencyData objects are identical
     * @param o another CurrencyData object
     * @return boolean of whether they are equal or not
     */
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

    /**
     * String representation of the the CurrencyData object
     */
    @Override
    public String toString() {
        return "CurrencyData{" +
                "base='" + base + '\'' +
                ", currecy=" + currecy.toString() +
                '}';
    }
}
