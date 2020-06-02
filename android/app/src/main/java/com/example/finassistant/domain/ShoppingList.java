package com.example.finassistant.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Shopping list.
 */
public class ShoppingList {

    private String title;
    private Set<Product> products = new HashSet<>();

    /**
     * Instantiates a new Shopping list.
     */
    public ShoppingList(){ }

    /**
     * Instantiates a new Shopping list.
     *
     * @param title the title
     */
    public ShoppingList(String title) {
        this.title = title;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets products.
     *
     * @return the products
     */
    public Set<Product> getProducts() {
        return new HashSet<Product>(products);
    }

    /**
     * Add product.
     *
     * @param product the product
     */
    public void addProduct(Product product){
        if( product != null){
            this.products.add(product);
        }
    }

    /**
     * Remove product.
     *
     * @param product the product
     */
    public void removeProduct(Product product){
        if( product != null){
            this.products.remove(product);
        }
    }

    /**
     * Get total double.
     *
     * @return the double
     */
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
