package com.grupo2.inventory_ms.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
public class InventoryModel { //coleccion inventario(coleccion producto)

    //Datos del inventario
    @Id
    private String id_inventory;
    private String name_inventory;
    private String date_creation;

    //#Datos del usuario
    private String id_user;
    private String user_name;

    private Set<ProductModel> products;

}