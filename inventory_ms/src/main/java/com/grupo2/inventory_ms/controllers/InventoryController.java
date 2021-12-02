package com.grupo2.inventory_ms.controllers;

import com.grupo2.inventory_ms.models.InventoryModel;
import com.grupo2.inventory_ms.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/findAll")
    public ResponseEntity<List<InventoryModel>> findAll() {
        return ResponseEntity.ok(inventoryService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<InventoryModel> save(@RequestBody InventoryModel request) {
        return ResponseEntity.ok(inventoryService.save(request));
    }

    @PutMapping("/update")
    public ResponseEntity<InventoryModel> update(@RequestBody InventoryModel request) {
        return ResponseEntity.ok(inventoryService.update(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam String id) {
        inventoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findInventory")
    public ResponseEntity<InventoryModel> findById(@RequestBody String id) {
        inventoryService.findById(id);
        return ResponseEntity.ok().build();
    }
}
