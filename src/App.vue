<template>

  <div id="app" class="app">

  <div class="header">
      <h1> +Inventarios</h1>
      <nav class="botones">        
        <button v-if="!is_auth" v-on:click="loadinicio">Inicio</button>
        <button v-if="!is_auth" v-on:click="loadLoginApp">Registrarse</button>
        <button v-if="!is_auth" v-on:click="loadLoginApp">Iniciar Sesion</button>
      </nav>

  </div>
  <br>

     <table class="agrupar">
    <td><img src="./assets/configuration.png" alt="configuracion" class="imagen"></td>
    <td>
      <div class="parrafo">
        <p>Sistema de gestion de inventarios desarrollado para satisfacer las necesidades de control</p>
        <p>Control y gestión de productos para todos los clientes</p>
      </div>
    </td>

  </table>  

  <div class="main-component">
    <router-view
      v-on:completedInicio="completedInicio"

    >
    </router-view>
  </div>


    

<!--   <div class="main-component">
    <router-view  
        v-on:completedLogIn="completedLogIn"
        v-on:completedSignUp="completedSignUp"
        v-on:logOut="logOut"
      >
    </router-view>
  </div> -->
    

  </div>

</template>


<script>
export default {
  name: 'App',

  computed: {
    is_auth: {
      get: function() {
        return this.$route.meta.requiresAuth;
      },
      set: function() { }
    }
  },

  methods:{

    loadLogIn: function(){
      this.$router.push({name: "logIn"})
    },

    loadSignUp: function(){
      this.$router.push({name: "signUp"})
    },

    completedLogIn: function(data) {
			localStorage.setItem("username", data.username);
			localStorage.setItem("token_access", data.token_access);
			localStorage.setItem("token_refresh", data.token_refresh);
			alert("Autenticación Exitosa");
			this.loadHome();
    },

    completedSignUp: function(data) {
			alert("Registro Exitoso");
			this.completedLogIn(data);
    },

    loadHome: function() {
      this.$router.push({ name: "home" });
    },

    loadAccount: function () {
			this.$router.push({ name: "account" });
		},

    loadTransaction: function(){
      this.$router.push({ name: "transaction" });
    },

    logOut: function () {
			localStorage.clear();
			alert("Sesión Cerrada");
      this.loadLogIn();
		},
  }
}
</script>

<style>

  body{
    margin: 0 0 0 0;
    background-image: url('./assets/fondo.png');
    background-size: 100%;
  }

  .header{
    margin: 0%;
    padding: 0;
    width: 100%;
    height: 10vh; 
    min-height: 100px;
    color:#E5E7E9  ;

    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .header h1{
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

  .header nav button{
    color: #E5E7E9;
    background: #314686;
    border: 1px solid #E5E7E9;

    border-radius: 5px;
    padding: 10px 20px;
  }

  .header nav button:hover{
    color: #283747;
    background: #E5E7E9;
    border: 1px solid #E5E7E9;
  }

  .main-component{
    height: 75vh;
    margin: 0%;
    padding: 0%;
  }



</style>

