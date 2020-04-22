package com.example.finassistant.domain;

public class Expense extends Exchange{

    private ExpenseCategory category = ExpenseCategory.DEPT; //default

    public Expense() {
        super();
    }

    public Expense(double sum, Date dateEnd) {
        super(sum, dateEnd);
    }
}
