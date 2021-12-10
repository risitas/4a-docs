const inventoryResolver = {
  Query: {
    inventaryList: async (_, __, { dataSources, userIdToken }) => {
      return await dataSources.inventoryAPI.inventaryList();
    },
  },
  Mutation: {
    inventorySave: async (_, { infoInventory }, { dataSources }) => {
      //  return JSON.stringify(infoInventory);
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

    productById: async (_, { datos }, { dataSources }) => {
    
        const ObjetoBuscarInput = {
        id_inventory: datos.id_inventory,
        code: datos.code,
     
      };
     
      return dataSources.inventoryAPI.productById(ObjetoBuscarInput);
    },
  },
};

module.exports = inventoryResolver;
