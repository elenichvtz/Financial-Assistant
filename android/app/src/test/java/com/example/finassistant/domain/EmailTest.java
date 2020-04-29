package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

public class EmailTest {

    @Test
    public void testEquals(){
        Email email = new Email();
        Email email2 = new Email();

        Assert.assertFalse(email.equals(null));
        Assert.assertEquals(email,email2);
        Assert.assertEquals(email.hashCode(), email2.hashCode());

        email.setEmail("example@gmail.com");
        Assert.assertNotEquals(email,email2);
        Assert.assertFalse(email.hashCode() == email2.hashCode());
        email2.setEmail("example@gmail.com");
        Assert.assertEquals(email, email2);
        Assert.assertEquals(email.hashCode(), email2.hashCode());

    }

    @Test
    public void equalsAndHash(){
        BasicEqualTester<Email> equalsTester = new BasicEqualTester<Email>();
        equalsTester.setObjectUnderTest(new Email(null));


        equalsTester.otherObjectIsOfDifferentType(new Object());

        equalsTester.bothObjectsHaveNoState(new Email(null));

        equalsTester.setObjectUnderTest(new Email("a@b.gr"));
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectsHasNoState(new Email(null));
        equalsTester.objectsHaveDifferentState(new Email("123@123.com"));
        equalsTester.bothObjectsHaveSameState(new Email("a@b.gr"));

        Email email2 = equalsTester.getObjectUnderTest();
        equalsTester.sameReferences(email2);
    }
}
