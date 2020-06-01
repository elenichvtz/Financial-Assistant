package com.example.finassistant.ui.account;

import com.example.finassistant.domain.ExpenseCategory;

import java.util.Date;

public interface ExpenseView {

    /**
     * Sets the sum field of an Expense Object
     * @param amount we want to insert
     */
    void addAmount(Double amount);

    /**
     * Sets the date field of an Expense Object
     * @param date the date to be inserted
     */
    void addDate(Date date);

    /**
     * Sets the category field of an Expense Object
     * @param category the category to be inserted
     */
    void addCategory(ExpenseCategory category);

    /**
     * Shows error message
     * @param title is the title of the message
     * @param message the content of the message
     */
    void showErrorMessage(String title,String message);
}
