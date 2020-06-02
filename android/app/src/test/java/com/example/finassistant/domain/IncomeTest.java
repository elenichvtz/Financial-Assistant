package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * The type Income test.
 */
public class IncomeTest {

    /**
     * Check if when default constructor is used, expense category is the default one (SALARY).
     */
    @Test
    public void getCategory(){
        Income income = new Income();
        Assert.assertEquals(IncomeCategory.SALARY,income.getCategory());
    }

    /**
     * Check if income category provided is passed correctly with setCategory().
     */
    @Test
    public void setCategory(){
        Income income = new Income();
        income.setCategory(IncomeCategory.REGULAR);
        Assert.assertEquals(IncomeCategory.REGULAR,income.getCategory());
    }

    /**
     * Check if exchange category provided is passed correctly with setExchange().
     */
    @Test
    public void checkExchangeCategory(){
        Income income = new Income();
        ExchangeCategory exchangeCategory = ExchangeCategory.CASH;
        income.setExchange(exchangeCategory);
        Assert.assertEquals(ExchangeCategory.CASH,income.getExchange());
    }

    /**
     * Check if sum provided is passed correctly.
     */
    @Test
    public void checkIncome(){
        Date date = new Date();
        IncomeCategory category = IncomeCategory.SALARY;
        Income income = new Income(30.04,date,category);
        Assert.assertEquals(30.04,income.getSum(),0.000001);
    }

    /**
     * Check if categories' list is accessed correctly from an Expense object.
     */
    @Test
    public void testCategoryList(){
        Income income = new Income();
        income.setCategory(IncomeCategory.REGULAR);
        Assert.assertEquals(income.getCategoryList()[1],income.getCategory());
    }

    @Test
    public void testString(){
        Income income = new Income();
        Assert.assertEquals("Income1",income.toString());
    }
}
