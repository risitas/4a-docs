package com.grupo2.inventory_ms.models;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ProductModel {

    @Id
    private String code;
    private String name;
    private String specification;
    private int amount;
    private int price;
}