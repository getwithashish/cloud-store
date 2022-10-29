<template>
  <div class="page-sign-up">
    <div class="columns">
      <div class="column is-4 is-offset-4">
        <h1 class="title">Add New Shop</h1>

        <form @submit.prevent="submitForm">
          <div class="field">
            <label class="is-size-5">Full Name</label>
            <div class="control">
              <input type="text" class="input" v-model="state.fullName" />
              <span v-if="v$.fullName.$error" class="has-text-danger">
                {{ v$.fullName.$errors[0].$message }}
              </span>
            </div>
          </div>

          <div class="field">
            <label class="is-size-5">Email</label>
            <div class="control">
              <input type="text" class="input" v-model="state.email" />
              <span v-if="v$.email.$error" class="has-text-danger">
                {{ v$.email.$errors[0].$message }}
              </span>
            </div>
          </div>

          <div class="field">
            <label class="is-size-5">Password</label>
            <div class="control">
              <input type="password" class="input" v-model="state.password" />
              <span v-if="v$.password.$error" class="has-text-danger">
                {{ v$.password.$errors[0].$message }}
              </span>
            </div>
          </div>

          <div class="field">
            <label class="is-size-5">Confirm password</label>
            <div class="control">
              <input type="password" class="input" v-model="state.password2" />
              <span v-if="v$.password2.$error" class="has-text-danger">
                {{ v$.password2.$errors[0].$message }}
              </span>
            </div>
          </div>

          <div class="field">
            <label class="is-size-5">Role</label>
            <div class="control">
              <div class="select">
                <select class="is-hovered" v-model="state.role">
                  <!-- <option selected value="CUSTOMER">Customer</option> -->
                  <option value="SHOP">Shop</option>
                </select>
                <span v-if="v$.role.$error" class="has-text-danger">
                  {{ v$.role.$errors[0].$message }}
                </span>
              </div>
            </div>
          </div>

          <!-- <div class="notification is-danger" v-if="errors.length">
            <p v-for="error in errors" v-bind:key="error">{{ error }}</p>
          </div> -->

          <div class="field mt-6">
            <div class="control">
              <button class="button is-dark">Add</button>
            </div>
          </div>

          <hr />

          <!-- Already have an Account? <router-link to="/login">Log in</router-link> -->
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { toast } from "bulma-toast";
// import { toast } from "@import 'bulma/css/bulma.css'"

import useVuelidate from "@vuelidate/core";
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
  name: "SignUp",


  
  setup() {
    const state = reactive({
      fullName: "",
      email: "",
      password: "",
      password2: "",
      role: "",
    });

    const rules = computed(() => {
      return {
        fullName: { required },
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
        password2: { required, sameAs: sameAs(state.password) },
        role: { required },
      };
    });

    const v$ = useVuelidate(rules, state);

    return {
      state,
      v$,
    };
  },

  // data() {
  //   return {
  //     v$: useValidate(),
  //     fullName: "",
  //     email: "",
  //     password: "",
  //     password2: "",
  //     role: "",
  //     errors: [],
  //   };
  // },
  // validations() {
  //   return {
  //     fullName: { required },
  //     email: { required },
  //     password: "",
  //     password2: "",
  //     role: "",
  //   };
  // },
  mounted() {
    document.title = "Sign Up | CloudStore";
  },
  methods: {
    submitForm() {
      this.v$.$validate();

      // this.errors = [];

      // if (this.username === "") {
      //   this.errors.push("The username is missing");
      // }

      // if (this.password === "") {
      //   this.errors.push("The password is too short");
      // }

      // if (this.password !== this.password2) {
      //   this.errors.push("The passwords doesn't match");
      // }

      if (!this.v$.$error) {
        const formData = {
          // fullName: this.fullName,
          // email: this.email,
          // password: this.password,
          // role: this.role,
          fullName: this.state.fullName,
          email: this.state.email,
          password: this.state.password,
          role: this.state.role,
        };

        axios
          .post("/user/register", formData)
          .then((response) => {
            toast({
              message: "Account created, please log in!",
              type: "is-success",
              dismissible: true,
              pauseOnHover: true,
              duration: 2000,
              position: "bottom-right",
            });

            // this.$router.push("/login");
          })
          .catch((error) => {
            toast({
              message: "Account not created, Try again",
              type: "is-success",
              dismissible: true,
              pauseOnHover: true,
              duration: 2000,
              position: "bottom-right",
            });
          });
      }
    },
  },
};
</script>
