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

@Service
public class CurrencyService {

    @Autowired
    CacheManager cacheManager;

    Exchange exchange;
    CurrencyData currencyData;
    String baseCurrency;
    Map<String, Float> currencyRatesMap;





    public float calculateExhange(Exchange exchange, CurrencyData currencyData) {
        float ans = 0;
        if(exchange.source.equals("EUR")) {
            ans = Float.parseFloat(exchange.amount) * currencyData.currecy.get(exchange.target);
        } else if(exchange.target.equals("EUR")) {

            ans = Float.parseFloat(exchange.amount) / currencyData.currecy.get(exchange.source);
        } else {
            float source = currencyData.currecy.get(exchange.source);
            float target = currencyData.currecy.get(exchange.target);
            ans = calculate(source, target, Float.parseFloat(exchange.amount));
        }
     return ans;
    }

    public Exchange prepare(Exchange exchange) throws IOException {



        baseCurrency = "EUR";
        currencyRatesMap = new HashMap<>();
        this.exchange = exchange;
        prepareCurrencyData();
        this.currencyData = new CurrencyData(baseCurrency, currencyRatesMap);
        exchange.value = String.valueOf(calculateExhange(exchange, currencyData));
        return this.exchange;

    }

    public float calculate(float source, float target, float amount) {
        float ans = amount * target / source;
        return ans;
    }

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


    public void evictAllCaches() {
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
    @Scheduled(fixedRate = 20000)
    public void evictAllcachesAtIntervals() {
        evictAllCaches();
    }

}
