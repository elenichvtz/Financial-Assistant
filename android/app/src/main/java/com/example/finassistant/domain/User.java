package com.example.finassistant.domain;

public class User {

    private String name;
    private String email;
    private int id;
    private Account account;

    public User(){

    }

    public User(String name, String email, int id, Account account) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.account = account == null ? null : new Account(account);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
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
