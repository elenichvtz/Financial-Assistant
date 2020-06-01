package com.example.finassistant.ui.account;

import android.widget.ArrayAdapter;

import com.example.finassistant.domain.Product;

public interface ShoppingListView {

    void addProduct(Product product);

    void addTitle(String title);

    void addPrice(double price);

    void addTitleList(String title);
}
