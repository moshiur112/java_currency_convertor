package com.springboot.Services;

import com.springboot.Models.CurrencyData;
import com.springboot.Models.Exchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
/** This class tests the CurrencyService class
 * @author Moshiur Rahman
 */
class CurrencyServiceTest {
    CurrencyService currencyServiceMock;

    @BeforeEach
    void setUp() {
        currencyServiceMock = mock(CurrencyService.class);
    }


    @Test
    void calculateExhangeEURSource() {
        CurrencyService cs = new CurrencyService();
        Map<String, Float> currency =  new HashMap<>();
        currency.put("EUR", 1.0f);
        currency.put("HKD", 2.0f);
        currency.put("CAD", 3.0f);

        Exchange exchange = new Exchange("EUR", "HKD", "20", "0");
        CurrencyData cd = new CurrencyData("Eur", currency);
        float EURSourceAnswer = cs.calculateExhange(exchange, cd);
        assertEquals(EURSourceAnswer, 40.f);
    }
    @Test
    void calculateExhangeEURTarget() {
        CurrencyService cs = new CurrencyService();
        Map<String, Float> currency =  new HashMap<>();
        currency.put("EUR", 1.0f);
        currency.put("HKD", 2.0f);
        currency.put("CAD", 3.0f);

        Exchange exchange = new Exchange("HKD", "EUR","20", "0");
        CurrencyData cd = new CurrencyData("Eur", currency);
        float EURSourceAnswer = cs.calculateExhange(exchange, cd);
        assertEquals(EURSourceAnswer, 10.f);
    }
    @Test
    void calculateExhange() {
        CurrencyService cs = new CurrencyService();
        Map<String, Float> currency =  new HashMap<>();
        currency.put("HKD", 2.0f);
        currency.put("CAD", 3.0f);

        Exchange exchange = new Exchange("HKD", "CAD","20", "0");
        CurrencyData cd = new CurrencyData("Eur", currency);
        float EURSourceAnswer = cs.calculateExhange(exchange, cd);
        assertEquals(EURSourceAnswer, 30.f);
    }


    @Test
    void calculate() {
        CurrencyService cs = new CurrencyService();
        float answer = cs.calculate(2.0f, 4.0f, 6.0f);
        assertEquals(answer, 12.0f);

    }


}