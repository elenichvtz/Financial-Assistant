package com.example.finassistant.domain;

import java.util.Date;

public class Goal {

    private String title;
    private int sumGoal;
    private double amount;
    private Date endDate;

    public Goal(){ }

    public Goal(String title, int sumGoal, Date endDate) {
        this.title = title;
        this.sumGoal = sumGoal;
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSumGoal() {
        return sumGoal;
    }

    public void setSumGoal(int sumGoal) {
        this.sumGoal = sumGoal;
    }

    public void setAmount(double amount) { this.amount = amount;}

    public double getAmount(){ return amount;}


}
