package com.springboot.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorPageTest {


    @Test
    void equalSame() {
        ErrorPage ep = new ErrorPage("error page same");
        ErrorPage ep2 = new ErrorPage("error page same");
        assertEquals(ep, ep2);

    }

    @Test
    void equalDiffernet() {
        ErrorPage ep = new ErrorPage("error page 1");
        ErrorPage ep2 = new ErrorPage("error page 2");
        assertNotEquals(ep, ep2);
    }

}