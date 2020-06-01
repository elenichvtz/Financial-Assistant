package com.example.finassistant.ui;

import com.example.finassistant.domain.Product;

public interface ListView {

    void addProduct(Product product);

    void addTitle();

    void addPrice();

    void addTitleList();
}
