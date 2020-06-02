package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * The type Exchange test.
 */
public class ExchangeTest {

    /**
     * Test sum.
     */
    @Test
    public void testSum() {
        Exchange exchange = new Exchange();
        exchange.setSum(59.42);
        Assert.assertEquals(59.42, exchange.getSum(), 0.0000001);
    }

    /**
     * Test date.
     */
    @Test
    public void testDate() {
        Exchange exchange = new Exchange();
        Date date = new Date();
        exchange.setDateEnd(date);
        Assert.assertEquals(date, exchange.getDateEnd());
    }

    /**
     * Test insert sum.
     */
    @Test
    public void testInsertSum(){
        Exchange exchange = new Exchange();
        exchange.setSum(-234.96);
        Assert.assertEquals(0.0, exchange.getSum(), 0.001);
    }

    /**
     * Test exchange category.
     */
    @Test
    public void testExchangeCategory() {
        Exchange exchange = new Exchange();
        ExchangeCategory exchangeCategory = ExchangeCategory.CASH;
        exchange.setExchange(exchangeCategory);
        Assert.assertEquals(exchangeCategory, exchange.getExchange());
    }
}
