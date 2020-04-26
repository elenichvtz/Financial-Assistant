package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

public class IncomeTest {

    @Test
    public void getCategory(){
        Income income = new Income();
        Assert.assertEquals(IncomeCategory.PAYMENT,income.getCategory());
    }

    @Test
    public void setCategory(){
        Income income = new Income();
        income.setCategory(IncomeCategory.DAILY);
        Assert.assertEquals(IncomeCategory.DAILY,income.getCategory());
    }

    /*@Test
    public void checkExchangeCategory(){
        Income income = new Income();
        ExchangeCategory exchangeCategory = ExchangeCategory.CASH;
        income.setExchangeCategory(exchangeCategory);
        Assert.assertEquals();


    }*/

    @Test
    public void checkIncome(){
        Date date = new Date();
        IncomeCategory category = IncomeCategory.EMERGENCY;
        Income income = new Income(30.04,date,category);
        Assert.assertEquals(30.04,income.getSum(),0.000001);
    }
}
