package com.example.finassistant.memorydao;

import com.example.finassistant.dao.AccountDAO;
import com.example.finassistant.dao.Initializer;
import com.example.finassistant.dao.UserDAO;
import com.example.finassistant.domain.Account;
import com.example.finassistant.domain.User;

import java.util.List;

/**
 * The type Memory initializer.
 */
public class MemoryInitializer extends Initializer {

    @Override
    protected void eraseData() {
        List<Account> allAccounts = getAccountDAO().findAll();
        for(Account account : allAccounts) {
            getAccountDAO().delete(account);
        }

        List<User> allUsers = getUserDAO().findAll();
        for(User user : allUsers) {
            getUserDAO().delete(user);
        }
    }

    @Override
    protected AccountDAO getAccountDAO() {
        return new AccountDAOMemory();
    }

    @Override
    protected UserDAO getUserDAO() {
        return new UserDAOMemory();
    }
}
