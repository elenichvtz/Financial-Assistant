package com.example.finassistant.ui.account;

import com.example.finassistant.dao.AccountDAO;
import com.example.finassistant.domain.Account;
import com.example.finassistant.memorydao.AccountDAOMemory;

/**
 * The type Shopping list presenter.
 */
public class ShoppingListPresenter {

    private ShoppingListView view;
    private AccountDAO accountDAO;
    private Account account;

    /**
     * Instantiates a new Shopping list presenter.
     *
     * @param view the view
     */
    public ShoppingListPresenter(ShoppingListView view){
        this.view = view;
        accountDAO = new AccountDAOMemory();
        account = accountDAO.find(1234);
    }

    /**
     * Get account account.
     *
     * @return the account
     */
    public Account getAccount(){
        return this.account;
    }

}

