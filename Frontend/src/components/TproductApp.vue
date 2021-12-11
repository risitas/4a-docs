<template>
  <div class="main-cnt">
    <div class="menu-cnt">
      <div class="imagen">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          class="h-6 w-6"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2"
          />
        </svg>
      </div>
      <div class="botones-cnt">
        <button class="btn-opciones">Productos</button>
      </div>
    </div>
    <div class="control-cnt">
      <div class="fondo-cnt">
        <div class="opciones-btn">
          <div class="buscador-btn">
            <button v-on:click="searchProducto" class="btn-accion">Buscar</button>
            <input v-on:input="searchProducto" v-model="search" type="text" id="search" name="search" />
          </div>
          <table class="tabla">
            <thead>
              <tr>
                <th>Código</th>
                <th>Nombre</th>
                <th>Marca</th>
                <th>Precio Compra</th>
                <th>Precio Venta</th>
                <th>Descripcion</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(producto, key) in productos" :key="key">
                <td contenteditable="true">{{producto.codigo_producto}}</td>
                <td>{{producto.nombre_producto}}</td>
                <td>{{producto.marca_producto}}</td>
                <td>{{producto.productofk.precio_compra}}</td>
                <td>{{producto.productofk.precio_venta}}</td>
                <td>{{producto.descripcion_producto}}</td>
                <td class="acciones">
                  <button v-on:click="updateProducto(producto.id)" class="btn-editar"><em class="fas fa-pen-square"></em></button>
                  <button v-on:click="deleteProducto(producto.id)" class="btn-eliminar"><em class="fas fa-minus-square"></em></button>
                </td>
              </tr>
              <div class="btn-add btn-accion" v-on:click="showAddProducto">+</div>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <div class="container-form" v-bind:class="{'show-form': form_visible}">
    <div class="bodyformulario">
      <h1>Producto</h1>
      <form action="" v-on:submit.prevent="addProductos">
        <div class="form-group">
          <div class="field">
            <label for="codigo_producto">Codigo: </label>
            <input v-model="producto.codigo_producto" id="codigo_producto" type="text" name="codigo_producto" placeholder="Codigo"/>
          </div>
          <div class="field">
            <label for="nombre_producto">Nombre:  </label>
            <input v-model="producto.nombre_producto" id="nombre_producto" type="text" name="nombre_producto" placeholder="Nombre"/>
          </div>
          <div class="field">
            <label for="marca_producto">Marca:  </label>
            <input v-model="producto.marca_producto" id="marca_producto" type="text" name="marca_producto" placeholder="Marca"/>
          </div>
          <div class="field">
            <label for="precio_compra"> Precio Compra:  </label>
            <input v-model="producto.productofk.precio_compra" id="precio_compra" type="number" name="precio_compra" placeholder="Precio Compra"/>
          </div>
          <div class="field">
            <label for="precio_venta"> Precio Venta:  </label>
            <input v-model="producto.productofk.precio_venta" id="precio_venta" type="number" name="precio_venta" placeholder="Precio Venta"/>
          </div>
          <div class="field">
            <label for="comentario">Descripción:</label>
            <textarea v-model="producto.descripcion_producto" cols="30" rows="2" name="Descripción" id="Descripción"></textarea>
          </div>
        </div>
        <div class="buttons-actions">
          <input class="btn-agregar" type="submit" value="Agregar" title="Agregar"/>
          <button type="button" class="btn-cancelar" v-on:click="hideAddProducto">Cancelar</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Swal from 'sweetalert2';
