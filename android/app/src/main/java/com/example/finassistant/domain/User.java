package com.example.finassistant.domain;


public class User {

    private String name;
    private Email email;
    private String password;
    private Account account;
    private int id;

    public User(){ }

    public User(String name, Email email, String password, Account account,int id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.account = account == null ? null : new Account(account);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Account getAccount() {
        return account == null ? null : new Account(account);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public void setAccount(Account account){
        this.account = account == null ? null : new Account(account);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPassword(String password) {
        if(password.length() >= 8)  this.password = password;
        else System.out.println("Not enough characters");
    }
}
