package com.example.overlord.bookofsales.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String okpd;
    private int cost;
    private int costExcise;
    private int nds;

    public Product(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOkpd(String okpd) {
        this.okpd = okpd;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCostExcise(int costExcise) {
        this.costExcise = costExcise;
    }

    public void setNds(int nds) {
        this.nds = nds;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOkpd() {
        return okpd;
    }

    public int getCost() {
        return cost;
    }

    public int getCostExcise() {
        return costExcise;
    }

    public int getNds() {
        return nds;
    }
}
