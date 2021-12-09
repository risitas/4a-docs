<template>
  <div class="login">
    <div class="contenedor-login">
      <img class="logoinventario" src="../assets/logologin.png" alt="logoinventario">
      <form v-on:submit.prevent="validarCredenciales" accion="#" >
        <div class="grupoinput">
          <img src="../assets/usericono.svg" alt="logouser">
          <input v-model="credenciales.email" type="email" name="email" id="email" placeholder="email">
        </div>
        <div class="grupoinput">
          <img src="../assets/logopassword.svg" alt="logopassword">
          <input v-model="credenciales.password" type="password" name="password" id="password" placeholder="password">
        </div>
        <input type="submit" class="btn-accion btnlogin" value="Login">
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Swal from 'sweetalert2';
export default {
  name:"LoginApp",
  data: function(){
    return {
      credenciales: {
        email:"",
        password:""
      }
    }
  },
  
  methods: {
    validarCredenciales: async function() {
      if(!this.credenciales.email || !this.credenciales.password){
        Swal.fire({
          icon: 'error',
          title: 'Error al iniciar sesion',
          text: 'El email y la contraseña son requeridos!',
        })
        return;
      }

      // colocar el link del despliege de la app BE de heroku
      try {
        let endpoint="https://inventario-bk.herokuapp.com/inicio_sesion/";
        let body = this.credenciales;
        let params={headers:{}};

        let response = await axios.post(endpoint, body, params);
        
        if(response.status == 200){
          localStorage.setItem("access", response.data.access);
          localStorage.setItem("refresh", response.data.refresh);
          localStorage.setItem("auth", true);
          this.$router.push("/productos");
        }
      } catch (error) {
        console.log(error.request);
        Swal.fire({
          icon: 'error',
          title: 'Error al iniciar sesion',
          text: 'El email o la contraseña son requeridos!',
        });
        return;
      }
      
      
    }

  }
}
</script>


<style>

  
</style>
