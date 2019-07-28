package com.example.overlord.bookofsales.Model;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private Product product;
    private String units;
    private int count;

    public Order(int id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {

        return id;
    }

    public Product getProduct() {
        return product;
    }

    public String getUnits() {
        return units;
    }

    public int getCount() {
        return count;
    }
}
