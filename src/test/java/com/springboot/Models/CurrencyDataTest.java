package com.springboot.Models;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
/** This class tests the CurrencyData class
 * @author Moshiur Rahman
 */
class CurrencyDataTest {
    /**
     * Tests the equals method for two identical CurrencyData objects
     */
    @Test
    void equalsSame() {
        String base = "base";
        String base2 = "base";

        Map<String, Float> currency = new HashMap<>();
        currency.put("input", 1.0f);
        currency.put("input2", 1.0f);

        Map<String, Float> currency2 = new HashMap<>();
        currency2.put("input", 1.0f);
        currency2.put("input2", 1.0f);

        CurrencyData cd =  new CurrencyData(base, currency);
        CurrencyData cd2 =  new CurrencyData(base2, currency2);

        assertEquals(cd, cd2);


    }
    /**
     * Tests the equals method for two different CurrencyData objects (the bases are different)
     */
    @Test
    void equalsDifferent() {
//      The base is different for the CurrencyData objects
        String base = "base";
        String base2 = "base2";

        Map<String, Float> currency = new HashMap<>();
        currency.put("input", 1.0f);
        currency.put("input2", 1.0f);

        Map<String, Float> currency2 = new HashMap<>();
        currency2.put("input", 1.0f);
        currency2.put("input2", 1.0f);

        CurrencyData cd =  new CurrencyData(base, currency);
        CurrencyData cd2 =  new CurrencyData(base2, currency2);

        assertNotEquals(cd, cd2);
    }
    /**
     * Tests the equals method for two different CurrencyData objects (the currency maps are different)
     */
    @Test
    void equalsDifferent2() {
        String base = "base";
        String base2 = "base";
//      The currency Hashmap is different for the CurrencyData objects
        Map<String, Float> currency = new HashMap<>();
        currency.put("input", 2.0f);
        currency.put("input2", 2.0f);

        Map<String, Float> currency2 = new HashMap<>();
        currency2.put("input", 1.0f);
        currency2.put("input2", 1.0f);

        CurrencyData cd =  new CurrencyData(base, currency);
        CurrencyData cd2 =  new CurrencyData(base2, currency2);

        assertNotEquals(cd, cd2);
    }
}