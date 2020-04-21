package com.example.finassistant.domain;

public class Goal {

    private String title;
    private int sumGoal;
    //private Date endDate;


    public Goal(String title, int sumGoal) {
        this.title = title;
        this.sumGoal = sumGoal;
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
}
