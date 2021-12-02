package com.grupo2.inventory_ms.service;

import com.grupo2.inventory_ms.models.InventoryModel;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    List<InventoryModel> findAll();

    InventoryModel save(InventoryModel inventoryModel);
    InventoryModel update(InventoryModel inventoryModel);

    Optional<InventoryModel> findById(String id);

    void delete(String id);
}
