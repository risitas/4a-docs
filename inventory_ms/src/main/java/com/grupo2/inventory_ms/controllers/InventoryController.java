package com.grupo2.inventory_ms.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grupo2.inventory_ms.models.InventoryModel;
import com.grupo2.inventory_ms.models.ProductModel;
import com.grupo2.inventory_ms.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
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
        return ResponseEntity.ok(inventoryService.findById(id));
    }


    //____________________________________CRUD DE PRODUCTOS_________________________//

    @GetMapping("/allProduct")
    public ResponseEntity<List<ProductModel>> allProduct(@RequestParam String id) {
        inventoryService.allProduct(id);
        return ResponseEntity.ok(inventoryService.allProduct(id));
    }


    @GetMapping("/productById")
    public ProductModel productById(@RequestParam String id_inventory,@RequestParam String code) {

        if(inventoryService.productById(id_inventory,code) != null){
            return inventoryService.productById(id_inventory,code);
        }

        return null;
    }

    @PostMapping("/productSave")
    public ResponseEntity<String> productSave(@RequestBody ObjectNode objectNode) {

        String id_inventory = objectNode.get("id_inventory").asText();
        ProductModel productModel = new ProductModel();
        productModel.setCode(objectNode.get("productModel").get("code").asText());
        productModel.setName(objectNode.get("productModel").get("name").asText());
        productModel.setPrice(objectNode.get("productModel").get("price").asInt());
        productModel.setAmount(objectNode.get("productModel").get("amount").asInt());
        productModel.setSpecification(objectNode.get("productModel").get("specification").asText());

        ProductModel productModel1 = inventoryService.productSave(id_inventory,productModel);
        if(productModel1 == null) {

            return ResponseEntity.ok("El código del inventario no existe");

        }

        return ResponseEntity.ok("Producto registrado");
    }

    @DeleteMapping("/productDelete")
    public ResponseEntity<String> productDelete(@RequestParam String id_inventory,@RequestParam String code) {

        boolean bandera;

        bandera = inventoryService.productDelete(id_inventory,code);

        if(bandera){
            return ResponseEntity.ok("Su producto fue eliminado");
        }else{
            return ResponseEntity.ok("El producto no existe");
        }

    }



    @PutMapping("/productUpdate")
    public ResponseEntity<String> productUpdate(@RequestBody ObjectNode objectNode) {

        String id_inventory = objectNode.get("id_inventory").asText();
        ProductModel productModel = new ProductModel();
        productModel.setCode(objectNode.get("productModel").get("code").asText());
        productModel.setName(objectNode.get("productModel").get("name").asText());
        productModel.setPrice(objectNode.get("productModel").get("price").asInt());
        productModel.setAmount(objectNode.get("productModel").get("amount").asInt());
        productModel.setSpecification(objectNode.get("productModel").get("specification").asText());


        ProductModel productModel1 = inventoryService.productUpdate(id_inventory,productModel);
        if(productModel1 == null) {

            return ResponseEntity.ok("No existe el producto");
        }


        return ResponseEntity.ok("Su producto fue modificado satisfactoriamente");
    }





}
