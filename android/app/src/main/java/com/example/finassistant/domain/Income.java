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

    public void setCategory(IncomeCategory category)
    {
        this.category = category;
    }

    public IncomeCategory getCategory(){
        return category;
    }

    /*public IncomeCategory setCategory(int x){
        switch(x)
        {
            case 1:


        }
    }*/


}
