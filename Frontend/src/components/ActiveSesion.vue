<template>
  <div id="app" class="app">
    <div class="header">
      <div class="imagen">
        <img src="https://img.icons8.com/ultraviolet/40/000000/user.png" />
      </div>
      <h1>USUARIO</h1>
      <nav>
        <button v-if="is_auth" v-on:click="loadHome">Mis inventarios</button>
        <button v-if="is_auth" v-on:click="loadRegister">Perfil</button>
        <button v-if="is_auth" v-on:click="logOut">Cerrar Sesión</button>

        <button v-if="is_auth" v-on:click="logOut">Crear Inventario</button>
      </nav>
    </div>
  </div>
</template>
 
<script>
export default {
  name: "ActiveSesion",

  data: function () {
    return {
      username: localStorage.getItem("username") || "none",
    };
  },
};
</script>

<style>


.header {
  margin: 0%;
  padding: 0;
  width: 90%;
  height: 10vh;
  min-height: 100px;
  color: #e5e7e9;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header h1 {
  width: 20%;
  text-align: center;
  color: aliceblue;
  margin-top: 5%;
  margin-left: 10%;
  font-size: 300%;
  text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.header nav {
  height: 100%;
  width: 30%;
  margin-left: 3px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  font-size: 20px;
}

.header nav button {
  color: #ffffff;
  background: #314686;
  border: 1px solid #ffffff;

  border-radius: 5px;
  padding: 10px 20px;
}

.header nav button:hover {
  color: #283747;
  background: #ffffff;
  border: 1px solid #ffffff;
}

.main-component {
  height: 75vh;
  margin: 0%;
  padding: 0%;
}
</style>
