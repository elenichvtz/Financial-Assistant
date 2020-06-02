package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * The type Expense test.
 */
public class ExpenseTest {

    /**
     * Get category.
     */
    @Test
    public void getCategory(){
        Expense expense = new Expense();
        Assert.assertEquals(ExpenseCategory.OBLIGATION,expense.getCategory());
    }

    /**
     * Set category.
     */
    @Test
    public void setCategory(){
        Expense expense = new Expense();
        expense.setCategory(ExpenseCategory.TRANSPORT);
        Assert.assertEquals(ExpenseCategory.TRANSPORT,expense.getCategory());
    }

    /**
     * Check exchange.
     */
    @Test
    public void checkExchange(){
        Expense expense = new Expense();
        ExchangeCategory exchangeCategory = ExchangeCategory.ONLINE;
        expense.setExchange(exchangeCategory);
        Assert.assertEquals(exchangeCategory,expense.getExchange());
    }

    /**
     * Check expense.
     */
    @Test
    public void checkExpense(){
        Date date = new Date();
        ExpenseCategory category = ExpenseCategory.OBLIGATION;
        Expense expense = new Expense(30.04,date,category);
        Assert.assertEquals(30.04,expense.getSum(),0.000001);
    }

    /**
     * Test category list.
     */
    @Test
    public void testCategoryList(){
        Expense expense = new Expense();
        expense.setCategory(ExpenseCategory.OBLIGATION);
        Assert.assertEquals(expense.getCategoryList()[4],expense.getCategory());
    }
}
