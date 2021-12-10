const { RESTDataSource } = require('apollo-datasource-rest');

const serverConfig = require('../server');

class InventoryAPI extends RESTDataSource {

    constructor() {
        super();
        this.baseURL = serverConfig.inventory_api_url;
    }
   
    async inventaryList(){
        return this.get(`/findAll`);
    }

    async inventorySave(inventory){
        inventory = new Object(JSON.parse(JSON.stringify(inventory)));
        return this.post(`/save`,inventory);
       
    }

    async inventoryUpdate(inventory){
        inventory = new Object(JSON.parse(JSON.stringify(inventory)));
        return this.put(`/update`,inventory);
    }

    async inventoryDelete(id){
        return this.delete(`/delete?id=${id}`);
    }

    async inventoryfindById(id){
        return this.get(`/findById?id=${id}`);
    }

    async productList(id){
        return this.get(`/allProduct?id=${id}`);
    }

    //Cambiar a parametros
    async productById(id_inventory,code){
        return this.get(`/productById?id_inventory=${id_inventory}&code=${code}`);
    }

    async productDelete(id_inventory,code){
        return this.delete(`/productDelete?id_inventory=${id_inventory}&code=${code}`);
    }


    async productSave(informacion){
        informacion = new Object(JSON.parse(JSON.stringify(informacion)));
        return this.post(`/productSave`,informacion);
    }

        async productUpdate(informacion){
            informacion = new Object(JSON.parse(JSON.stringify(informacion)));
            return this.put(`/productUpdate`,informacion);
        }

}
module.exports = InventoryAPI;