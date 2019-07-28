package com.example.overlord.bookofsales.Model;

import java.io.Serializable;

public class Enterprise implements Serializable {
    private int id;
    private String name;
    private String adress;
    private String phone;
    private String checkingAccount;
    private String city;
    private String tin;
    private String okonx;
    private String okpo;

    public Enterprise(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCheckingAccount(String checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public void setOkonx(String okonx) {
        this.okonx = okonx;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }

    public String getCheckingAccount() {
        return checkingAccount;
    }

    public String getCity() {
        return city;
    }

    public String getTin() {
        return tin;
    }

    public String getOkonx() {
        return okonx;
    }

    public String getOkpo() {
        return okpo;
    }
}
