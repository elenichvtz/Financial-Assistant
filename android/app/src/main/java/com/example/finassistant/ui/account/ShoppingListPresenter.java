package com.example.finassistant.ui.account;

import com.example.finassistant.dao.AccountDAO;
import com.example.finassistant.domain.Account;
import com.example.finassistant.memorydao.AccountDAOMemory;

public class ShoppingListPresenter {

    private ShoppingListView view;
    private AccountDAO accountDAO;
    private Account account;

    public ShoppingListPresenter(ShoppingListView view){
        this.view = view;
        accountDAO = new AccountDAOMemory();
        account = accountDAO.find(1234);
    }

    public Account getAccount(){
        return this.account;
    }

}

