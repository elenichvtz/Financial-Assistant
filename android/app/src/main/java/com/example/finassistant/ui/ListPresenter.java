package com.example.finassistant.ui;

import com.example.finassistant.dao.AccountDAO;
import com.example.finassistant.domain.Account;
import com.example.finassistant.memorydao.AccountDAOMemory;

public class ListPresenter {

    private ListView view;
    private AccountDAO accountDAO;
    private Account account;

    public ListPresenter(ListView view){
        this.view = view;
        accountDAO = new AccountDAOMemory();
        account = accountDAO.find(1234);
    }

    public Account getAccount(){
        return this.account;
    }

}

