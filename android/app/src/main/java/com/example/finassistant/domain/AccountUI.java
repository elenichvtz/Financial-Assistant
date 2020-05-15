package com.example.finassistant.domain;

import java.util.Set;

public interface AccountUI {

    void setId(int id);

    void setUser(User user);

    int getId();

    User getUser();

    double getTaxFree();

    Set<Expense> getExpenses();

    Set<Income> getIncome();

    Set<Goal> getGoals();

    Set<ShoppingList> getShoppingList();

    void addIncome(Income income);

    void removeIncome(Income income);

    void addExpense(Expense expense);

    void removeExpense(Expense expense);

    void addGoal(Goal goal);

    void removeGoal(Goal goal);

    void addList(ShoppingList list);

    void removeList(ShoppingList list);

    double CalculateTotalIncome();

    double CalculateTotalExpense();

    double CalculateTaxFree();

}