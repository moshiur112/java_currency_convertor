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

    /**
     * Create a mock CurrencySevice object before each test
     */
    @BeforeEach
    void setUp() {
        currencyServiceMock = mock(CurrencyService.class);
    }

    /**
     * Tests the functionality of the calculateExchange method when the source currency is Euro
     */
    @Test
    void calculateExchangeEURSource() {
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
    /**
     * Tests the functionality of the calculateExchange method when the target currency is Euro
     */
    @Test
    void calculateExchangeEURTarget() {
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
    /**
     * Tests the functionality of the calculateExchange method in general
     */
    @Test
    void calculateExchange() {
        CurrencyService cs = new CurrencyService();
        Map<String, Float> currency =  new HashMap<>();
        currency.put("HKD", 2.0f);
        currency.put("CAD", 3.0f);

        Exchange exchange = new Exchange("HKD", "CAD","20", "0");
        CurrencyData cd = new CurrencyData("Eur", currency);
        float EURSourceAnswer = cs.calculateExhange(exchange, cd);
        assertEquals(EURSourceAnswer, 30.f);
    }

    /**
     * Tests the functionality of the calculate method
     */
    @Test
    void calculate() {
        CurrencyService cs = new CurrencyService();
        float answer = cs.calculate(2.0f, 4.0f, 6.0f);
        assertEquals(answer, 12.0f);

    }


}