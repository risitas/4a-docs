<template>
  <div class="logIn_user">
    <div class="container_logIn_user">
      <h2>Iniciar sesión</h2>
      <div class="imagen">
        <img
          src="https://cdn-icons-png.flaticon.com/512/1802/1802965.png"
        />
      </div>
      <form v-on:submit.prevent="processLogInUser">
        <input type="text" v-model="user.username" placeholder="Username" />
        <br />
        <input type="password" v-model="user.password" placeholder="Password" />
        <br />
        <button type="submit">Iniciar Sesion</button>
      </form>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "LogIn",

  data: function () {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },

  methods: {
    processLogInUser: function () {
      axios
        .post("https://apigateway-ms1-2.herokuapp.com/", this.user, {
          headers: {},
        })
        .then((result) => {
          let dataLogIn = {
            username: this.user.username,
            token_access: result.data.access,
            token_refresh: result.data.refresh,
          };

          this.$emit("completedLogIn", dataLogIn);
        })
        .catch((error) => {
          if (error.response.status == "401")
            alert("ERROR 401: Credenciales Incorrectas.");
        });
    },
  },
};
</script>
<style>
.logIn_user {
  margin: 0;
  margin-top: 50px;
  padding: 0%;
  height: 100%;
  width: 100%;
  

  display: flex;
  justify-content: center;
  align-items: center;
}

.container_logIn_user {
  width: 25%;
  height: 60%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.container_logIn_user h2 {
  color: #ffffff;
  margin-bottom: -99px;
}
.logIn_user form {
  width: 70%;
}

.logIn_user input {
  height: 40px;
  width: 100%;

  box-sizing: border-box;
  padding: 10px 20px;
  margin: 5px 0;

  border: 1px solid #283747;
}

.logIn_user button {
  width: 100%;
  height: 40px;

  color: #283747;
  background: #ffffff;
  border: 1px solid #283747;

  border-radius: 5px;
  padding: 10px 25px;
  margin: 5px 0;
  margin-top: 20px;

  font-size: small;
}

.logIn_user button:hover {
  color: #e5e7e9;
  background: #283747;
  border: 1px solid #283747;
}

.imagen img {
  position: absolute;
  width: 176px;
  height: 175px;
  left: 600px;
  top: 135px;
  mix-blend-mode: lighten;
  border: 1px solid #000000;
}
</style>
