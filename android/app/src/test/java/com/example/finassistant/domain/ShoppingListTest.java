package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShoppingListTest {



    @Test
    public void addNullProduct(){
        ShoppingList list = new ShoppingList();
        list.addProduct(null);
        Assert.assertEquals(0,list.getProducts().size());
    }
    @Test
    public void addProduct(){
        ShoppingList list = new ShoppingList();
        Product product = new Product();
        list.addProduct(product);
        Assert.assertEquals(1,list.getProducts().size());
    }

    @Test
    public void removeNullProduct(){
        ShoppingList list = new ShoppingList();
        Product product = new Product();
        list.addProduct(product);
        list.removeProduct(null);
        Assert.assertEquals(1,list.getProducts().size());
    }

    @Test
    public void removeProduct(){
        ShoppingList list = new ShoppingList();
        Product product = new Product();
        list.addProduct(product);
        list.removeProduct(product);
        Assert.assertEquals(0,list.getProducts().size());

    }
}
