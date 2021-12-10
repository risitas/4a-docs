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
      </nav>
    </div>
    <div class="main-component">
      <router-view
        v-on:completedLogIn="completedLogIn"
        v-on:completedSignUp="completedSignUp"
        v-on:logOut="logOut"
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
  width: 100%;
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
  margin-left: 4%;
  font-size: 400%;
}

.header nav {
  height: 100%;
  width: 30%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  font-size: 20px;
}

.header nav button {
  color: #e5e7e9;
  background: #314686;
  border: 1px solid #e5e7e9;

  border-radius: 5px;
  padding: 10px 20px;
}

.header nav button:hover {
  color: #283747;
  background: #e5e7e9;
  border: 1px solid #e5e7e9;
}

.main-component {
  height: 75vh;
  margin: 0%;
  padding: 0%;
}
</style>
