<template>
  <div class="page-log-in">
    <div class="columns">
      <div class="column is-4 is-offset-4">
        <h1 class="title">Add New Product</h1>

        <form @submit.prevent="validateForm">
          <div class="field">
            <label>Product Name</label>
            <div class="control">
              <input type="text" class="input" v-model="state.prodName" />
              <span v-if="v$.prodName.$error" class="has-text-danger">
                  {{ v$.prodName.$errors[0].$message }}
                </span>
            </div>
          </div>

          <div class="field">
            <label>Category</label>
            <div class="control">
              <input type="text" class="input" v-model="state.category" />
              <span v-if="v$.category.$error" class="has-text-danger">
                  {{ v$.category.$errors[0].$message }}
                </span>
            </div>
          </div>

          <div class="field">
            <label>Image</label>
            <div class="control">
              <input type="text" class="input" v-model="state.image" />
              <!-- <span v-if="v$.image.$error" class="has-text-danger">
                  {{ v$.image.$errors[0].$message }}
                </span> -->
            </div>
          </div>

          <div class="field">
            <label>Main Unit of Measurement</label>
            <div class="control">
              <input type="text" class="input" v-model="state.mainUnit" />
              <span v-if="v$.mainUnit.$error" class="has-text-danger">
                  {{ v$.mainUnit.$errors[0].$message }}
                </span>
            </div>
          </div>

          <div class="field">
            <label>Sale Unit of Measurement</label>
            <div class="control">
              <input type="text" class="input" v-model="state.saleUnit" />
              <span v-if="v$.saleUnit.$error" class="has-text-danger">
                  {{ v$.saleUnit.$errors[0].$message }}
                </span>
            </div>
          </div>

          <div class="field">
            <label>Weight per unit</label>
            <div class="control">
              <input type="text" class="input" v-model="state.weight" />
              <span v-if="v$.weight.$error" class="has-text-danger">
                  {{ v$.weight.$errors[0].$message }}
                </span>
            </div>
          </div>

          <div class="field">
            <label>Price per unit</label>
            <div class="control">
              <input type="text" class="input" v-model="state.price" />
              <span v-if="v$.price.$error" class="has-text-danger">
                  {{ v$.price.$errors[0].$message }}
                </span>
            </div>
          </div>

          <div class="field">
            <label>Range of Increment</label>
            <div class="control">
              <input type="text" class="input" v-model="state.increment" />
              <span v-if="v$.increment.$error" class="has-text-danger">
                  {{ v$.increment.$errors[0].$message }}
                </span>
            </div>
          </div>

          <div class="field">
            <div class="control">
              <button class="button is-dark">Submit</button>
            </div>
          </div>

          <hr />
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

import { toast } from "bulma-toast";
import useVuelidate from "@vuelidate/core";
import {
  required,
  numeric
  
} from "@vuelidate/validators";
import { reactive, computed } from "vue";

export default {
  name: "AddShopDetails",
  setup() {
    const state = reactive({
      prodName: "",
      category: "",
      image: "",
      mainUnit: "",
      saleUnit: "",
      weight: "",
      price: "",
      increment: ""
    });

    const rules = computed(() => {
      return {
        prodName: { required },
        category: { required },
        mainUnit: { required },
        saleUnit: { required },
        weight: { required, numeric },
        price: { required, numeric },
        increment: { required, numeric },
        
      };
    });

    const v$ = useVuelidate(rules, state);

    return {
      state,
      v$,
    };
  },
  mounted() {},
  methods: {
    validateForm() {
      this.v$.$validate();
      if(!this.v$.$error){
        this.submitForm()
      }
    },
    async submitForm() {
      const formData = {
        prodName: this.state.prodName,
        category: this.state.category,
        image: this.state.image,
        mainUnit: this.state.mainUnit,
        saleUnit: this.state.saleUnit,
        weight: this.state.weight,
        price: this.state.price,
        increment: this.state.increment,
      };

      await axios
        .post("/admin/product", formData)
        .then((response) => {
          toast({
            message: "New product successfully added",
            type: "is-success",
            dismissible: true,
            pauseOnHover: true,
            duration: 2000,
            position: "bottom-right",
          });

          //   const toPath = this.$route.query.to || "/cart";

          //   this.$router.push(toPath);
        })
        .catch((error) => {
          toast({
              message: "Product not added, Try again",
              type: "is-success",
              dismissible: true,
              pauseOnHover: true,
              duration: 2000,
              position: "bottom-right",
            });
        });
    },
  },
};
</script>