export default {
  name:"ProductosApp",
  data: function(){
    return{
      productos:[],
      form_visible: false,
      producto: {
        codigo_producto: "",
        nombre_producto: "",
        marca_producto: "",
        descripcion_producto: "",
        productofk: {
          precio_compra: "",
          precio_venta: "",
          fecha_actualizacion: this.getDate()
        }
      },
      update: false,
      id_update: null,
      search: ""
    }
  },
  methods:{
    searchProducto: async function(){
      await this.getProductos();
      if(this.search) {
        let buscador = this.productos.filter(producto => producto.nombre_producto.toLowerCase().includes(this.search.toLowerCase()));
        this.productos = buscador;
      }
    },
    validateSession: function(){
      if(!localStorage.getItem('access') || !localStorage.getItem('refresh') || !localStorage.getItem('auth')){
        Swal.fire({
          title: 'Error',
          text: 'Inicia sesión para acceder a esta sección.',
          icon: 'error',
          confirmButtonText: 'Aceptar'
        });
        this.$router.push('/login');
      }
    },
    getProductos: async function(){
      let endpoint = 'https://inventario-bk.herokuapp.com/productos/';
      let body = {};
      let params = {headers: {}};

      try{
        let response = await axios.get(endpoint, body, params);
        this.productos = response.data;
      }catch(error){
        Swal.fire({
          title: 'Error',
          text: 'No se pudo obtener los productos.',
          icon: 'error',
          confirmButtonText: 'Aceptar'
        });
      }
    },
    addProductos: async function(){
      if(!this.update) {
        let endpoint = 'https://inventario-bk.herokuapp.com/productos/';
        let body = this.producto;
        let params = {headers: {}};
        
        try{
          let response = await axios.post(endpoint, body, params);
          if(response.status == 201){
            Swal.fire({
              title: 'Éxito',
              text: 'Producto agregado.',
              icon: 'success',
              confirmButtonText: 'Aceptar'
            });
            this.hideAddProducto();
            this.resetModel();
            this.getProductos();
          }
        } catch(error){
          console.log(error.request);
          Swal.fire({
            title: 'Error',
            text: 'No se pudo agregar el producto, por favor llena todos los campos.',
            icon: 'error',
            confirmButtonText: 'Aceptar'
          });
        }

      } else {
        let endpoint = `https://inventario-bk.herokuapp.com/producto/${this.id_update}`;
        let body = this.producto;
        let params = {headers: {}};

        this.update = false;
        this.id_update = null;
        this.hideAddProducto();
        this.resetModel();
        return;

        try{
          let response = await axios.put(endpoint, body, params);
          console.log(response);
          return;
          if(response.status == 200){
            Swal.fire({
              title: 'Éxito',
              text: 'Producto actualizado.',
              icon: 'success',
              confirmButtonText: 'Aceptar'
            });

            this.update = false;
            this.hideAddProducto();
            this.resetModel();
            this.getProductos();
          }
        } catch(error){
          console.log(error.request.response);
          Swal.fire({
            title: 'Error',
            text: 'No se pudo actualizar el producto, por favor llena todos los campos.',
            icon: 'error',
            confirmButtonText: 'Aceptar'
          });
        }
      }
    },
    updateProducto: async function(id){
      this.update = true;
      this.id_update = id;
      let endpoint = `https://inventario-bk.herokuapp.com/producto/${id}`;
      try{
        let response = await axios.get(endpoint);
        this.producto = response.data;
        this.showAddProducto();
      } catch(error){
        Swal.fire({
          title: 'Error',
          text: 'No se pudo obtener el producto.',
          icon: 'error',
          confirmButtonText: 'Aceptar'
        });
      }

      let body = this.producto;
      let params = {headers: {}};
      
    },
    deleteProducto: function(id){
      let endpoint = `https://inventario-bk.herokuapp.com/producto/${id}`;
      let body = {};
      let params = {headers: {}};

      try{
        Swal.fire({
          title: '¿Estás seguro de eliminar este producto?',
          text: "¡Si eliminas el producto ya no se podrá recuperar!",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Sí, eliminar',
          cancelButtonText: 'Cancelar'
        }).then(async (result) => {
          if (result.isConfirmed) {
            let response = await axios.delete(endpoint, body, params);
            
            if(response.status == 204){
              Swal.fire({
                title: 'Éxito',
                text: 'Producto eliminado.',
                icon: 'success',
                confirmButtonText: 'Aceptar'
              });
              this.getProductos();
            }
          }
        });
      } catch(error){
        Swal.fire({
          title: 'Error',
          text: 'No se pudo eliminar el producto.',
          icon: 'error',
          confirmButtonText: 'Aceptar'
        });
      }
    },
    showAddProducto: function(){
      this.form_visible = true;
    },
    hideAddProducto: function(){
      this.form_visible = false;
      this.resetModel();
    },
    resetModel: function(){
      this.producto = {
        codigo_producto: "",
        nombre_producto: "",
        marca_producto: "",
        descripcion_producto: "",
        productofk: {
          precio_compra: "",
          precio_venta: "",
          fecha_actualizacion: this.getDate()
        }
      }
    },
    getDate: function(){
      let date = new Date();
      let day = date.getDate();
      let month = date.getMonth() + 1;
      let year = date.getFullYear();
      return `${year}-${month}-${day}`;
    }
  },

  created: async function(){
    await this.validateSession();
    await this.getProductos();
  },
} 



