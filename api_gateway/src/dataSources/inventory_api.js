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

    async productById(datos){
        datos = new Object(JSON.parse(JSON.stringify(datos)));
        return this.get(`/productById/`,datos);
    }

}
module.exports = InventoryAPI;