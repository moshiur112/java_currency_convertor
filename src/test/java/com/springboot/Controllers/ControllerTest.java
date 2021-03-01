package com.springboot.Controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.springboot.Models.ErrorPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;
class ControllerTest {

    Controller controllerMock;

    @BeforeEach
    public void createMock() {
        controllerMock = mock(Controller.class);
    }

    @Test
    void index() {
    }

    @Test
    void processSameSourceAndTarget() throws IOException {
        ErrorPage errorPage = new ErrorPage("Please make sure that the source currency is not the same as the target currency");
        when(controllerMock.process("source", "source","amount")).thenReturn(errorPage);
        assertEquals(controllerMock.process("source", "source","amount"), errorPage);
    }

    @Test
    void processChakForValidSourceAndTarget() throws IOException {
//      Invalid source currency
        ErrorPage errorPage = new ErrorPage("Please enter a valid source currency");
        when(controllerMock.process("source", "EUR","amount")).thenReturn(errorPage);
        assertEquals(controllerMock.process("source", "EUR","amount"), errorPage);

//      Invalid target currency
        ErrorPage errorPage2 = new ErrorPage("Please enter a valid target currency");
        when(controllerMock.process("EUR", "target","amount")).thenReturn(errorPage2);
        assertEquals(controllerMock.process("EUR", "target","amount"), errorPage2);
    }

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