</script>


<style>

body {
  background-color: var(--azul-claro);
  font-family: var(--fuente-principal);
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.container-form {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: none;
}

.bodyformulario{
  margin: 20px;
  padding: 20px;
  border: 2px solid #ffffff; 
  color: var(--blanco);
  background-color: var(--azul-oscuro);
  max-width: 600px;
  width: 100%;
}

.bodyformulario h1{
  text-align: center;
  margin-top: 0;
  margin-bottom: 25px;
}

.show-form{
  display: flex;
}

.form-group {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
}

.form-group .field {
  width: 100%;
}

.form-group .field label {
  font-weight: bold;
  color: var(--blanco);
  display: block;
}

.form-group .field input,
.form-group .field textarea {
  border: 1px solid var(--blanco);
  border-radius: 5px;
  padding: 10px;
  width: 100%;
}

.buttons-actions {
  text-align: center;
  margin-top: 20px;
  height: 100%;
}

.btn-agregar {
  background-color: #13a81f;
  color: var(--blanco);
  border: none;
  border-radius: 5px;
  margin-right: 20px;
  padding: 10px 20px;
  cursor: pointer;
  font-weight: bold;
}

.btn-cancelar {
  background-color: #b51424;
  color: var(--blanco);
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
  font-weight: bold;
}

.main-cnt {
  min-height: 95vh;
  width: 100%;
  border-top: 2px solid var(--blanco);
  border-bottom: 2px solid var(--blanco);

  display: grid;
  grid-template-columns: 20% 80%;
}

.menu-cnt .imagen {
  margin-top: 30px;
  text-align: center;
}

.menu-cnt .imagen svg {
  width: 70px;
  height: 70px;
  color: var(--blanco);
}

.botones-cnt {
  margin-top: 20px;
}

.btn-opciones {
  width: 100%;
  padding: 10px 20px;
  text-align: left;
  font-size: 20px;
  font-family: var(--fuente-principal);
  color: var(--azul-oscuro);
  font-weight: 600;
  border-radius: 0 20px 20px 0;
  border: unset;
  transition: all 0.2s ease;
  margin-bottom: 10px;
}

.btn-opciones:hover,
.btn-accion:hover {
  background-color: var(--azul-oscuro);
  color: var(--blanco);
  cursor: pointer;
}

.control-cnt {
  height: 100%;
  width: 100%;
}

.fondo-cnt {
  background-color: var(--gris);
  height: 100%;
  width: 100%;
}

.opciones-btn {
  padding: 70px 20px 20px 20px;
  height: 100%;
}

.buscador-btn {
  width: 100%;
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
}

.buscador-btn input {
  flex-basis: 100%;
}

.btn-accion {
  width: 200px;
  padding: 7px;
  font-family: var(--fuente-principal);
  text-transform: uppercase;
  border: none;
  color: var(--azul-oscuro);
  font-weight: 600;
}

.acciones-cnt {
  display: flex;
  gap: 30px;
}

.tabla {
  margin-top: 20px;
  width: 100%;
  background-color: var(--dark-claro);
  border-collapse:collapse;
  border-spacing:0;
}

.tabla th {
  padding: 5px 3px;
  color: #ffffff;
  border: 1px solid #ffffff;
}

.tabla td {
  padding: 10px;
  color: #ffffff;
  font-size: 16px;
  font-family: var(--fuente-principal);
  border: 1px solid #ffffff;
}

.btn-add {
  text-align: center;
  font-size: 25px;
  font-weight: 800;
}

.acciones {
  display: flex;
  gap: 10px;
}

.btn-editar {
  background-color: #20d62f;
  color: #fff;
  padding: 7px;
  border: none;
  border-radius: 5px;
}

.btn-eliminar {
  background-color: #ff0000;
  color: #fff;
  padding: 7px;
  border: none;
  border-radius: 5px;
}

@media (max-width: 980px) {
  .opciones-btn {
    padding: 70px 20px 20px 20px;
  }
}
</style>
