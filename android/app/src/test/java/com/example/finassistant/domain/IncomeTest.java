package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class IncomeTest {

    @Test
    public void getCategory(){
        Income income = new Income();
        Assert.assertEquals(IncomeCategory.SALARY,income.getCategory());
    }

    @Test
    public void setCategory(){
        Income income = new Income();
        income.setCategory(IncomeCategory.REGULAR);
        Assert.assertEquals(IncomeCategory.REGULAR,income.getCategory());
    }

    @Test
    public void checkExchangeCategory(){
        Income income = new Income();
        ExchangeCategory exchangeCategory = ExchangeCategory.CASH;
        income.setExchangeCategory(exchangeCategory);
        Assert.assertEquals(exchangeCategory,income.getExchangeCategory());

    }

    @Test
    public void checkIncome(){
        Date date = new Date();
        IncomeCategory category = IncomeCategory.SALARY;
        Income income = new Income(30.04,date,category);
        Assert.assertEquals(30.04,income.getSum(),0.000001);
    }

    @Test
    public void testCategoryList(){
        Income income = new Income();
        income.setCategory(IncomeCategory.REGULAR);
        Assert.assertEquals(income.getCategoryList()[1],income.getCategory());

    }
}