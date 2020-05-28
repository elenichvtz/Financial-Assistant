package com.example.finassistant.domain;

import com.example.finassistant.domain.Account;


import static org.junit.Assert.*;
import org.junit.*;

import java.util.Date;
import java.util.Iterator;

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
    public void checkAccount(){
        int id = 12345;
        User user = new User();
        Account account = new Account(id, user);
        Assert.assertEquals(12345, account.getId());
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

    @Test
    public void addNullList(){
        Account account = new Account();
        account.addList(null);
        Assert.assertEquals(0,account.getShoppingList().size());
    }

    @Test
    public void addList(){
        Account account = new Account();
        ShoppingList list = new ShoppingList();
        account.addList(list);
        Assert.assertEquals(1,account.getShoppingList().size());
    }

    @Test
    public void removeNullList(){
        Account account = new Account();
        ShoppingList list = new ShoppingList();
        account.addList(list);
        account.removeList(null);
        Assert.assertEquals(1, account.getShoppingList().size());

    }

    @Test
    public void removeList(){
        Account account = new Account();
        ShoppingList list = new ShoppingList();
        account.addList(list);
        account.removeList(list);
        Assert.assertEquals(0, account.getShoppingList().size());
    }

    @Test
    public void checkListExpense(){
        Account account = new Account();
        Product product = new Product("toothbrush",2.99);
        Product product2 = new Product("vegetables",20.59);
        Product product3 = new Product("drinks",15.40);
        ShoppingList list = new ShoppingList("Super market essentials");
        list.addProduct(product);
        list.addProduct(product2);
        list.addProduct(product3);
        account.addList(list);
        account.ShoppingExpenses();

        Assert.assertEquals(1,account.getExpenses().size());
        Assert.assertEquals(38.98,account.getTemp().get(1).getSum(),0.00001);
    }

    @Test
    public void testGoalExpenses() {
        Account account = new Account();
        Goal goal = new Goal();
        goal.setAmount(89.51);
        account.addGoal(goal);
        account.updateGoalExpenses(goal, 43.56);
        Assert.assertEquals(1, account.getExpenses().size());
        for (Iterator<Expense> it = account.getExpenses().iterator(); it.hasNext(); ) {
            Expense expense = it.next();
            Assert.assertEquals(43.56, expense.getSum(), 0.001);
        }
    }

}
