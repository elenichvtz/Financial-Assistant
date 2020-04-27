package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class ExchangeTest {

    @Test
    public void testSum() {
        Exchange exchange = new Exchange();
        exchange.setSum(59.42);
        Assert.assertEquals(59.42, exchange.getSum(), 0.0000001);
    }

    @Test
    public void testDate() {
        Exchange exchange = new Exchange();
        Date date = new Date();
        exchange.setDateEnd(date);
        Assert.assertEquals(date, exchange.getDateEnd());
    }

    @Test
    public void testExchangeCategory() {
        Exchange exchange = new Exchange();
        ExchangeCategory exchangeCategory = ExchangeCategory.CASH;
        exchange.setExchange(exchangeCategory);
        Assert.assertEquals(exchangeCategory, exchange.getExchange());
    }
}
