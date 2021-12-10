const { gql } = require('apollo-server');

const inventoryTypeDefs = gql `
    type ProductModel {
        code: String
        name: String
        specification: String
        amount: Int
        price: Int
    }


    type InventoryModel {
        id_inventory: String!
        name_inventory: String!
        date_creation: String!
        id_user: String!
        products: [ProductModel]
    }

    input InventoryInput{
        id_inventory: String!
        name_inventory: String!
        date_creation: String!
        id_user: String!
    }

    input ObjetoBuscarInput{
        id_inventory: String
        code: String
    }

    type Query {
        inventaryList: [InventoryModel]!
    }

    type Mutation{
        inventorySave(infoInventory: InventoryInput):InventoryModel
        inventoryUpdate(infoInventory: InventoryInput):InventoryModel
        inventoryDelete(id:String):String
        inventoryfindById(id:String):InventoryModel
        productList(id:String):[ProductModel]
        productById(datos:ObjetoBuscarInput):ProductModel
    }
`;

module.exports = inventoryTypeDefs;