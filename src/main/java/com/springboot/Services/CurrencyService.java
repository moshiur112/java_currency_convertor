package com.springboot.Services;

import com.springboot.Models.CurrencyData;
import com.springboot.Models.Exchange;
import com.springboot.Resources.Configuration;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/** This class contains all the business logic for the application
 * @author Moshiur Rahman
 */
@Service
public class CurrencyService {

    @Autowired
    CacheManager cacheManager;

    Exchange exchange;
    CurrencyData currencyData;
    String baseCurrency;
    Map<String, Float> currencyRatesMap;


    /**
     * Processes all the data. This is the central control point of the CurrencyService class
     * @param exchange An Exchange object with the user inputs only
     * @return An Exchange object with the final exchanged values
     * @throws IOException
     */

    public Exchange prepare(Exchange exchange) throws IOException {
        baseCurrency = "EUR";
        currencyRatesMap = new HashMap<>();
        this.exchange = exchange;
        prepareCurrencyData();
        this.currencyData = new CurrencyData(baseCurrency, currencyRatesMap);
        exchange.value = String.valueOf(calculateExhange(exchange, currencyData));
        return this.exchange;

    }

    /**
     * Performs the currency exchange operations
     * @param exchange Exchange object with the user input data
     * @param currencyData All the data amount the different currencies
     * @return The exchange rate amount
     */
    public float calculateExhange(Exchange exchange, CurrencyData currencyData) {
        float ans = 0;
        if(exchange.source.equals("EUR")) {
            ans = Float.parseFloat(exchange.amount) * currencyData.currecy.get(exchange.target);
        } else if(exchange.target.equals("EUR")) {

            ans = Float.parseFloat(exchange.amount) / currencyData.currecy.get(exchange.source);
        } else {
            float source = currencyData.currecy.get(exchange.source);
            float target = currencyData.currecy.get(exchange.target);
            ans = calculateExchangeRate(source, target, Float.parseFloat(exchange.amount));
        }
        return ans;
    }

    /**
     * Calculates the exchange rate
     * @param source Source currency rate
     * @param target Target currency rate
     * @param amount Amount to be exchanged
     * @return The exchange rate amount
     */
    public float calculateExchangeRate(float source, float target, float amount) {
        float ans = amount * target / source;
        return ans;
    }

    /**
     * This method gathers all the exchange rates from the exchange rate
     * api and and puts them in a map to be used by other methods
     * @throws IOException
     */

    public void prepareCurrencyData() throws IOException {
        URL url = new URL(Configuration.baseURL);
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String rawJSONData = in.readLine();
        JSONObject jsonObject = new JSONObject(rawJSONData);
        JSONObject currencyRates = jsonObject.getJSONObject("rates");
        for(String currency: Configuration.currencies) {
            if(currency.equals("EUR")) {
                currencyRatesMap.put(currency, 1.0f );
            } else {
                currencyRatesMap.put(currency, currencyRates.getFloat(currency) );
            }
        }
        in.close();
    }

    /**
     * The method is called to get the final exchange rate.
     * The exchange rate is cached under the "exchange" name in the CacheManager
     * @param answer
     * @return The final exchange rate
     */
    @Cacheable("exchange")
    public Exchange getExchange(Exchange answer) {
//        Uncomment this to simulate a backed a call
/*        try
        {
            System.out.println("Going to sleep for 5 seconds to simulate backend call.");
            Thread.sleep(1000*5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }*/

        return this.exchange;
    }

    /**
     * This method clears the cache
     */
    public void evictAllCaches() {
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    /**
     * A scheduler that call the evictAllCaches method and clears the cache every minute
     */
    @Scheduled(fixedRate = 60000)
    public void evictAllcachesAtIntervals() {
        evictAllCaches();
    }

}
