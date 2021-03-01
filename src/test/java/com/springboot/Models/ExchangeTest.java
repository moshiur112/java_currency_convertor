package com.springboot.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/** This class tests the Exchange class
 * @author Moshiur Rahman
 */
class ExchangeTest {
    /**
     * Tests the equals method for two identical Exchange objects
     */
    @Test
    void equalsSame() {
        Exchange exchange = new Exchange("source", "target", "amount", "value");
        Exchange exchange2 = new Exchange("source", "target", "amount", "value");
        assertEquals(exchange, exchange2);

    }
    /**
     * Tests the equals method for two different Exchange objects
     */
    @Test
    void equalsDifferent() {
//      The source of the two exchange objects are different
        Exchange exchange = new Exchange("source", "target", "amount", "value");
        Exchange exchange2 = new Exchange("source2", "target", "amount", "value");
        assertNotEquals(exchange, exchange2);
    }
}