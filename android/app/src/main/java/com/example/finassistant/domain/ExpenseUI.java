package com.example.finassistant.domain;

public interface ExpenseUI{

    ExpenseCategory getCategory();

    void setCategory(ExpenseCategory category);

    Object[] getCategoryList();
}
