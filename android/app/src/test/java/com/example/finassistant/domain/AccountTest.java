package com.example.finassistant.domain;


import com.example.finassistant.domain.Account;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.Date;

public class AccountTest {


    @Test
    public void setUserTest(){
        Account account =  new Account();
        account.setId(1);
        User user = new User();
        account.setUser(user);
        Assert.assertEquals(1,account.getId());
        Assert.assertEquals(user, account.getUser());
    }

    @Test
    public void addNullIncome() {
        Account account = new Account();
        account.addIncome(null);
        Assert.assertEquals(0,account.getIncome().size());

    }

    @Test
    public void addIncome(){
        Account account = new Account();
        Income income = new Income();
        account.addIncome(income);
        Assert.assertEquals(1, account.getIncome().size());
        Assert.assertTrue(account.getIncome().contains(income));
    }

    @Test
    public void removeNullIncome(){
        Account account = new Account();
        Income income = new Income();
        account.addIncome(income);
        account.removeIncome(null);
        Assert.assertEquals(1, account.getIncome().size());

    }

    @Test
    public void removeIncome(){
        Account account = new Account();
        Income income = new Income();
        account.addIncome(income);
        account.removeIncome(income);
        Assert.assertEquals(0, account.getIncome().size());
    }

    @Test
    public void addNullExpense() {
        Account account = new Account();
        account.addExpense(null);
        Assert.assertEquals(0,account.getExpenses().size());
    }

    @Test
    public void addExpense(){
        Account account = new Account();
        Expense expense = new Expense();
        account.addExpense(expense);
        Assert.assertEquals(1, account.getExpenses().size());
        Assert.assertTrue(account.getExpenses().contains(expense));
    }

    @Test
    public void removeNullExpense(){
        Account account = new Account();
        Expense expense = new Expense();
        account.addExpense(expense);
        account.removeExpense(null);
        Assert.assertEquals(1, account.getExpenses().size());

    }

    @Test
    public void removeExpense(){
        Account account = new Account();
        Expense expense = new Expense();
        account.addExpense(expense);
        account.removeExpense(expense);
        Assert.assertEquals(0, account.getExpenses().size());
    }

    @Test
    public void addNullGoal() {
        Account account = new Account();
        account.addGoal(null);
        Assert.assertEquals(0,account.getGoals().size());
    }

    @Test
    public void addGoal(){
        Account account = new Account();
        Goal goal = new Goal();
        account.addGoal(goal);
        Assert.assertEquals(1, account.getGoals().size());
        Assert.assertTrue(account.getGoals().contains(goal));
    }

    @Test
    public void removeNullGoal(){
        Account account = new Account();
        Goal goal = new Goal();
        account.addGoal(goal);
        account.removeGoal(null);
        Assert.assertEquals(1, account.getGoals().size());

    }

    @Test
    public void removeGoal(){
        Account account = new Account();
        Goal goal = new Goal();
        account.addGoal(goal);
        account.removeGoal(goal);
        Assert.assertEquals(0,account.getGoals().size());
    }

    @Test
    public void checkTotal(){
        Account account = new Account();
        Date date = new Date();
        IncomeCategory category = IncomeCategory.SALARY;
        //ExchangeCategory exchangeCategory = ExchangeCategory.
        Income income = new Income(30.04,date,category);
        Income income2 = new Income(20.00,date,category);
        account.addIncome(income);
        account.addIncome(income2);
        Assert.assertEquals(50.04,account.CalculateTotalIncome(),0.0000001);
    }


    @Test
    public void checkTotalExpense(){
        Account account = new Account();
        Date date = new Date();
        ExpenseCategory category = ExpenseCategory.OBLIGATION;
        Expense expense = new Expense(49.99,date,category);
        Expense expense2 = new Expense(100.00,date,category);
        account.addExpense(expense);
        account.addExpense(expense2);
        Assert.assertEquals(149.99,account.CalculateTotalExpense(),0.000001);
    }


    @Test
    public void testTaxFree(){
        Account account = new Account();
        Date date = new Date();
        IncomeCategory category = IncomeCategory.SALARY;
        Income income = new Income(30.04,date,category);
        Income income2 = new Income(20.00,date,category);
        account.addIncome(income);
        account.addIncome(income2);
        account.CalculateTaxFree();
        Assert.assertEquals(15.012,account.getTaxFree(),0.0001);
    }


}