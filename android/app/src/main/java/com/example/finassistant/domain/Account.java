package com.example.finassistant.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Account{
    private int id;
    private User user;
    private double taxFree;

    private Set<Goal> goals = new HashSet<>();
    private Set<Income> income = new HashSet<>();
    private Set<Expense> expenses = new HashSet<>();
    private Set<ShoppingList> shoppingList = new HashSet<>();
    private HashMap<Integer,Expense> temp = new HashMap<>();

    Account(){ }

    Account(int id, User user) {
        this.id = id;
        this.user = user;
    }

    Account(Account account) {
        this.id = account.getId();
        this.user = account.getUser();
        this.taxFree = account.getTaxFree();
    }

    public void setId(int id) {
        this.id = id;
    }

    void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    User getUser() {
        return user;
    }

    double getTaxFree() {
        return taxFree;
    }

    Set<Expense> getExpenses() {
        return expenses;
    }

    Set<Income> getIncome() {
        return new HashSet<>(income);
    }

    Set<Goal> getGoals() {
        return new HashSet<>(goals);
    }

    Set<ShoppingList> getShoppingList() {
        return shoppingList;
    }

    public HashMap<Integer, Expense> getTemp() { return temp; }

    public void addIncome(Income income){
        if(income != null){
            this.income.add(income);

        }
    }

    void removeIncome(Income income){
        if(income != null){
            this.income.remove(income);

        }
    }

    void addExpense(Expense expense){
        if(expense != null){
            this.expenses.add(expense);

        }
    }

    void removeExpense(Expense expense){
        if(expense != null){
            this.expenses.remove(expense);

        }
    }

    void addGoal(Goal goal){
        if( goal != null){
            this.goals.add(goal);

        }
    }

    void removeGoal(Goal goal){
        if(goal != null){
            this.goals.remove(goal);
        }
    }

    void updateGoalExpenses(Goal goal, double amount) {
        goal.GoalCompletion(amount);
        Expense expence = new Expense(amount, new Date(), ExpenseCategory.OBLIGATION);
        addExpense(expence);
    }


    public void addList(ShoppingList list){
        if( list != null){
            this.shoppingList.add(list);

        }
    }

    void removeList(ShoppingList list){
        if(list != null){
            this.shoppingList.remove(list);
        }
    }

    double CalculateTotalIncome(){

        double total =0;

        for(Income income : income){
            total = total + income.getSum();
        }
        return total;
    }

    double CalculateTotalExpense(){

        double total = 0;

        for(Expense expense: expenses){
            total = total + expense.getSum();
        }
        return total;
    }

    double CalculateTaxFree(){

        this.taxFree = 0.3*CalculateTotalIncome();

        return this.taxFree;
    }

    /*public void validateAmounts(){
        if( CalculateTotalIncome() < CalculateTotalExpense()){
            //add something
        }
    }*/

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
