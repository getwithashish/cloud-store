<template>
  <div class="page-log-in">
    <div class="columns">
      <div class="column is-4 is-offset-4">
        <h1 class="title">Edit Product Details</h1>

        <form @submit.prevent="submitForm">
          <div class="field">
            <label>Product Name</label>
            <div class="control">
              <input type="text" class="input" v-model="prodName" />
            </div>
          </div>

          <div class="field">
            <label>Category</label>
            <div class="control">
              <input type="text" class="input" v-model="category" />
            </div>
          </div>

          <div class="field">
            <label>Image</label>
            <div class="control">
              <input type="text" class="input" v-model="image" />
            </div>
          </div>

          <div class="field">
            <label>Main Unit of Measurement</label>
            <div class="control">
              <input type="text" class="input" v-model="mainUnit" />
            </div>
          </div>

          <div class="field">
            <label>Sale Unit of Measurement</label>
            <div class="control">
              <input type="text" class="input" v-model="saleUnit" />
            </div>
          </div>

          <div class="field">
            <label>Weight per unit</label>
            <div class="control">
              <input type="text" class="input" v-model="weight" />
            </div>
          </div>

          <div class="field">
            <label>Price per unit</label>
            <div class="control">
              <input type="text" class="input" v-model="price" />
            </div>
          </div>

          <div class="field">
            <label>Range of Increment</label>
            <div class="control">
              <input type="text" class="input" v-model="increment" />
            </div>
          </div>

          <!-- <div class="field">
            <label>User House Name</label>
            <div class="control">
              <input type="text" class="input" v-model="houseName" />
            </div>
          </div>

          <div class="field">
            <label>User Residing Street</label>
            <div class="control">
              <input type="text" class="input" v-model="streetName" />
            </div>
          </div>

          <div class="field">
            <label>User Residing City</label>
            <div class="control">
              <input type="text" class="input" v-model="cityName" />
            </div>
          </div>

          <div class="field">
            <label>User Residing State</label>
            <div class="control">
              <input type="text" class="input" v-model="stateName" />
            </div>
          </div> -->

          <div class="notification is-danger" v-if="errors.length">
            <p v-for="error in errors" v-bind:key="error">{{ error }}</p>
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

export default {
  name: "AddShopDetails",
  data() {
    return {
      prodName: "",
      category: "",
      image: "",
      mainUnit: "",
      saleUnit: "",
      weight: "",
      price: "",
      increment: "",
      errors: [],
    };
  },
  mounted() {},
  methods: {
    async submitForm() {
      const formData = {
        prodName: this.prodName,
        category: this.category,
        image: this.image,
        mainUnit: this.mainUnit,
        saleUnit: this.saleUnit,
        weight: this.weight,
        price: this.price,
        increment: this.increment,
      };

      await axios
        .post("/user/shop/product/add", formData)
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
          if (error.response) {
            for (const property in error.response.data) {
              this.errors.push(`${property}: ${error.response.data[property]}`);
            }
          } else {
            this.errors.push("Something went wrong. Please try again");

            console.log(JSON.stringify(error));
          }
        });
    },
  },
};
</script>
