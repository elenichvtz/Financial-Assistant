package com.example.finassistant.ui.account;

import com.example.finassistant.dao.AccountDAO;
import com.example.finassistant.domain.Account;
import com.example.finassistant.memorydao.AccountDAOMemory;

/**
 * The type Product presenter.
 */
public class ProductPresenter {

    private ProductView view;
    private AccountDAO accountDAO;
    private Account account;

    /**
     * Instantiates a new Product presenter.
     *
     * @param view the view
     */
    public ProductPresenter(ProductView view){
        this.view = view;
        accountDAO = new AccountDAOMemory();
        account = accountDAO.find(1234);
    }

    /**
     * Validate amount boolean.
     *
     * @param amount the amount
     * @return the boolean
     */
    public boolean validateAmount(Double amount){
        if ( amount != 0.0 || amount==null){

            return true;
        }
        view.showErrorMessage("Error!","Invalid amount inserted!");
        return false;
    }

    /**
     * Validate title boolean.
     *
     * @param title the title
     * @return the boolean
     */
    public boolean validateTitle(String title){
        if(title.equals("")){
            view.showErrorMessage("Error!","Invalid title field!");
            return false;
        }
        return true;
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
