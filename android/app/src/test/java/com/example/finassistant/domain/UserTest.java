package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void testAccount(){
        Account account = new Account();
        account.setId(123456);

        User user = new User();
        user.setAccount(account);
        Assert.assertNotSame(account,user.getAccount());
    }

}
