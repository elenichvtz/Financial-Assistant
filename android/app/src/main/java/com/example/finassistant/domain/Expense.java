package com.example.finassistant.domain;

import java.util.Date;

/**
 * The type Expense.
 */
public class Expense extends Exchange{

    private ExpenseCategory category = ExpenseCategory.OBLIGATION; //default
    private static int counter = 1;

    /**
     * Instantiates a new Expense.
     */
    public Expense() {
        super();
    }

    /**
     * Instantiates a new Expense.
     *
     * @param sum      the sum
     * @param dateEnd  the date end
     * @param category the category
     */
    public Expense(double sum, Date dateEnd, ExpenseCategory category) {
        super(sum, dateEnd);
        this.category = category;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public ExpenseCategory getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    /**
     * Get category list object [ ].
     *
     * @return the object [ ]
     */
    public Object[] getCategoryList(){
        Object[] possibleValues = ExpenseCategory.class.getEnumConstants();
        return possibleValues;
    }
    @Override
    public String toString(){
        return "Expense"+counter++;
    }
}
