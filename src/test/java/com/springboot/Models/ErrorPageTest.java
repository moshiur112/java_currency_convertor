package com.springboot.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/** This class tests the ErrorPage class
 * @author Moshiur Rahman
 */
class ErrorPageTest {

    /**
     * Tests the equals method for two identical ErrorPage objects
     */
    @Test
    void equalSame() {
        ErrorPage ep = new ErrorPage("error page same");
        ErrorPage ep2 = new ErrorPage("error page same");
        assertEquals(ep, ep2);

    }
    /**
     * Tests the equals method for two different ErrorPage objects
     */
    @Test
    void equalDiffernet() {
//      The input for the constructor for the ep2 object is different
        ErrorPage ep = new ErrorPage("error page 1");
        ErrorPage ep2 = new ErrorPage("error page 2");
        assertNotEquals(ep, ep2);
    }

}