package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

public class ProductTest {

    @Test
    public void testProduct(){
        Product product = new Product("toothbrush",2.99);
        Assert.assertEquals("toothbrush",product.getTitle());
        Assert.assertEquals(2.99,product.getPrice(),0.0001);
    }

    @Test
    public void TestSetters(){
        Product product = new Product();
        product.setTitle("ice cream");
        product.setPrice(5.99);
        Assert.assertEquals("ice cream",product.getTitle());
        Assert.assertEquals(5.99,product.getPrice(),0.00001);

    }
}
