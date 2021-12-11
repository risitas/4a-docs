<template>
  <div class="signUp_user">
    <div class="container_signUp_user">
      <h2>Registrarse</h2>

      <form v-on:submit.prevent="processSignUp">
        <input type="text" v-model="userInput.username" placeholder="Username" />
        <br />

        <input type="text" v-model="userInput.name" placeholder="Name" />
        <br />

        <input type="text" v-model="userInput.last_name" placeholder="Lastname" />
        <br />

        <input type="email" v-model="userInput.email" placeholder="Email" />
        <br />

        <input type="text" v-model="userInput.phone" placeholder="Phone" />
        <br />

        <input type="text" v-model="userInput.address" placeholder="Address" />
        <br />

        <input type="password" v-model="userInput.password" placeholder="Password" />
        <br />

        <button type="submit">Registrarse</button>
      </form>
    </div>
  </div>
</template>                
<script>
import gql from "graphql-tag";

export default {
  name: "SignUp",

  data: function () {
    return {
      userInput: {
        username: "",
        name: "",
        last_name: "",
        email: "",
        phone: "",
        address: "",
        password: ""
      },
    };
  },

  methods: {
    processSignUp: async function () {
        console.log(this.userInput);
      await this.$apollo
        .mutate({
            mutation: gql`
                mutation($userInput: SignUpInput) {
                    signUpUser(userInput: $userInput) {
                        refresh
                        access
                    }
                }
            `,
          variables: {
            userInput: this.userInput,
          },
        })
        .then((result) => {
          console.log("Todo Bien");
          console.log(result);
        })
        .catch((error) => {
          console.log("Todo Mal");
          console.log(error.graphQLErrors);
          console.log(error.message);
          console.log(error.networkError);
          console.log(error);
        });
    },
  },
};
</script> 
<style>
.signUp_user {
  margin: 0;
  padding: 0%;
  height: 100%;
  width: 100%;

  display: flex;
  justify-content: center;
  align-items: center;
}

.container_signUp_user {
  border: 3px solid #283747;
  border-radius: 10px;
  width: 25%;
  height: 60%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.signUp_user h2 {
  color: #283747;
}

.signUp_user form {
  width: 70%;
}

.signUp_user input {
  height: 40px;
  width: 100%;

  box-sizing: border-box;
  padding: 10px 20px;
  margin: 5px 0;

  border: 1px solid #283747;
}

.signUp_user button {
  width: 100%;
  height: 40px;

  color: #e5e7e9;
  background: #283747;
  border: 1px solid #e5e7e9;

  border-radius: 5px;
  padding: 10px 25px;
  margin: 5px 0 25px 0;
}

.signUp_user button:hover {
  color: #e5e7e9;
  background: crimson;
  border: 1px solid #283747;
}
</style> 