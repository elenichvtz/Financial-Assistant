package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class UserTest {

    @Test
    public void UserTest(){
        Email email = new Email("example@gmail.com");
        User user = new User("Name",email,"password123",123);
        Assert.assertEquals("Name",user.getName());
        Assert.assertEquals("password123",user.getPassword());
        Assert.assertEquals(email, user.getEmail());
        Assert.assertEquals(123,user.getId());
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

    @Test
    public void testId(){
        User user = new User();
        user.setId(1234);
        Assert.assertEquals(1234,user.getId());
    }

}
