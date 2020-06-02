package com.example.finassistant.ui.account;

import com.example.finassistant.domain.Product;

/**
 * The interface Shopping list view.
 */
public interface ShoppingListView {

    /**
     * Add product.
     *
     * @param product the product
     */
    void addProduct(Product product);

    /**
     * Add title.
     *
     * @param title the title
     */
    void addTitle(String title);

    /**
     * Add price.
     *
     * @param price the price
     */
    void addPrice(double price);

    /**
     * Add title list.
     *
     * @param title the title
     */
    void addTitleList(String title);
}
