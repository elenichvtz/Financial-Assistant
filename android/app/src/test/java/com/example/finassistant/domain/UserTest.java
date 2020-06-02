package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * The type User test.
 */
public class UserTest {

    /**
     * User test.
     */
    @Test
    public void UserTest(){
        Email email = new Email("example@gmail.com");
        User user = new User("Name",email,"password123",123);
        Assert.assertEquals("Name",user.getName());
        Assert.assertEquals("password123",user.getPassword());
        Assert.assertEquals(email, user.getEmail());
        Assert.assertEquals(123,user.getId());
    }

    /**
     * Check if short password provided is not passed in User.
     */
    @Test
    public void testPassword(){
        User user = new User();
        user.setPassword("123");
        Assert.assertEquals(null,user.getPassword());
    }

    /**
     * Check if password provided is passed correctly with setPassword().
     */
    @Test
    public void testPassword2(){
        User user = new User();
        user.setPassword("12345678");
        Assert.assertEquals("12345678",user.getPassword());
    }

    /**
     * Check if name provided is passed correctly with setName().
     */
    @Test
    public void testName(){
        User user = new User();
        user.setName("Name");
        Assert.assertEquals("Name",user.getName());
    }

    /**
     * Check if email provided is passed correctly with setEmail().
     */
    @Test
    public void testEmail(){
        User user = new User();
        Email email = new Email("example@gmail.com");
        user.setEmail(email);
        Assert.assertEquals(email,user.getEmail());
    }

    /**
     * Check if id provided is passed correctly with setId().
     */
    @Test
    public void testId(){
        User user = new User();
        user.setId(1234);
        Assert.assertEquals(1234,user.getId());
    }
}
