package com.example.finassistant.domain;

/**
 * The type Product.
 */
public class Product {

    private String title;
    private double price;

    /**
     * Instantiates a new Product.
     */
    public Product(){ }

    /**
     * Instantiates a new Product.
     *
     * @param title the title
     * @param price the price
     */
    public Product(String title, double price) {
        this.title = title;
        this.price = price;
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
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return ""+this.title+"";
    }
}
