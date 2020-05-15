package com.example.finassistant.domain;

import java.util.Set;

public interface ShoppingListUI {

    String getTitle();

    void setTitle(String title);

    Set<Product> getProducts();

    void addProduct(Product product);

    void removeProduct(Product product);
}
