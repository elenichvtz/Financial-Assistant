package com.example.finassistant.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Account.
 */
public class Account{
    private int id;
    private User user;
    private double taxFree;

    private Set<Goal> goals = new HashSet<>();
    private Set<Income> income = new HashSet<>();
    private Set<Expense> expenses = new HashSet<>();
    private Set<ShoppingList> shoppingList = new HashSet<>();
    private HashMap<Integer,Expense> temp = new HashMap<>();
    private static int count = 1;

    /**
     * Instantiates a new Account.
     */
    public Account(){ }

    /**
     * Instantiates a new Account.
     *
     * @param id   the id
     * @param user the user
     */
    public Account(int id, User user) {
        this.id = id;
        this.user = user;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets expenses.
     *
     * @return the expenses
     */
    public Set<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Gets income.
     *
     * @return the income
     */
    public Set<Income> getIncome() {
        return new HashSet<>(income);
    }

    /**
     * Gets goals.
     *
     * @return the goals
     */
    public Set<Goal> getGoals() {
        return new HashSet<>(goals);
    }

    /**
     * Gets shopping list.
     *
     * @return the shopping list
     */
    public Set<ShoppingList> getShoppingList() {
        return shoppingList;
    }

    /**
     * Gets temp.
     *
     * @return the temp
     */
    public HashMap<Integer, Expense> getTemp() { return temp; }

    /**
     * Add income.
     *
     * @param income the income
     */
    public void addIncome(Income income){
        if(income != null){
            this.income.add(income);
        }
    }

    /**
     * Remove income.
     *
     * @param income the income
     */
    public void removeIncome(Income income){
        if(income != null){
            this.income.remove(income);
        }
    }

    /**
     * Add expense.
     *
     * @param expense the expense
     */
    public void addExpense(Expense expense){
        if(expense != null){
            this.expenses.add(expense);
        }
    }

    /**
     * Remove expense.
     *
     * @param expense the expense
     */
    public void removeExpense(Expense expense){
        if(expense != null){
            this.expenses.remove(expense);

        }
    }

    /**
     * Add goal.
     *
     * @param goal the goal
     */
    public void addGoal(Goal goal){
        if( goal != null){
            this.goals.add(goal);
        }
    }

    /**
     * Remove goal.
     *
     * @param goal the goal
     */
    public void removeGoal(Goal goal){
        if(goal != null){
            this.goals.remove(goal);
        }
    }

    /**
     * Update goal expenses.
     *
     * @param goal   the goal
     * @param amount the amount
     */
    public void updateGoalExpenses(Goal goal, double amount) {
        goal.GoalCompletion(amount);
        Expense expence = new Expense(amount, new Date(), ExpenseCategory.OBLIGATION);
        addExpense(expence);
    }


    /**
     * Add list.
     *
     * @param list the list
     */
    public void addList(ShoppingList list){
        if( list != null){
            this.shoppingList.add(list);
        }
    }

    /**
     * Remove list.
     *
     * @param list the list
     */
    public void removeList(ShoppingList list){
        if(list != null){
            this.shoppingList.remove(list);
        }
    }

    /**
     * Calculate total income double.
     *
     * @return the double
     */
    public double CalculateTotalIncome(){

        double total = 0;

        for(Income income : income){
            total = total + income.getSum();
        }
        return total;
    }

    /**
     * Calculate total expense double.
     *
     * @return the double
     */
    public double CalculateTotalExpense(){

        double total = 0;

        for(Expense expense: expenses){
            total = total + expense.getSum();
        }
        return total;
    }

    /**
     * Calculate tax free double.
     *
     * @return the double
     */
    public double CalculateTaxFree(){

        this.taxFree = 0.3*CalculateTotalIncome();
        return this.taxFree;
    }

    /**
     * Calculate current tax free double (current tax free is the amount of the all online expenses of the user).
     *
     * @return the double
     */
    public double CalculateCurrentTaxFree() {

        double total = 0;

        for (Expense expense: expenses) {
            if(expense.getExchange().equals(ExchangeCategory.ONLINE)){
                total = total + expense.getSum();
            }
        }
        return total;
    }

    /**
     * Shopping expenses.
     *
     * @param list the list
     */
    public void ShoppingExpenses(ShoppingList list){

        Date date = new Date();
        Expense expense = new Expense(list.getTotal(),date, ExpenseCategory.SHOPPING);
        this.addExpense(expense);
        this.temp.put(count, expense);
    }
}

