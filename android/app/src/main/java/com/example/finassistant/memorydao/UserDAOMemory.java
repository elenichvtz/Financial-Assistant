package com.example.finassistant.memorydao;

import com.example.finassistant.dao.UserDAO;
import com.example.finassistant.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User dao memory.
 */
public class UserDAOMemory implements UserDAO {

    /**
     * The constant entities.
     */
    protected static List<User> entities = new ArrayList<User>();

    public void delete(User entity) {
        entities.remove(entity);
    }

    public List<User> findAll() {
        return new ArrayList<User>(entities);
    }


    public void save(User entity) {
        if (! entities.contains(entity)) {
            entities.add(entity);
        }
    }
    public User find(int id) {
        for(User user : entities) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

}
