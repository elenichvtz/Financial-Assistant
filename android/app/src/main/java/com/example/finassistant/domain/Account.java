package com.example.finassistant.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Account{
    private int id;
    private User user;
    private double taxFree;
    private double currenttaxFree;

    private Set<Goal> goals = new HashSet<>();
    private Set<Income> income = new HashSet<>();
    private Set<Expense> expenses = new HashSet<>();
    private Set<ShoppingList> shoppingList = new HashSet<>();
    private HashMap<Integer,Expense> temp = new HashMap<>();

    public Account(){ }

    public Account(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public Set<Income> getIncome() {
        return new HashSet<>(income);
    }

    public Set<Goal> getGoals() {
        return new HashSet<>(goals);
    }

    public Set<ShoppingList> getShoppingList() {
        return shoppingList;
    }

    public HashMap<Integer, Expense> getTemp() { return temp; }

    public void addIncome(Income income){
        if(income != null){
            this.income.add(income);

        }
    }

    public void removeIncome(Income income){
        if(income != null){
            this.income.remove(income);

        }
    }

    public void addExpense(Expense expense){
        if(expense != null){
            this.expenses.add(expense);

        }
    }

    public void removeExpense(Expense expense){
        if(expense != null){
            this.expenses.remove(expense);

        }
    }

    public void addGoal(Goal goal){
        if( goal != null){
            this.goals.add(goal);

        }
    }

    public void removeGoal(Goal goal){
        if(goal != null){
            this.goals.remove(goal);
        }
    }

    public void updateGoalExpenses(Goal goal, double amount) {
        goal.GoalCompletion(amount);
        Expense expence = new Expense(amount, new Date(), ExpenseCategory.OBLIGATION);
        addExpense(expence);
    }


    public void addList(ShoppingList list){
        if( list != null){
            this.shoppingList.add(list);

        }
    }

    public void removeList(ShoppingList list){
        if(list != null){
            this.shoppingList.remove(list);
        }
    }

    public double CalculateTotalIncome(){

        double total =0;

        for(Income income : income){
            total = total + income.getSum();
        }
        return total;
    }

    public double CalculateTotalExpense(){

        double total = 0;

        for(Expense expense: expenses){
            total = total + expense.getSum();
        }
        return total;
    }

    public double CalculateTaxFree(){

        this.taxFree = 0.3*CalculateTotalIncome();

        return this.taxFree;
    }

    public double CalculateCurrentTaxFree() {

        double total = 0;

        for (Expense expense: expenses) {
            if(expense.getExchange().equals(ExchangeCategory.ONLINE)){
                total = total + expense.getSum();
            }
        }
        return total;
    }

    public void ShoppingExpenses(){

        int count = 1;

        for(ShoppingList list: shoppingList){
            Expense expense = new Expense(list.getTotal(),null,ExpenseCategory.SHOPPING);
            temp.put(count,expense);
            this.addExpense(expense);
            count ++;
        }
    }
}
