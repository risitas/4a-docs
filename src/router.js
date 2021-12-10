import gql from "graphql-tag";
import { createRouter, createWebHistory } from "vue-router";
import { ApolloClient, createHttpLink, InMemoryCache } from '@apollo/client/core'

import inicio from './components/inicio.vue'
import LogIn from './components/LogIn.vue'
import SignUp from './components/SignUp.vue'

/* import LoginApp from './components/LoginApp.vue'
import TproductApp from './components/TproductApp.vue' */

const routes = [
    {
        path: '/user/inicio',
        name: "inicio",
        component: inicio,
        meta: { requiresAuth: false }
    },
    {
        path: '/user/logIn',
        name: "logIn",
        component: LogIn,
        meta: { requiresAuth: false }
    },
    {
        path: '/user/signUp',
        name: "signUp",
        component: SignUp,
        meta: { requiresAuth: false }
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

const apolloClient = new ApolloClient({
    link: createHttpLink({ uri: 'https://mision-tic-api-gateway.herokuapp.com/' }),
    cache: new InMemoryCache()
})

async function isAuth() {
    if (localStorage.getItem("token_access") === null || localStorage.getItem("token_refresh") === null) {
        return false;
    }

    try {
        var result = await apolloClient.mutate({
            mutation: gql `
                mutation ($refresh: String!) {
                    refreshToken(refresh: $refresh) {
                        access
                    }
                }
            `,
            variables: {
                refresh: localStorage.getItem("token_refresh"),
            },
        })

        localStorage.setItem("token_access", result.data.refreshToken.access);
        return true;
    } catch {
        localStorage.clear();
        alert("Su sesión expiró, por favor vuelva a iniciar sesión");
        return false;
    }
}

router.beforeEach(async(to, from) => {
    var is_auth = await isAuth();

    if (is_auth == to.meta.requiresAuth) return true
    if (is_auth) return { name: "home" };
    return { name: "logIn" };
})

export default router;