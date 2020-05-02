package com.example.finassistant.domain;

import org.jetbrains.annotations.NotNull;

public class User {

    private String name;
    private Email email;
    private String password;
    private Account account;

    public User(){ }

    public User(String name, Email email, String password, Account account) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.account = account == null ? null : new Account(account);
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

    public void setAccount(Account account){
        this.account = account == null ? null : new Account(account);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPassword(@NotNull String password) {
        if(password.length() >= 8)  this.password = password;
        else System.out.println("Not enough characters");
    }
}
