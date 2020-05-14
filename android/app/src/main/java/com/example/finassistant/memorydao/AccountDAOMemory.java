package com.example.finassistant.memorydao;

import com.example.finassistant.dao.AccountDAO;
import com.example.finassistant.domain.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountDAOMemory implements AccountDAO {

    protected static List<Account> entities = new ArrayList<Account>();

    public void delete(Account entity) {
        entities.remove(entity);
    }

    public List<Account> findAll() {
        return new ArrayList<Account>(entities);
    }


    public void save(Account entity) {
        if (! entities.contains(entity)) {
            entities.add(entity);
        }
    }
    public Account find(int id) {
        for(Account account : entities) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }



}
