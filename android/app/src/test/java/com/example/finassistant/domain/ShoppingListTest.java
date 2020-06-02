package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * The type Shopping list test.
 */
public class ShoppingListTest {

    /**
     * Add null product.
     */
    @Test
    public void addNullProduct(){
        ShoppingList list = new ShoppingList();
        list.addProduct(null);
        Assert.assertEquals(0,list.getProducts().size());
    }

    /**
     * Add product.
     */
    @Test
    public void addProduct(){
        ShoppingList list = new ShoppingList();
        Product product = new Product();
        list.addProduct(product);
        Assert.assertEquals(1,list.getProducts().size());
    }

    /**
     * Remove null product.
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
     * Remove product.
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
     * Test title.
     */
    @Test
    public void testTitle() {
        ShoppingList list = new ShoppingList("example");
        list.setTitle("grocery shopping");
        Assert.assertEquals("grocery shopping", list.getTitle());
    }
}
