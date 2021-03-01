# java_currency_convertor

This project uses Java 8

## Running locally
To run the application locally:
1. Go to Application page and start the main method
2. Go to http://localhost:8080/ and enter the query
 parameters the following way:
 
http://localhost:8080/?source=<source_currency>&target=<target_currency>&amount=<amount\>  

for example: http://localhost:8080/?source=EUR&target=HKD&amount=7
3. Ensure that the source and the target currencies are selected from the following list
[HRK, CHF, MXN, ZAR, INR, THB, CNY, AUD, ILS,
 KRW, JPY, PLN, GBP, IDR, HUF, PHP, TRY, RUB,
 HKD, ISK, EUR, DKK, CAD, USD, MYR, BGN, NOK,
  RON, SGD, CZK, SEK, NZD, BRL]
4. Ensure that the amount is a valid number








## Heroku


The application has been deployed to heroku
https://java-currency-convertor.herokuapp.com/

To make requests in Heroku, use this format
https://java-currency-convertor.herokuapp.com/?source=<source_currency>&target=<target_currency>&amount=<amount\> 

for example: https://java-currency-convertor.herokuapp.com/?source=HKD&target=EUR&amount=40

3. Ensure that the source and the target currencies are selected from the following list
[HRK, CHF, MXN, ZAR, INR, THB, CNY, AUD, ILS,
 KRW, JPY, PLN, GBP, IDR, HUF, PHP, TRY, RUB,
 HKD, ISK, EUR, DKK, CAD, USD, MYR, BGN, NOK,
  RON, SGD, CZK, SEK, NZD, BRL]
4. Ensure that the amount is a valid number



## Tests
To run the tests, use the command "mvn test" or "mvn clean test" or go to the test directory 
and run the tests manually in each test class
