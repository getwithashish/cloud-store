<template>
  <div class="page-log-in">
    <div class="columns">
      <div class="column is-4 is-offset-4">
        <h1 class="title">Log in</h1>

        <form @submit.prevent="submitForm">
          <div class="field">
            <label>Email</label>
            <div class="control">
              <input type="text" class="input" v-model="state.email" id="emailLogin" />
              <span v-if="v$.email.$error" class="has-text-danger">
                {{ v$.email.$errors[0].$message }}
              </span>
            </div>
          </div>

          <div class="field">
            <label>Password</label>
            <div class="control">
              <input type="password" class="input" v-model="state.password" id="passLogin" />
              <span v-if="v$.password.$error" class="has-text-danger">
                {{ v$.password.$errors[0].$message }}
              </span>
            </div>
          </div>

          <!-- <div class="notification is-danger" v-if="errors.length">
            <p v-for="error in errors" v-bind:key="error">{{ error }}</p>
          </div> -->

          <div class="field">
            <div class="control">
              <button class="button is-dark" id="submitLogin">Log in</button>
            </div>
          </div>

          <hr />

          Don't have an Account? <router-link to="/signup">Sign Up</router-link>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// import VueJwtDecode from "vue-jwt-decode";

import { toast } from "bulma-toast";

import useValidate from "@vuelidate/core";
import {
  required,
  email,
  sameAs,
  minLength,
  maxLength,
  helpers,
} from "@vuelidate/validators";
import { reactive, computed } from "vue";

export default {
  name: "LogIn",
  // data() {
  //   return {
  //     email: "",
  //     password: "",
  //     errors: [],
  //   };
  // },

  setup() {
    const state = reactive({
      email: "",
      password: "",
    });

    const rules = computed(() => {
      return {
        email: { required, email },
        password: {
          required,
          minLength: minLength(8),
          maxLength: maxLength(131),
          containsPasswordRequirement: helpers.withMessage(
            () =>
              `The password requires an uppercase, lowercase, number and special character`,
            (value) =>
              /(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])/.test(value)
          ),
        },
      };
    });

    const v$ = useValidate(rules, state);

    return {
      state,
      v$,
    };
  },

  mounted() {
    document.title = "Log In | CloudStore";
  },
  methods: {
    async submitForm() {
      this.v$.$validate();

      if (!this.v$.$error) {
        axios.defaults.headers.common["Authorization"] = "";

        localStorage.removeItem("token");

        const formData = {
          email: this.state.email,
          password: this.state.password,
        };

        this.$store.commit("setIsLoading", true);

        await axios
          .post("/user/login", formData)
          .then((response) => {
            // const token = response.data.auth_token

            // toast({
            //   message: "Account created, please log in!",
            //   type: "is-success",
            //   dismissible: true,
            //   pauseOnHover: true,
            //   duration: 2000,
            //   position: "bottom-right",
            // });

            const token = response.data;

            this.$store.commit("setToken", token);

            axios.defaults.headers.common["Authorization"] = "Bearer " + token;

            localStorage.setItem("token", token);

            //   let decoded = VueJwtDecode.decode(token);

            var base64Url = token.split(".")[1];
            var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
            var jsonPayload = decodeURIComponent(
              window
                .atob(base64)
                .split("")
                .map(function(c) {
                  return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
                })
                .join("")
            );


            let decoded = JSON.parse(jsonPayload);
            let decodedRole = decoded.authorities[0].authority;
            let decodedId = decoded.id

            this.$store.commit("setUserRole", decodedRole);
            this.$store.commit("setUserId", decodedId)

            const toPath = this.$route.query.to || "/";

            this.$router.push(toPath);
          })
          .catch((error) => {
            toast({
              message: "Not Logged In, Try again",
              type: "is-success",
              dismissible: true,
              pauseOnHover: true,
              duration: 2000,
              position: "bottom-right",
            });
          });

          this.$store.commit("setIsLoading", false);
      }
    },
  },
};
</script>