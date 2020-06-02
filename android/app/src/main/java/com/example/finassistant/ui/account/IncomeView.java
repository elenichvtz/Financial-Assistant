package com.example.finassistant.ui.account;

import com.example.finassistant.dao.AccountDAO;
import com.example.finassistant.domain.Account;
import com.example.finassistant.domain.IncomeCategory;
import com.example.finassistant.domain.Product;
import com.example.finassistant.memorydao.AccountDAOMemory;

import java.util.Date;

public interface IncomeView {

    /**
     * Sets the sum field of an Income Object
     * @param amount we want to insert
     */
    void addAmount(Double amount);

    /**
     * Sets the date field of an Income Object
     * @param date
     */
    void addDate(Date date);

    /**
     * Sets the category field of an Income Object
     * @param category
     */
    void addCategory(IncomeCategory category);

    /**
     * Message to be displayed when error occurs
     * @param title the title of the message
     * @param message the content of the message explaining the error
     */
    void showErrorMessage(String title,String message);
}
