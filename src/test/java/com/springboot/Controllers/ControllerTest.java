package com.springboot.Controllers;

import static org.junit.jupiter.api.Assertions.*;
import com.springboot.Models.ErrorPage;
import com.springboot.Models.Exchange;
import com.springboot.Models.ReturnPage;
import com.springboot.Services.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.*;

/** This class tests the Controller class
 * @author Moshiur Rahman
 */
class ControllerTest {

    Controller controllerMock;
    /**
     * Create a mock Controller object before each test
     */
    @BeforeEach
    public void createMock() {
        controllerMock = mock(Controller.class);
    }

    /**
     * Tests whether the user enters all three query parameters (source, target and amount)
     */
    @Test
    void indexBadCase() throws IOException {
        ErrorPage ep = new ErrorPage("Please enter all three query parameters (source, target and amount).");
        Map<String, String> queries = new HashMap<>();
//      The queries map has only source and target and is missing amount
        queries.put("source", "EUR");
        queries.put("target", "HKD");

        when(controllerMock.index(queries)).thenReturn(ep);
        assertEquals(controllerMock.index(queries), ep);
    }
    /**
     * Tests whether the user enters the same source and target currency
     */
    @Test
    void processSameSourceAndTarget() throws IOException {
        ErrorPage errorPage = new ErrorPage("Please make sure that the source currency is not the same as the target currency");
        when(controllerMock.process("source", "source","amount")).thenReturn(errorPage);
        assertEquals(controllerMock.process("source", "source","amount"), errorPage);
    }
    /**
     * Tests whether user enters valid source and target currencies
     */
    @Test
    void processCheckForValidSourceAndTarget() throws IOException {
//      Invalid source currency
        ErrorPage errorPage = new ErrorPage("Please enter a valid source currency");
        when(controllerMock.process("source", "EUR","amount")).thenReturn(errorPage);
        assertEquals(controllerMock.process("source", "EUR","amount"), errorPage);

//      Invalid target currency
        ErrorPage errorPage2 = new ErrorPage("Please enter a valid target currency");
        when(controllerMock.process("EUR", "target","amount")).thenReturn(errorPage2);
        assertEquals(controllerMock.process("EUR", "target","amount"), errorPage2);
    }
    /**
     * Tests robustness against different types of valid and invalid user input.
     * The is the good weather scenario
     */
    @Test
    void processCheckForValidInput() throws IOException{
        CurrencyService currencyServiceMock = mock(CurrencyService.class);

        Exchange exchange = new Exchange("Eur", "HKD", "10", "0");
        Exchange retrunValue = mock(Exchange.class);

        when(currencyServiceMock.prepare(exchange)).thenReturn(retrunValue);
        assertEquals(currencyServiceMock.prepare(exchange), retrunValue);

        Exchange answer = mock(Exchange.class);
        when(currencyServiceMock.getExchange(retrunValue)).thenReturn(answer);

        assertEquals(currencyServiceMock.getExchange(retrunValue), answer );


    }
    /**
     * Tests robustness against different types of valid and invalid user input.
     * The amount query parameter is checked here
     */
    @Test
    void checkAmountInput() {
//      Valid input
        when(controllerMock.checkAmountInput("10")).thenReturn(true);
        assertTrue(controllerMock.checkAmountInput("10"));

//      Valid input 2
        when(controllerMock.checkAmountInput("10.76")).thenReturn(true);
        assertTrue(controllerMock.checkAmountInput("10.76"));

//       Invalid input
        when(controllerMock.checkAmountInput("10.abcd")).thenReturn(false);
        assertFalse(controllerMock.checkAmountInput("10.abcd"));

//       Invalid input 2
        when(controllerMock.checkAmountInput("bad_input")).thenReturn(false);
        assertFalse(controllerMock.checkAmountInput("bad_input"));




    }
}