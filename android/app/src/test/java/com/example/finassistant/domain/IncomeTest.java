package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * The type Income test.
 */
public class IncomeTest {

    /**
     * Get category.
     */
    @Test
    public void getCategory(){
        Income income = new Income();
        Assert.assertEquals(IncomeCategory.SALARY,income.getCategory());
    }

    /**
     * Set category.
     */
    @Test
    public void setCategory(){
        Income income = new Income();
        income.setCategory(IncomeCategory.REGULAR);
        Assert.assertEquals(IncomeCategory.REGULAR,income.getCategory());
    }

    /**
     * Check exchange category.
     */
    @Test
    public void checkExchangeCategory(){
        Income income = new Income();
        ExchangeCategory exchangeCategory = ExchangeCategory.CASH;
        income.setExchange(exchangeCategory);
        Assert.assertEquals(exchangeCategory,income.getExchange());
    }

    /**
     * Check income.
     */
    @Test
    public void checkIncome(){
        Date date = new Date();
        IncomeCategory category = IncomeCategory.SALARY;
        Income income = new Income(30.04,date,category);
        Assert.assertEquals(30.04,income.getSum(),0.000001);
    }

    /**
     * Test category list.
     */
    @Test
    public void testCategoryList(){
        Income income = new Income();
        income.setCategory(IncomeCategory.REGULAR);
        Assert.assertEquals(income.getCategoryList()[1],income.getCategory());
    }

    /**
     * Random.
     */
    @Test
    public void random(){
        Income income = new Income();
        income.setCategory(IncomeCategory.REGULAR);
        income.setExchange(ExchangeCategory.ONLINE);
        Assert.assertEquals(ExchangeCategory.ONLINE,income.getExchange());
    }
}
