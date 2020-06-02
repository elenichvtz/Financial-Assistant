package com.example.finassistant.domain;

import java.util.Date;

/**
 * The type Goal.
 */
public class Goal{

    private String title;
    private double amount;
    private Date endDate;
    private double currentamount;

    /**
     * Instantiates a new Goal.
     */
    public Goal(){ }

    /**
     * Instantiates a new Goal.
     *
     * @param title   the title
     * @param amount  the amount
     * @param endDate the end date
     */
    public Goal(String title, double amount, Date endDate) {
        this.title = title;
        this.amount = amount;
        this.endDate = endDate;
        this.currentamount = 0.0;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(double amount) {
        if(amount > 0) this.amount = amount;
        else System.out.println("Invalid input");
    }

    /**
     * Get amount double.
     *
     * @return the double
     */
    public double getAmount(){ return amount;}

    /**
     * Gets current amount.
     *
     * @return the current amount
     */
    public double getCurrentAmount() {return this.currentamount; }

    /**
     * Goal completion.
     *
     * @param completion the completion
     */
    public void GoalCompletion(double completion) {
        if(this.amount-this.currentamount<completion) {
            this.currentamount = this.amount;
        }
        else if(this.currentamount!=this.amount && this.amount-this.currentamount>=completion) {
            this.currentamount = this.currentamount + completion;
        }
    }

    @Override
    public String toString(){
        return ""+this.title+"";
    }
}
