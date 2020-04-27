package com.example.finassistant.domain;

import java.util.Date;

public class Expense extends Exchange{

    private ExpenseCategory category = ExpenseCategory.OBLIGATION; //default
    private ExchangeCategory exchangeCategory = ExchangeCategory.ONLINE;

    public Expense() {
        super();
    }

    public Expense(double sum, Date dateEnd, ExpenseCategory category) {

        super(sum, dateEnd);
        this.category = category;

    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public ExchangeCategory getExchangeCategory() {
        return exchangeCategory;
    }

    public void setExchangeCategory(ExchangeCategory exchangeCategory) {
        this.exchangeCategory = exchangeCategory;
    }

    public Object[] getCategoryList(){
        Object[] possibleValues = ExpenseCategory.class.getEnumConstants();
        return possibleValues;
    }
}
