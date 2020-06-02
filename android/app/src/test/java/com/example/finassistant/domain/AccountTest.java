package com.example.finassistant.domain;

import org.junit.*;

import java.util.Date;
import java.util.Iterator;

/**
 * The type Account test.
 */
public class AccountTest {

    /**
     * Check if id provided is passed correctly with setId().
     */
    @Test
    public void setUserTest(){
        Account account =  new Account();
        account.setId(1);
        Assert.assertEquals(1, account.getId());
    }

    /**
     * Check acount constructor.
     */
    @Test
    public void checkAcount() {
        User user = new User();
        Account account = new Account(1234, user);
        Assert.assertEquals(1234, account.getId());
        Assert.assertEquals(user, account.getUser());
    }

    /**
     * Check if user provided is passed correctly with setUser().
     */
    @Test
    public void checkUser(){
        User user = new User();
        Account account = new Account();
        account.setUser(user);
        Assert.assertEquals(user, account.getUser());
    }

    /**
     * Check if null income is passed, it doesn't get in Account's Income Set.
     */
    @Test
    public void addNullIncome() {
        Account account = new Account();
        account.addIncome(null);
        Assert.assertEquals(0, account.getIncome().size());
    }

    /**
     * Check if income is passed, it gets in Account's Income Set.
     */
    @Test
    public void addIncome(){
        Account account = new Account();
        Income income = new Income();
        account.addIncome(income);
        Assert.assertEquals(1, account.getIncome().size());
        Assert.assertTrue(account.getIncome().contains(income));
    }

    /**
     * Check if null income is removed, it doesn't affect Account's Income Set.
     */
    @Test
    public void removeNullIncome(){
        Account account = new Account();
        Income income = new Income();
        account.addIncome(income);
        account.removeIncome(null);
        Assert.assertEquals(1, account.getIncome().size());
    }

    /**
     * Check if income is removed, it affects Account's Income Set.
     */
    @Test
    public void removeIncome(){
        Account account = new Account();
        Income income = new Income();
        account.addIncome(income);
        account.removeIncome(income);
        Assert.assertEquals(0, account.getIncome().size());
    }

    /**
     * Check if null expense is added, it doesn't affect Account's Expenses Set.
     */
    @Test
    public void addNullExpense() {
        Account account = new Account();
        account.addExpense(null);
        Assert.assertEquals(0,account.getExpenses().size());
    }

    /**
     * Check if expense is added, it affects Account's Expenses Set.
     */
    @Test
    public void addExpense(){
        Account account = new Account();
        Expense expense = new Expense();
        account.addExpense(expense);
        Assert.assertEquals(1, account.getExpenses().size());
        Assert.assertTrue(account.getExpenses().contains(expense));
    }

    /**
     * Check if null expense is removed, it doesn't affect Account's Expenses Set.
     */
    @Test
    public void removeNullExpense(){
        Account account = new Account();
        Expense expense = new Expense();
        account.addExpense(expense);
        account.removeExpense(null);
        Assert.assertEquals(1, account.getExpenses().size());
    }

    /**
     * Check if expense is removed, it affects Account's Expenses Set.
     */
    @Test
    public void removeExpense(){
        Account account = new Account();
        Expense expense = new Expense();
        account.addExpense(expense);
        account.removeExpense(expense);
        Assert.assertEquals(0, account.getExpenses().size());
    }

    /**
     * Check if null goal is added, it doesn't affect Account's Goals Set.
     */
    @Test
    public void addNullGoal() {
        Account account = new Account();
        account.addGoal(null);
        Assert.assertEquals(0,account.getGoals().size());
    }

    /**
     * Check if goal is added, it affects Account's Goals Set.
     */
    @Test
    public void addGoal(){
        Account account = new Account();
        Goal goal = new Goal();
        account.addGoal(goal);
        Assert.assertEquals(1, account.getGoals().size());
        Assert.assertTrue(account.getGoals().contains(goal));
    }

