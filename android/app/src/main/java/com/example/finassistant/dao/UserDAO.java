package com.example.finassistant.dao;

import com.example.finassistant.domain.User;

import java.util.List;

/**
 * διεπαφή Dao για την κλάση User
 * @author ομάδα 3
 *
 */
public interface UserDAO {

    User find(int id);

    void save(User entity);

    void delete(User entity);

    List<User> findAll();




}
