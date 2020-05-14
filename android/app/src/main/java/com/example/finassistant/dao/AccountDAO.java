package com.example.finassistant.dao;

import com.example.finassistant.domain.Account;

import java.util.List;

/**
 * διεπαφή Dao για την κλάση User
 * @author ομάδα 3
 *
 */
public interface AccountDAO {
    Account find(int id);

    void save(Account entity);

    void delete(Account entity);

    List<Account> findAll();



}
