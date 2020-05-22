package com.example.finassistant.ui.user;

import com.example.finassistant.domain.Account;
import com.example.finassistant.domain.Email;

public interface UserView extends View{

    void setPresenter(UserPresenter presenter);

    int getUserId();

    void setUserId(int id);

    Email getEmail();

    void setEmail(Email email);

    String getPassword();

    void setPassword(String password);

    Account getAccount();

    void setAccount(Account account);



}
