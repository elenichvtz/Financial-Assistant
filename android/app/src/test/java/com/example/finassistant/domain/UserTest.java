package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void UserTest(){
        Account account = new Account();
        account.setId(123456);
        Email email = new Email("example@gmail.com");
        User user = new User("Name",email,"password123",account);
        Assert.assertEquals("Name",user.getName());
        Assert.assertEquals("password123",user.getPassword());
        Assert.assertEquals(email, user.getEmail());
    }

    @Test
    public void testAccount(){
        Account account = new Account();
        account.setId(123456);
        User user = new User();
        user.setAccount(account);
        Assert.assertNotSame(account,user.getAccount());
    }

    @Test
    public void testPassword(){
        User user = new User();
        user.setPassword("123");
        Assert.assertEquals(null,user.getPassword());
    }

    @Test
    public void testPassword2(){
        User user = new User();
        user.setPassword("12345678");
        Assert.assertEquals("12345678",user.getPassword());
    }

    @Test
    public void testName(){
        User user = new User();
        user.setName("Name");
        Assert.assertEquals("Name",user.getName());
    }

    @Test
    public void testEmail(){
        User user = new User();
        Email email = new Email("example@gmail.com");
        user.setEmail(email);
        Assert.assertEquals(email,user.getEmail());
    }
}
