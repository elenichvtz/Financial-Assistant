package com.example.finassistant.domain;

import java.util.Date;

public interface GoalUI {

    Date getEndDate();

    void setEndDate(Date endDate);

    String getTitle();

    void setTitle(String title);

    void setAmount(double amount);

    double getAmount();
}
