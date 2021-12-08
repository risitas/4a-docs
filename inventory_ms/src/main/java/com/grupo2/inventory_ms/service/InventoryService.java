package com.grupo2.inventory_ms.service;

import com.grupo2.inventory_ms.models.InventoryModel;
import com.grupo2.inventory_ms.models.ProductModel;

import java.util.List;

public interface InventoryService {

//____________________________________CRUD DE INVENTARIO_________________________//


    List<InventoryModel> findAll() ;
    InventoryModel findById(String id);

    InventoryModel save(InventoryModel inventoryModel) ;

    InventoryModel update(InventoryModel inventoryModel);
    void delete(String id) ;

    //____________________________________CRUD DE PRODUCTOS_________________________//

    List<ProductModel> allProduct(String id_inventory) ;
    ProductModel productById(String id_inventory, String code);

    ProductModel productSave(String id_inventory, ProductModel productModel) ;

    ProductModel productupdate(String id_inventory, ProductModel productModel);
    void productdelete(String id_inventory, String code) ;




}
