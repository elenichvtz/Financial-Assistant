package com.example.finassistant.domain;


public class User {

    private String name;
    private Email email;
    private String password;
    private int id;

    public User(){ }

    public User(String name, Email email, String password,int id) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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
