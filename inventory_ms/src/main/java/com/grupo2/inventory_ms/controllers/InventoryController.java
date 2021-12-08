package com.grupo2.inventory_ms.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grupo2.inventory_ms.models.InventoryModel;
import com.grupo2.inventory_ms.models.ProductModel;
import com.grupo2.inventory_ms.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    //____________________________________CRUD DE INVENTARIO_________________________//


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

    @GetMapping("/findById")
    public ResponseEntity<InventoryModel> findById(@RequestParam String id) {
        inventoryService.findById(id);
        return ResponseEntity.ok(inventoryService.findById(id));
    }


    //____________________________________CRUD DE PRODUCTOS_________________________//

    @GetMapping("/allProduct")
    public ResponseEntity<List<ProductModel>> allProduct(@RequestParam String id) {
        inventoryService.allProduct(id);
        return ResponseEntity.ok(inventoryService.allProduct(id));
    }


    @GetMapping("/productById")
    public ResponseEntity<ProductModel> productById(@RequestBody String id_inventory, String code) {
        inventoryService.productById(id_inventory,code);
        return ResponseEntity.ok( inventoryService.productById(id_inventory,code));
    }

    @PostMapping("/productSave")
    public ResponseEntity<ProductModel> productSave(@RequestBody ObjectNode objectNode) {
        String id_inventory = objectNode.get("id_inventory").asText();
        ProductModel productModel = new ProductModel();
        productModel.setCode(objectNode.get("productModel").get("code").asText());
        productModel.setName(objectNode.get("productModel").get("name").asText());
        productModel.setPrice(objectNode.get("productModel").get("price").asInt());
        productModel.setAmount(objectNode.get("productModel").get("amount").asInt());
        productModel.setSpecification(objectNode.get("productModel").get("specification").asText());

        ProductModel productModel1 = inventoryService.productSave(id_inventory,productModel);

        return ResponseEntity.ok(productModel);
    }

}
