package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * The type Expense test.
 */
public class ExpenseTest {

    /**
     * Check if when default constructor is used, expense category is the default one (OBLIGATION).
     */
    @Test
    public void getCategory(){
        Expense expense = new Expense();
        Assert.assertEquals(ExpenseCategory.OBLIGATION,expense.getCategory());
    }

    /**
     * Check if expense category provided is passed correctly with setCategory().
     */
    @Test
    public void setCategory(){
        Expense expense = new Expense();
        expense.setCategory(ExpenseCategory.TRANSPORT);
        Assert.assertEquals(ExpenseCategory.TRANSPORT,expense.getCategory());
    }

    /**
     * Check if exchange category provided is passed correctly with setExchange().
     */
    @Test
    public void checkExchange(){
        Expense expense = new Expense();
        ExchangeCategory exchangeCategory = ExchangeCategory.ONLINE;
        expense.setExchange(exchangeCategory);
        Assert.assertEquals(ExchangeCategory.ONLINE,expense.getExchange());
    }

    /**
     * Check if sum provided is passed correctly.
     */
    @Test
    public void checkExpense(){
        Date date = new Date();
        ExpenseCategory category = ExpenseCategory.OBLIGATION;
        Expense expense = new Expense(30.04,date,category);
        Assert.assertEquals(30.04,expense.getSum(),0.000001);
    }

    /**
     * Check if categories' list is accessed correctly from an Expense object.
     */
    @Test
    public void testCategoryList(){
        Expense expense = new Expense();
        expense.setCategory(ExpenseCategory.OBLIGATION);
        Assert.assertEquals(expense.getCategoryList()[4],expense.getCategory());
    }

    @Test
    public void testString(){
        Expense expense = new Expense();
        Assert.assertEquals("Expense1",expense.toString());
    }
}
