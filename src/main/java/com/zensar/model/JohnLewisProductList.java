package com.zensar.model;

import java.util.ArrayList;
import java.util.List;

public class JohnLewisProductList {

    private List<JohnLewisProduct> products = new ArrayList<>();

    public JohnLewisProductList() {
    }

    public JohnLewisProductList(List<JohnLewisProduct> products) {
        this.products = products;
    }

    public List<JohnLewisProduct> getProducts() {
        return products;
    }

    public void setProducts(List<JohnLewisProduct> products) {
        this.products = products;
    }
}
