package com.grupo2.inventory_ms.repository;

import com.grupo2.inventory_ms.models.InventoryModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryModelRepository extends MongoRepository<InventoryModel,String> {


}
