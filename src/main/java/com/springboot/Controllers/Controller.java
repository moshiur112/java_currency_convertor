package com.springboot.Controllers;

import com.springboot.Models.ErrorPage;
import com.springboot.Models.Exchange;
import com.springboot.Models.ReturnPage;
import com.springboot.Resources.Configuration;
import com.springboot.Services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.util.Map;

/** This class accepts the the API endpoint queries from the user and passes it to the Services to processed.
 * @author Moshiur Rahman
 */
@RestController
public class Controller {

	@Autowired
	CurrencyService currencyService;


	/**
	 *
	 * Calculated the exchange rate from the user input
	 * @param queries Map of all the queries entered by the user
	 * @return The calculated exchange rate
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ReturnPage index(@RequestParam Map<String, String> queries) throws IOException {

		String source = queries.get("source");
		String target = queries.get("target");
		String amount = queries.get("amount");
		if(!queries.containsKey("source") || !queries.containsKey("target") || !queries.containsKey("amount") ) {
			return new 	ErrorPage( "Please enter all three query parameters (source, target and amount).");
		}


		ReturnPage response = process(source, target, amount);
		return response;
	}

	/**
	 * Calculated the exchange rate from the user input
	 * @param source The source parameter entered by the user
	 * @param target The target parameter entered by the user
	 * @param amount The amount parameter entered by the user
	 * @return The calculated exchange rate
	 * @throws IOException
	 */
	public ReturnPage process(String source, String target, String amount) throws IOException {

		if(!Configuration.currencies.contains(source)) {
			return new ErrorPage( "Please enter a valid source currency");
		}
		if(!Configuration.currencies.contains(target)) {
			return new ErrorPage( "Please enter a valid target currency");
		}
		if(source.equals(target)) {

			return new ErrorPage( "Please make sure that the source currency is not the same as the target currency");
		}
		Boolean check = checkAmountInput(amount);
		if(!check) {
			return new ErrorPage("Please enter a number for the amount parameter");
		}
		Exchange exchange =  new Exchange(source, target, amount, "0");
		Exchange returnValue = currencyService.prepare(exchange);
		Exchange answer = currencyService.getExchange(returnValue);
		return answer;
	}

	/**
	 * Checks whether the user entered a number for the amount parameter
	 * @param amount The amount parameter entered by the user
	 * @return Returns true if the number is valid else returns false
	 */

	public boolean checkAmountInput(String amount) {
		boolean foundDigit = false;
		for(int i = 0; i < amount.length(); i++) {
			if(!Character.isDigit(amount.charAt(i)) && amount.charAt(i) != '.') {
				return false;
			}
			if(amount.charAt(i) == '.') {
				if(foundDigit) {
					return false;
				}
				foundDigit = true;
			}
		}
		return true;
	}

}

