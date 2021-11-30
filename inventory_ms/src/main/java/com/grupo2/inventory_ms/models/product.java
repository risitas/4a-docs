package com.grupo2.inventory_ms.models;

import org.springframework.data.annotation.Id;
import java.util.Date;

public class product {
    //atributos o variables
    private String code;
    private String name;
    private String especification;
    private int    amount ;
    private int    price;

    public product(String code, String name, String especification, int amount, int price) {
        this.code = code;
        this.name = name;
        this.especification = especification;
        this.amount = amount;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEspecification() {
        return especification;
    }

    public void setEspecification(String especification) {
        this.especification = especification;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "product{" +
                "  code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", especification='" + especification + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
