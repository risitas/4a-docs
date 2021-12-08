package com.grupo2.inventory_ms.service;

import com.grupo2.inventory_ms.models.InventoryModel;
import com.grupo2.inventory_ms.models.ProductModel;
import com.grupo2.inventory_ms.repository.InventoryModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InventoryServicesImpl implements InventoryService {

    private final InventoryModelRepository inventoryModelRepository;


    //____________________________________CRUD DE INVENTARIO_________________________//

    /**
     * método que me permite listar todos los inventarios de la base de datos.
     * @return
     */
    @Override
    public List<InventoryModel> findAll() {
        return inventoryModelRepository.findAll();
    }

    /**
     * método para buscar por id de inventario
     * @param id
     * @return
     */
    @Override
    public InventoryModel findById(String id) {

        InventoryModel inventoryModel = inventoryModelRepository.findById(id).orElse(null);
        if(inventoryModel != null){

            return inventoryModel;

        }
        return null;

    }

    /**
     * metodo para guardar un inventario
     * @param inventoryModel
     * @return
     */
    @Override
    public InventoryModel save(InventoryModel inventoryModel) {
        validate(inventoryModel);
        return inventoryModelRepository.save(inventoryModel);
    }

    private void validate(InventoryModel inventoryModel) {

        if (inventoryModel.getId_inventory() == null || inventoryModel.getId_inventory().isEmpty()) {
            throw new IllegalArgumentException("id is null or empy");
        }

    }

    /**
     * método para actualizar un inventario.
     * @param inventoryModel
     * @return
     */
    @Override
    public InventoryModel update(InventoryModel inventoryModel) {
        inventoryModelRepository.findById(inventoryModel.getId_inventory()).orElseThrow(() -> new IllegalArgumentException("inventory doesn't exist"));
        validate(inventoryModel);
        return inventoryModelRepository.save(inventoryModel);
    }

    /**
     * método para eliminar un inventario.
     * @param id
     */
    @Override
    public void delete(String id) {
        inventoryModelRepository.deleteById(id);
    }


    //____________________________________CRUD DE PRODUCTOS_________________________//


    @Override
    public List<ProductModel> allProduct(String id_inventory) {
        List<ProductModel> myProducts = new ArrayList<>();
        InventoryModel inventoryModel = inventoryModelRepository.findById(id_inventory).orElse(null);

        if(inventoryModel != null){
           myProducts.addAll(inventoryModel.getProducts());
           return  myProducts;
        }
        return null;
    }

    //


    @Override
    public ProductModel productById(String id_inventory, String code) {

        List<ProductModel> myProducts = new ArrayList<>();
        ProductModel productModel = null;


        InventoryModel inventoryModel = inventoryModelRepository.findById(id_inventory).orElse(null);
        if(inventoryModel != null){

            myProducts.addAll(inventoryModel.getProducts());

            for (int i=0; i<myProducts.size();i++){

                if(myProducts.get(i).getCode().equals(code)){
                   return productModel = myProducts.get(i);

                }
            }

        }
        return null;
    }

    @Override
    public ProductModel productSave(String id_inventory, ProductModel productModel) {


        InventoryModel inventoryModel = inventoryModelRepository.findById(id_inventory).orElse(null);
        if(inventoryModel != null){

            inventoryModel.getProducts().add(productModel);
            return productModel;

        }
        return null;

    }


    @Override
    public ProductModel productupdate(String id_inventory, ProductModel productModel) {
        return null;
    }

    @Override
    public void productdelete(String id_inventory, String code) {

    }




}