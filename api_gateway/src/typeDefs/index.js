//Se llama al typedef (esquema) de cada submodulo
const inventoryTypeDefs = require('./inventoryTypeDefs');
const authTypeDefs = require('./auth_type_defs');

//Se unen
const schemasArrays = [authTypeDefs, inventoryTypeDefs];

//Se exportan
module.exports = schemasArrays;