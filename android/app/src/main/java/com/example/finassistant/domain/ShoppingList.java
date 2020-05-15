package com.example.finassistant.domain;

import java.util.HashSet;
import java.util.Set;

public class ShoppingList implements ShoppingListUI{

    private String title;
    private Set<Product> products = new HashSet<>();

    public ShoppingList(){ }

    public ShoppingList(String title) {
        this.title = title;
        this.products = products;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Product> getProducts() {
        return new HashSet<Product>(products);
    }

    public void addProduct(Product product){
        if( product != null){
            this.products.add(product);

        }
    }

    public void removeProduct(Product product){
        if( product != null){
            this.products.remove(product);

        }
    }
}
