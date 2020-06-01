package com.example.finassistant.domain;

import java.util.HashSet;
import java.util.Set;

public class ShoppingList {

    private String title;
    private Set<Product> products = new HashSet<>();

    public ShoppingList(){ }

    public ShoppingList(String title) {
        this.title = title;
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

    public double getTotal(){
        double total = 0;
        for(Product product : products){
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public String toString(){
        return ""+this.title+"";
    }
}
