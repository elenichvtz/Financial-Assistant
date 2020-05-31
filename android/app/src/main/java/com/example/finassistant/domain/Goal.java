package com.example.finassistant.domain;

import java.util.Date;

public class Goal{

    private String title;
    private double amount;
    private Date endDate;
    private double currentamount;
    private static int counter = 1;

    Goal(){ }

    public Goal(String title, double amount, Date endDate) {
        this.title = title;
        this.amount = amount;
        this.endDate = endDate;
        this.currentamount = 0.0;
    }

    public Date getEndDate() {
        return endDate;
    }

    void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setAmount(double amount) {
        if(amount > 0) this.amount = amount;
        else System.out.println("Invalid input");
    }

    public double getAmount(){ return amount;}

    public double getCurrentAmount() {return this.currentamount; }

    public double GoalCompletion(double completion) {
        if(this.amount-this.currentamount<completion) {
            this.currentamount = this.amount;
        }
        else if(this.currentamount!=this.amount && this.amount-this.currentamount>=completion) {
            this.currentamount = this.currentamount + completion;
        }
        return this.currentamount;
    }

    @Override
    public String toString(){
        return "Goal "+counter++;
    }
}
