package com.example.finassistant.domain;

import java.util.Date;

public class Income extends Exchange{

    private IncomeCategory category = IncomeCategory.SALARY;
    private static int counter = 1;

    public Income() {
        super();
    }


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
