
/**
 * Todo lo de inventario
 */
const inventoryResolver = require('./inventoryResolver');


/**
 * Todo lo de usuario
 */
const authResolver = require('./auth_resolver');


const lodash = require('lodash');

const resolvers = lodash.merge(inventoryResolver, authResolver);

module.exports = resolvers;