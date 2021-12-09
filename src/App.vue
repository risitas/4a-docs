<template>
<div id="app" class="app">
  <div class="header">
      <h1> +Inventarios</h1>
      <nav>
        <button v-if="!is_auth" v-on:click="loadLoginApp">Inicio</button>
        <button v-if="!is_auth" v-on:click="loadLoginApp">Registrarse</button>
        <button v-if="!is_auth" v-on:click="loadLoginApp">Iniciar Sesion</button>
      </nav>

  </div>
</div>
    <router-view/>
</template>

<script>
  export default {
    
  }
</script>

<style>
:root {
  --azul-claro: #5c7ad6;
  --azul-oscuro: #2148c0;
  --gris: #6671ae;
  --dark-claro: #3d447a;
  --blanco: #ffffff;
  --fuente-principal: "Montserrat", sans-serif;
}

* {
  box-sizing: border-box;
}


body{
  margin: 0 0 0 0;
  background: url(./assets/fondo_login.svg),  #5c7ad6;
  background-position: center center;
  background-size: cover;


}

.header{
  margin: 0%;
  padding: 0%;
  width: 100%;
  height: 0%;
  min-height: 100px;
  font-size: 4.6rem;
  color: white;
  display: flex;
  justify-content: space-between;
  text-align: center;

}
.header h1{
  width: 20%;
  text-align: center;
}
.header nav{
  height: 100%;
  width: 30%;
}

</style>
