package com.example.finassistant.ui.account;

import com.example.finassistant.dao.AccountDAO;
import com.example.finassistant.domain.Account;
import com.example.finassistant.memorydao.AccountDAOMemory;

public class ProductPresenter {

    private ProductView view;
    private AccountDAO accountDAO;
    private Account account;

    public ProductPresenter(ProductView view){
        this.view = view;
        accountDAO = new AccountDAOMemory();
        account = accountDAO.find(1234);

    }

    public boolean validateAmount(Double amount){
        if ( amount != 0.0){

            return true;
        }
        view.showErrorMessage("Error!","Invalid amount inserted!");
        return false;
    }

    public Account getAccount(){
        return this.account;
    }
}
