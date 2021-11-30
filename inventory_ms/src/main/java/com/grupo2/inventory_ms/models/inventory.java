package com.grupo2.inventory_ms.models;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;

public class inventory {
    // Atributos de users e inventory y lista de productos
    private String id_user;
    private String user_name;
    private String id_inventory;
    private String name_inventory;
    private Date date_creation;    // verificar o cambiar a String

    public inventory(String id_user, String user_name, String id_inventory, String name_inventory, Date date_creation) {
        this.id_user = id_user;
        this.user_name = user_name;
        this.id_inventory = id_inventory;
        this.name_inventory = name_inventory;
        this.date_creation = date_creation;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getId_inventory() {
        return id_inventory;
    }

    public void setId_inventory(String id_inventory) {
        this.id_inventory = id_inventory;
    }

    public String getName_inventory() {
        return name_inventory;
    }

    public void setName_inventory(String name_inventory) {
        this.name_inventory = name_inventory;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "inventory{" +
                "id_user='" + id_user + '\'' +
                ", user_name='" + user_name + '\'' +
                ", id_inventory='" + id_inventory + '\'' +
                ", name_inventory='" + name_inventory + '\'' +
                ", date_creation=" + date_creation +
                '}';
    }
}
