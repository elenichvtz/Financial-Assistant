package com.example.finassistant.domain;

import java.util.Date;

/**
 * The type Income.
 */
public class Income extends Exchange{

    private IncomeCategory category = IncomeCategory.SALARY;
    private static int counter = 1;

    /**
     * Instantiates a new Income.
     */
    public Income() {
        super();
    }

    /**
     * Instantiates a new Income.
     *
     * @param sum      the sum
     * @param dateEnd  the date end
     * @param category the category
     */
    public Income(double sum, Date dateEnd, IncomeCategory category) {
        super(sum, dateEnd);
        this.category = category;
    }

    public void setCategory(IncomeCategory category)
    {
        this.category = category;
    }

    public IncomeCategory getCategory(){
        return category;
    }

    public Object[] getCategoryList(){
        Object[] possibleValues = IncomeCategory.class.getEnumConstants();
        return possibleValues;
    }

    @Override
    public String toString(){
        return "Income"+counter++;
    }







}
