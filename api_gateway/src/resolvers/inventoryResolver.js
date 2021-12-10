const inventoryResolver = {
  Query: {
    inventaryList: async (_, __, { dataSources, userIdToken }) => {
      return await dataSources.inventoryAPI.inventaryList();
    },

    productById:async (_, { id_inventory,code }, { dataSources }) => {
      return dataSources.inventoryAPI.productById(id_inventory,code);
  },

  },
  Mutation: {
    inventorySave: async (_, { infoInventory }, { dataSources }) => {
    
      const inventoryInput = {
        id_inventory: infoInventory.id_inventory,
        name_inventory: infoInventory.name_inventory,
        date_creation: infoInventory.date_creation,
        id_user: infoInventory.id_user,
      };

      return dataSources.inventoryAPI.inventorySave(inventoryInput);
    },

    //___________________________-__________________-_____________________________

    inventoryUpdate: async (_, { infoInventory }, { dataSources }) => {
      const inventoryInput = {
        id_inventory: infoInventory.id_inventory,
        name_inventory: infoInventory.name_inventory,
        date_creation: infoInventory.date_creation,
        id_user: infoInventory.id_user,
      };
      return dataSources.inventoryAPI.inventoryUpdate(inventoryInput);
    },

    inventoryDelete: async (_, { id }, { dataSources }) => {
      return dataSources.inventoryAPI.inventoryDelete(id);
    },
    inventoryfindById: async (_, { id }, { dataSources }) => {
      return dataSources.inventoryAPI.inventoryfindById(id);
    },
    productList: async (_, { id }, { dataSources }) => {
      return dataSources.inventoryAPI.productList(id);
    },

    productDelete :async (_, { id_inventory,code }, { dataSources }) => {
      return dataSources.inventoryAPI.productDelete(id_inventory,code);
    },

    productSave :async (_, { informacion }, { dataSources }) => {
      const ProductInput = {
        id_inventory: informacion.id_inventory,
        productModel: informacion.productModel
      };

      return dataSources.inventoryAPI.productSave(informacion);
    },

    productUpdate :async (_, { informacion }, { dataSources }) => {
      const ProductInput = {
        id_inventory: informacion.id_inventory,
        productModel: informacion.productModel
      };
      
      return dataSources.inventoryAPI.productUpdate(informacion);
    },
  },
};

module.exports = inventoryResolver;
