package com.example.finassistant.domain;

import java.util.Date;

public class Goal{

    private String title;
    private double amount;
    private Date endDate;

    public Goal(){ }

    public Goal(String title, double amount, Date endDate) {
        this.title = title;
        this.amount = amount;
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


    public void setAmount(double amount) {
        if(amount > 0) this.amount = amount;
        else System.out.println("Invalid input");
    }

    public double getAmount(){ return amount;}


}
