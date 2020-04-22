package com.example.finassistant.domain;

public class Income extends Exchange{

    private IncomeCategory category = IncomeCategory.PAYMENT;

    public Income() {
        super();
    }

    public Income(double sum, Date dateEnd, IncomeCategory category) {
        super(sum, dateEnd);
        this.category = category;
    }

    


}
