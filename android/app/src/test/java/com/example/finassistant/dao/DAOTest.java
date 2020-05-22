package com.example.finassistant.dao;

import com.example.finassistant.domain.Account;
import com.example.finassistant.domain.Email;
import com.example.finassistant.domain.User;
import com.example.finassistant.memorydao.AccountDAOMemory;
import com.example.finassistant.memorydao.MemoryInitializer;
import com.example.finassistant.memorydao.UserDAOMemory;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

public class DAOTest {
    private UserDAO userDAO;
    private AccountDAO accountDAO;

    private static final int INITIAL_ACCOUNT_COUNT= 1;
    private static final int INITIAL_USER_COUNT= 1;

    /**
     * Preparing the data
     * @throws ParseException
     */
    @Before
    public void setUp() throws ParseException {
        Initializer helper = new MemoryInitializer();
        helper.prepareData();
        userDAO = new UserDAOMemory();
        accountDAO =  new AccountDAOMemory();

    }

    /**
     * Returns all existing users in the database
     */

    @Test
    public void listAllUsers(){
        List<User>  allUsers = userDAO.findAll();
        Assert.assertEquals(INITIAL_USER_COUNT,allUsers.size());
    }

    /**
     * Create a new user record in the database
     */

    @Test
    public void saveUser(){
        User user = new User("Joaquin Phoenix",new Email("phoenix@gmail.com"),"phoenix1234",null,7654);
        userDAO.save(user);
        Assert.assertEquals(INITIAL_USER_COUNT+1 , userDAO.findAll().size());
        Assert.assertTrue(userDAO.findAll().contains(user));
    }

    /**
     * Delete existing user from the database
     */

    @Test
    public void deleteUser(){
        List<User> allUsers = userDAO.findAll();
        User user = allUsers.get(0);
        userDAO.delete(user);
        allUsers = userDAO.findAll();
        Assert.assertEquals(INITIAL_USER_COUNT - 1, allUsers.size());
    }

    /**
     * Search for user in the database that exists
     */

    @Test
    public void findExistingUser(){
        User user = userDAO.find(9755);
        Assert.assertEquals("John Wick", user.getName());
    }

    /**
     * Search for user in the database that doesn't exist
     */

    @Test
    public void findNonExistingUser(){
        User user = userDAO.find(1000);
        Assert.assertNull(user);
    }

    /**
     * Returns all existing accounts in the database
     */

    @Test
    public void listAllAccounts(){
        List<Account> allAccounts = accountDAO.findAll();
        Assert.assertEquals(INITIAL_ACCOUNT_COUNT,allAccounts.size());
    }

    /**
     * Create a new account record in the database
     */

    @Test
    public void saveAccount(){
        Account account = new Account(5432,null);
        accountDAO.save(account);
        Assert.assertEquals(INITIAL_ACCOUNT_COUNT+1 , accountDAO.findAll().size());
        Assert.assertTrue(accountDAO.findAll().contains(account));
    }
    /**
     * Delete an existing account from the database
     */

    @Test
    public void deleteAccount(){
        List<Account> allAccounts = accountDAO.findAll();
        Account account = allAccounts.get(0);
        accountDAO.delete(account);
        allAccounts = accountDAO.findAll();
        Assert.assertEquals( INITIAL_ACCOUNT_COUNT - 1, allAccounts.size());
    }
    /**
     * Search for account in the database that exists
     */

    @Test
    public void findExistingAccount(){
        Account account = accountDAO.find(1234);
        Assert.assertEquals("John Wick",account.getUser().getName());
    }

    /**
     * Search for account in the database that doesn't exist
     */

    @Test
    public void findNonExistingAccount(){
        Account account = accountDAO.find(5928);
        Assert.assertNull(account);
    }

    /**
     * Checks if total expenses in account with id(1234) is equal to the expected amount
     */

    @Test
    public void totalExpenses(){
        Account account = accountDAO.find(1234);
        Assert.assertEquals(881.11, account.CalculateTotalExpense(), 0.000001);
    }


}
