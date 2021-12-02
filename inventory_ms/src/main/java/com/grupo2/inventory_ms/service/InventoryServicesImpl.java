package com.grupo2.inventory_ms.service;

import com.grupo2.inventory_ms.models.InventoryModel;
import com.grupo2.inventory_ms.repository.InventoryModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryServicesImpl implements InventoryService{

    private final InventoryModelRepository inventoryModelRepository;

    @Override
    public List<InventoryModel> findAll() {
        return inventoryModelRepository.findAll();
    }

    @Override
    public InventoryModel save(InventoryModel inventoryModel) {
        validate(inventoryModel);
        return inventoryModelRepository.save(inventoryModel);
    }

    private void validate(InventoryModel inventoryModel) {

        if (inventoryModel.getId_inventory() == null || inventoryModel.getId_inventory().isEmpty()){
            throw new IllegalArgumentException("id is null or empy");
        }

    }

    @Override
    public InventoryModel update(InventoryModel inventoryModel) {
        inventoryModelRepository.findById(inventoryModel.getId_inventory()).orElseThrow(() -> new IllegalArgumentException("inventory doesn't exist"));
        validate(inventoryModel);
        return inventoryModelRepository.save(inventoryModel);
    }


      @Override
    public Optional<InventoryModel> findById(String id) {
      //inventoryModelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("inventory doesn't exist"));
      return inventoryModelRepository.findById(id);
    }


    @Override
    public void delete(String id) {
        inventoryModelRepository.deleteById(id);
    }
}
