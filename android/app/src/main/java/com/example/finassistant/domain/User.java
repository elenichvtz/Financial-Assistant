package com.example.finassistant.domain;

public class User {

    private String name;
    private Email email;
    private int id;
    private Account account;

    public User(){

    }

    public User(String name, Email email, int id, Account account) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.account = account == null ? null : new Account(account);
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public Account getAccount() {
        return account == null ? null : new Account(account);
    }

    public void setAccount(Account account){
        this.account = account == null ? null : new Account(account);
    }
}
