package com.example.finassistant.domain;

public interface IncomeUI {

    void setCategory(IncomeCategory category);

    IncomeCategory getCategory();

    Object[] getCategoryList();
}
