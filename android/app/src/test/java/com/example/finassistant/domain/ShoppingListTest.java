package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * The type Shopping list test.
 */
public class ShoppingListTest {

    /**
     * Check if null product is added, it doesn't affect ShoppingList's Products Set.
     */
    @Test
    public void addNullProduct(){
        ShoppingList list = new ShoppingList();
        list.addProduct(null);
        Assert.assertEquals(0,list.getProducts().size());
    }

    /**
     * Check if product is added, it affects ShoppingList's Prosuct Set.
     */
    @Test
    public void addProduct(){
        ShoppingList list = new ShoppingList();
        Product product = new Product();
        list.addProduct(product);
        Assert.assertEquals(1,list.getProducts().size());
    }

    /**
     * Check if null product is removed, it doesn't affect ShoppingList's Products Set.
     */
    @Test
    public void removeNullProduct(){
        ShoppingList list = new ShoppingList();
        Product product = new Product();
        list.addProduct(product);
        list.removeProduct(null);
        Assert.assertEquals(1,list.getProducts().size());
    }

    /**
     * Check if product is removed, it affects ShoppingList's Products Set.
     */
    @Test
    public void removeProduct(){
        ShoppingList list = new ShoppingList();
        Product product = new Product();
        list.addProduct(product);
        list.removeProduct(product);
        Assert.assertEquals(0,list.getProducts().size());
    }

    /**
     * Check if title provided is passed correctly with setTitle().
     */
    @Test
    public void testTitle() {
        ShoppingList list = new ShoppingList("example");
        list.setTitle("grocery shopping");
        Assert.assertEquals("grocery shopping", list.getTitle());
    }
}
