package com.springboot.Models;


import java.util.Objects;

public class Exchange  extends ReturnPage{
    public String source;
    public String target;
    public String amount;
    public String value;

    /** This class used to show the currency exchange output to the user
     * @author Moshiur Rahman
     */
    public Exchange(String source, String target, String amount, String value){
        super();
        this.source = source;
        this.target = target;
        this.amount = amount;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {


        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exchange exchange = (Exchange) o;
        return source.equals(exchange.source) &&
                target.equals(exchange.target) &&
                amount.equals(exchange.amount) &&
                value.equals(exchange.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target, amount, value);
    }

    @Override
    public String toString() {
        return "s: " + source + " t: " + target + " a: " + amount + " v: " + value;
    }

}
