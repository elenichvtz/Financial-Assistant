package com.example.finassistant.ui.account;

import java.util.Date;

public interface GoalView {
    /**
     * Sets the title field of a Goal Object
     * @param title the user inserts
     */
    void addTitle(String title);

    /**
     * Sets the sum field of a Goal Object
      @param amount
     */
    void addAmount(Double amount);

    /**
     * Sets the date field of a Goal Object
     * @param date
     */
    void addDate(Date date);

    /**
     * Message to be displayed when error occurs
     * @param title the title of the message
     * @param message the content of the message explaining the error
     */
    void showErrorMessage(String title,String message);
}
