package com.example.finassistant.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Account{
    private int id;
    private User user;
    private double taxFree;

    private Set<Goal> goals = new HashSet<>();
    private Set<Income> income = new HashSet<>();
    private Set<Expense> expenses = new HashSet<>();
    private Set<ShoppingList> shoppingList = new HashSet<>();

    public Account(){ }

    public Account(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public Account(Account account) {
        this.id = account.getId();
        this.user = account.getUser();
        this.taxFree = account.getTaxFree();
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

    public double getTaxFree() {
        return taxFree;
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
}