    /**
     * Check if null goal is removed, it doesn't affect Account's Goals Set.
     */
    @Test
    public void removeNullGoal(){
        Account account = new Account();
        Goal goal = new Goal();
        account.addGoal(goal);
        account.removeGoal(null);
        Assert.assertEquals(1, account.getGoals().size());
    }

    /**
     * Check if goal is removed, it affects Account's Goals Set.
     */
    @Test
    public void removeGoal(){
        Account account = new Account();
        Goal goal = new Goal();
        account.addGoal(goal);
        account.removeGoal(goal);
        Assert.assertEquals(0,account.getGoals().size());
    }

    /**
     * Check if method CalculateTotalIncome() calculates total income correctly.
     */
    @Test
    public void checkTotalIncome(){
        Account account = new Account();
        Date date = new Date();
        IncomeCategory category = IncomeCategory.SALARY;
        Income income = new Income(30.04,date,category);
        Income income2 = new Income(20.00,date,category);
        account.addIncome(income);
        account.addIncome(income2);
        Assert.assertEquals(50.04,account.CalculateTotalIncome(),0.0000001);
    }


    /**
     * Check if method CalculateTotalExpense() calculates total expenses correctly.
     */
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

    /**
     * Check if null list is added, it doesn't affect Account's ShoppingList Set.
     */
    @Test
    public void addNullList(){
        Account account = new Account();
        account.addList(null);
        Assert.assertEquals(0,account.getShoppingList().size());
    }

    /**
     * Check if list is added, it affects Account's ShoppingList Set.
     */
    @Test
    public void addList(){
        Account account = new Account();
        ShoppingList list = new ShoppingList();
        account.addList(list);
        Assert.assertEquals(1,account.getShoppingList().size());
    }

    /**
     * Check if null list is removed, it doesn't affect Account's ShoppingList Set.
     */
    @Test
    public void removeNullList(){
        Account account = new Account();
        ShoppingList list = new ShoppingList();
        account.addList(list);
        account.removeList(null);
        Assert.assertEquals(1, account.getShoppingList().size());
    }

    /**
     * Check if list is removed, it affects Account's ShoppingList Set.
     */
    @Test
    public void removeList(){
        Account account = new Account();
        ShoppingList list = new ShoppingList();
        account.addList(list);
        account.removeList(list);
        Assert.assertEquals(0, account.getShoppingList().size());
    }

    /**
     * Check if list is added to Account's Expenses Set and if method getSum() calculates total products' prices correctly.
     */
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
        account.ShoppingExpenses(list);

        Assert.assertEquals(1,account.getExpenses().size());
        Assert.assertEquals(38.98,account.getTemp().get(1).getSum(),0.00001);
    }

    /**
     * Check if amount added to goal is added to Account's Expenses Set and if method getSum() calculates that amount correctly.
     */
    @Test
    public void checkGoalExpenses() {
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

    /**
     * Check if tax free is calculated correctly.
     */
    @Test
    public void checkTaxFree() {
        Account account = new Account();
        Income income = new Income();
        income.setSum(23.56);
        account.addIncome(income);
        Income income2 = new Income();
        income2.setSum(123.5);
        account.addIncome(income2);
        Assert.assertEquals(44.118, account.CalculateTaxFree(), 0.001);
    }

    /**
     * Check if current tax free is calculated correctly.
     */
    @Test
    public void checkCurrentTaxFree() {
        Account account = new Account();
        Expense expense = new Expense();
        expense.setSum(23.5);
        expense.setExchange(ExchangeCategory.CASH);
        Expense expense2 = new Expense();
        expense2.setSum(12.87);
        expense2.setExchange(ExchangeCategory.ONLINE);
        account.addExpense(expense);
        account.addExpense(expense2);
        Assert.assertEquals(12.87 ,account.CalculateCurrentTaxFree(), 0.001);
    }
}
