const { RESTDataSource } = require('apollo-datasource-rest');

const serverConfig = require('../server');

class InventoryAIP extends RESTDataSource {

    constructor() {
        super();
        this.baseURL = serverConfig.inventory_api_url;
    }
}