package com.example.finassistant.dao;

import com.example.finassistant.domain.User;

import java.util.List;

/**
 * διεπαφή Dao για την κλάση User
 *
 *
 */
public interface UserDAO {

    /**
     * Search of user based on its id
     * @param id the identification number of the user
     * @return the user found or {@code null} if not found
     */
    User find(int id);

    /**
     * Saves an object in our database.
     * Object can be new , so it is inserted
     * in the database, or already exists
     * and it's status is updated.
     * @param entity the object that is saved in the database
     */
    void save(User entity);

    /**
     * Delete object from our database
     * @param entity the object for deletion
     */
    void delete(User entity);

    /**
     * Returns all objects from our database
     * @return catalog of the objects
     * */
    List<User> findAll();




}
