package com.example.finassistant.domain;

public class Income extends Exchange{

    private IncomeCategory category = IncomeCategory.SALARY;
    private ExchangeCategory exchangeCategory = ExchangeCategory.CASH;

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

    public Object getCategoryList(){
        Object[] possibleValues = IncomeCategory.class.getEnumConstants();
        return possibleValues;
    }

    public ExchangeCategory getExchangeCategory() {
        return exchangeCategory;
    }

    public void setExchangeCategory(ExchangeCategory exchangeCategory) {
        this.exchangeCategory = exchangeCategory;
    }



}
