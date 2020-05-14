package com.example.finassistant.memorydao;

import com.example.finassistant.dao.UserDAO;
import com.example.finassistant.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOMemory implements UserDAO {

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
    public User find(int itemNo) {  //NEED TO CHECK IF IT NEEDS DAO AND IF SO ADD AN ID FIELD TO THE MAIN CLASS AND ADD/CHANGE TESTS
        for(User user : entities) {
            if (user == null) { //it's not null it's user.getid() == id !!!!
                return user;
            }
        }
        return null;
    }

}
