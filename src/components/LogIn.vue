<template>

    <div class="logIn_user">
        <div class="container_logIn_user">
            <td><img src="../assets/logologin.png" alt="logologin" class='login'></td>

            <form v-on:submit.prevent="processLogInUser" >
                <input type="text" v-model="user.username" placeholder="Email" class="email">
                <br>
                <input type="password" v-model="user.password" placeholder="Password" class="password">
                <br>
                <button type="submit">Login</button>
            </form>
        </div>

    </div>

</template>


<script>
import gql from "graphql-tag";

export default {
  name: "LogIn",

  data: function() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },

  methods: {
    processLogInUser: async function() {
      await this.$apollo
        .mutate({
          mutation: gql`
            mutation($credentials: CredentialsInput!) {
              logIn(credentials: $credentials) {
                refresh
                access
              }
            }
          `,
          variables: {
            credentials: this.user,
          },
        })
        .then((result) => {
          let dataLogIn = {
            username: this.user.username,
            token_access: result.data.logIn.access,
            token_refresh: result.data.logIn.refresh,
          };

          this.$emit("completedLogIn", dataLogIn);
        })
        .catch((error) => {
          alert("ERROR 401: Credenciales Incorrectas.");
        });
    },
  },
}
</script>


<style>

    .logIn_user{
        margin: 0;
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
    .login{
      border: 2px solid rgba(0, 0, 0, 0.8);
      border-radius: 8%;
      width: 200px;
      margin-bottom: 20%;
    }

    .logIn_user h2{
        color: #dde5ec;

    }

    .logIn_user form{
        width: 70%;
        
    }

    .logIn_user input{
        height: 40px;
        width: 100%;

        box-sizing: border-box;
        padding: 10px 20px;
        margin: 5px 0;
        background-color: transparent;
        border: 1px solid #283747;
        color: #ffffff;
        border-radius: 10px;
        border-color: #ffffff;
        text-align: center;
        
    }


    .logIn_user button{
        width: 100%;
        height: 40px;

        color: #314686;
        background: #ffffff;
        border: 1px solid #E5E7E9;

        border-radius: 5px;
        padding: 10px 25px;
        margin: 5px 0;
    }

    .logIn_user button:hover{
        color: #E5E7E9;
        background: #314686;
        border: 1px solid #283747;
    }

</style>