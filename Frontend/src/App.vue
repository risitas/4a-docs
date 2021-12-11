<template>
  <div id="app" class="app">
    <div class="header">
      <h1>+INVENTARIO</h1>
      <nav>
        <button v-if="is_auth" v-on:click="loadHome">Inicio</button>
        <button v-if="is_auth" v-on:click="loadRegister">Cuenta</button>
        <button v-if="is_auth" v-on:click="logOut">Cerrar Sesión</button>
        <button v-if="!is_auth" v-on:click="loadHome">Inicio</button>
        <button v-if="!is_auth" v-on:click="loadSignUp">Registrarse</button>
        <button v-if="!is_auth" v-on:click="loadLogIn">Iniciar Sesión</button>
        <button v-if="!is_auth" v-on:click="loadActiveSesion">Crear Inventario</button>
      </nav>
    </div>
    <div class="main-component">
      <router-view
        v-on:completedLogIn="completedLogIn"
        v-on:completedSignUp="completedSignUp"
        v-on:logOut="logOut"
        v-on:loadActiveSesion="loadActiveSesion"
      >
      </router-view>
    </div>
  </div>
</template>
 
<script>
export default {
  name: "App",
  data: function () {
    return {
      is_auth: false,
    };
  },
  components: {},
  methods: {
    verifyAuth: function () {
      this.is_auth = localStorage.getItem("isAuth") || false;
      if (this.is_auth == false) this.$router.push({ name: "logIn" });
      else this.$router.push({ name: "home" });
    },
    loadLogIn: function () {
      this.$router.push({ name: "logIn" });
    },
    loadSignUp: function () {
      this.$router.push({ name: "signUp" });
    },
    completedLogIn: function (data) {
      localStorage.setItem("isAuth", true);
      localStorage.setItem("username", data.username);
      localStorage.setItem("token_access", data.token_access);
      localStorage.setItem("token_refresh", data.token_refresh);
      alert("Autenticación Exitosa");
      this.verifyAuth();
    },
    completedSignUp: function (data) {
      alert("Registro Exitoso");
      this.completedLogIn(data);
    },
    loadHome: function () {
      this.$router.push({ name: "home" });
    },
    logOut: function () {
      localStorage.clear();
      alert("Sesión Cerrada");
      this.verifyAuth();
    },
    loadRegister: function () {
      this.$router.push({ name: "register" });
    },
    loadActiveSesion: function () {
      this.$router.push({ name: "loadActiveSesion" });
    },
  },
  created: function () {
    this.verifyAuth();
  },
};
</script>
<style>
body {
  margin: 0 0 0 0;
  background-image: url("./assets/fondo.png");
  background-size: 100%;
}

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
  color: #FFFFFF;
  background: #314686;
  border: 1px solid #FFFFFF;

  border-radius: 5px;
  padding: 10px 20px;
}

.header nav button:hover {
  color: #283747;
  background: #FFFFFF;
  border: 1px solid #FFFFFF;
  
}

.main-component {
  height: 75vh;
  margin: 0%;
  padding: 0%;
}
</style>
