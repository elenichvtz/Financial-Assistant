package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void UserTest(){
        Account account = new Account();
        account.setId(123456);
        Email email = new Email("examplegmail.com");
        User user = new User("Name",email,5,account);
        Assert.assertEquals("Name",user.getName());
        Assert.assertEquals(5,user.getId());
    }

    @Test
    public void testAccount(){
        Account account = new Account();
        account.setId(123456);

        User user = new User();
        user.setAccount(account);
        Assert.assertNotSame(account,user.getAccount());
    }

}
