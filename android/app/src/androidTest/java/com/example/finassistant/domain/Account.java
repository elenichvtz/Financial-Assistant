package com.example.finassistant.domain;

import java.util.HashSet;
import java.util.Set;

public class Account {
    private int id;
    private User user;
    private double taxFree;

    private Set<Goal> goals = new HashSet<>();

    public Account(int id, User user, double taxFree) {
        this.id = id;
        this.user = user;
        this.taxFree = taxFree;
    }


    public Account(Account account) {
        this.id = account.getId();
        this.user = account.getUser();
        this.taxFree = account.getTaxFree();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTaxFree(double taxFree) {
        this.taxFree = taxFree;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public double getTaxFree() {
        return taxFree;
    }
}
