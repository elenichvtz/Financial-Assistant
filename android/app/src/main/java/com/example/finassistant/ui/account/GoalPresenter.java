package com.example.finassistant.ui.account;

import com.example.finassistant.dao.AccountDAO;
import com.example.finassistant.domain.Account;
import com.example.finassistant.memorydao.AccountDAOMemory;

public class GoalPresenter {

    private GoalView view;
    private AccountDAO accountDAO;
    private Account account;

    public GoalPresenter(GoalView view){
        this.view = view;
        accountDAO = new AccountDAOMemory();
        account = accountDAO.find(1234);
    }

    public boolean validateAmount(Double amount){
        if ( amount != 0.0 || amount==null){

            return true;
        }
        view.showErrorMessage("Error!","Invalid amount inserted!");
        return false;
    }

    public boolean validateTitle(String title){
        if(title.equals("")){
            view.showErrorMessage("Error!","Invalid title field!");
            return false;
        }
        return true;
    }

    public Account getAccount(){
        return this.account;
    }
}
