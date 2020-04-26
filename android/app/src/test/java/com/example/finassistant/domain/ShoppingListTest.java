package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ShoppingListTest {

    @Test
    public void testList() {
        Set<Product> products = new HashSet<>();
        ShoppingList list = new ShoppingList("example",products);
        Assert.assertEquals("example", list.getTitle());
        Assert.assertEquals(products, list.getProducts());
    }

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

    @Test
    public void testTitle() {
        ShoppingList list = new ShoppingList();
        list.setTitle("example");
        Assert.assertEquals("example", list.getTitle());
    }

    @Test
    public void testProducts() {
        ShoppingList list = new ShoppingList();
        Set<Product> products = new HashSet<>();
        list.setProducts(products);
        Assert.assertEquals(products, list.getProducts());
}
}
