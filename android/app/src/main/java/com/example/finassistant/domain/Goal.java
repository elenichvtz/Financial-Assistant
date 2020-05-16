package com.example.finassistant.domain;

import java.util.Date;

public class Goal{

    private String title;
    private double amount;
    private Date endDate;
    private double currentamount;

    Goal(){ }

    Goal(String title, double amount, Date endDate) {
        this.title = title;
        this.amount = amount;
        this.endDate = endDate;
        this.currentamount = 0.0;
    }

    Date getEndDate() {
        return endDate;
    }

    void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setAmount(double amount) {
        if(amount > 0) this.amount = amount;
        else System.out.println("Invalid input");
    }

    double getAmount(){ return amount;}

    double getCurrentAmmount() {return this.currentamount; }

    double GoalCompletion(double completion) {
        this.currentamount = this.currentamount + completion;
        return this.currentamount;
    }
}
