package com.springboot.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeTest {

    @Test
    void equalsSame() {
        Exchange exchange = new Exchange("source", "target", "amount", "value");
        Exchange exchange2 = new Exchange("source", "target", "amount", "value");
        assertEquals(exchange, exchange2);

    }

    @Test
    void equalsDifferent() {
//      The source of the two exchange objects are different
        Exchange exchange = new Exchange("source", "target", "amount", "value");
        Exchange exchange2 = new Exchange("source2", "target", "amount", "value");
        assertNotEquals(exchange, exchange2);
    }
}