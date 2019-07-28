package com.example.overlord.bookofsales.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Bill implements Serializable {
    private int id;
    private int number;
    private Enterprise provider;
    private Enterprise customer;
    private ArrayList<Order> orders;
    private String date;
    private int cost;

    private boolean partialInit = false;

    public Bill(int id) {
        this.id = id;
    }

    public int getAllCost() {
        int sum = 0;
        if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) {
                sum += order.getCount() * order.getProduct().getCost();
            }
        }
        return sum;
    }

    public String getDate() {
        return date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isPartialInit() {
        return partialInit;
    }

    public void setPartialInit(boolean partialInit) {
        this.partialInit = partialInit;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {

        return number;
    }

    public void setProvider(Enterprise provider) {
        this.provider = provider;
    }

    public void setCustomer(Enterprise customer) {
        this.customer = customer;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public int getId() {

        return id;
    }

    public Enterprise getProvider() {
        return provider;
    }

    public Enterprise getCustomer() {
        return customer;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
