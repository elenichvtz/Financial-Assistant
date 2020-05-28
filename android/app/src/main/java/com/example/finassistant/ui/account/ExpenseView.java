package com.example.finassistant.ui.account;

import com.example.finassistant.domain.ExpenseCategory;

import java.util.Date;

public interface ExpenseView {

    /**
     * Sets the sum field of an Income or Expense Object
     * @param amount we want to insert
     */
    void addAmount(Double amount);

    /**
     * Sets the date field of an Income or Expense Object
     * @param date
     */
    void addDate(Date date);

    /**
     * Sets the category field of an Income Object
     * @param category
     */
    void addCategory(ExpenseCategory category);

    /**
     *
     * @param title
     * @param message
     */
    void showErrorMessage(String title,String message);
}
