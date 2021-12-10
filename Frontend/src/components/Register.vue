<template>
  <div class="signUp_user">
    <div v-if="loaded" class="information">
      <h1>Información de su cuenta</h1>
      <h2>
        Nombre: <span>{{ name }}</span>
      </h2>
      <h2>
        Rol: <span>{{ role }} </span>
      </h2>
      <h2>
        Correo electrónico: <span>{{ email }}</span>
      </h2>
    </div>
  </div>
</template>

<script>
import gql from "graphql-tag";
export default {
  name: "SignUp",
  data: function () {
    return {
      user: {
        username: "",
        password: "",
        name: "",
        email: "",
        balance: 0,
      },
    };
  },
  methods: {
    processSignUp: async function () {
      await this.$apollo
        .mutate({
          mutation: gql`
            mutation($userInput: SignUpInput!) {
              signUpUser(userInput: $userInput) {
                refresh
                access
              }
            }
          `,
          variables: {
            userInput: this.user,
          },
        })
        .then((result) => {
          let dataLogIn = {
            username: this.user.username,
            token_access: result.data.signUpUser.access,
            token_refresh: result.data.signUpUser.refresh,
          };
          this.$emit("completedSignUp", dataLogIn);
        })
        .catch((error) => {
          alert("ERROR: Fallo en el registro.");
        });
    },
  },
};
</script>

<style>
.information {
  margin: 0;
  padding: 0%;
  width: 100%;
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.information h1 {
  font-size: 60px;
  color: #0f1316;
}

.information h2 {
  font-size: 40px;
  color: #283747;
}

.information span {
  color: crimson;
  font-weight: bold;
}

.signUp_user {
  border: none;
}
</style>
