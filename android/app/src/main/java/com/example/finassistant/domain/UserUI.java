package com.example.finassistant.domain;

public interface UserUI {

     String getName();

     Email getEmail();

     String getPassword();

     Account getAccount();

     int getId();

     void setId(int id);

     void setAccount(Account account);

     void setName(String name);

     void setEmail(Email email);

     void setPassword(String password);
}
