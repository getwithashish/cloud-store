<template>
  <div class="page-log-in">
    <div class="columns">
      <div class="column is-4 is-offset-4">
        <h1 class="title">Add New Product</h1>

        <div class="modal" v-bind:class="{ 'is-active': modalActive }">
          <div class="modal-background"></div>
          <div class="modal-card">
            <header class="modal-card-head">
              <p class="modal-card-title">Similar Products</p>
              <button
                class="delete"
                aria-label="close"
                @click="hideSimilarProducts"
              ></button>
            </header>

            <section class="modal-card-body">
              <div class="columns is-multiline">
                <SimilarProductBox
                  v-for="product in similarProducts"
                  v-bind:key="product.id"
                  v-bind:product="product"
                />
              </div>
            </section>

            <!-- <footer class="modal-card-foot">
              <button class="button is-success">Save changes</button>
              <button class="button">Cancel</button>
            </footer> -->
          </div>
        </div>

        <form @submit.prevent="submitForm">
          <label>Product Name</label>
          <div class="field has-addons">
            <div class="control is-expanded">
              <input type="text" class="input" v-model="prodName" />
            </div>
            <div class="control">
              <a class="button is-info" @click="showSimilarProducts"> Check </a>
            </div>
          </div>

          <!-- <div class="field">
            <label>Category</label>
            <div class="control">
              <input type="text" class="input" v-model="category" />
            </div>
          </div> -->

          <div class="field">
            <label>Category</label>
            <div class="control">
              <div class="select">
                <select v-model="category">
                  <!-- <option selected>None</option> -->
                  <option v-for="item in categories" v-bind:key="item.id">
                    {{ item.category }}
                  </option>
                </select>
              </div>
            </div>
          </div>

          <div class="field">
            <label>Image</label>
            <!-- <div class="control">
              <input type="text" class="input" v-model="image" />
            </div> -->
            <div class="file">
              <label class="file-label">
                <input
                  class="file-input"
                  type="file"
                  name="resume"
                  @change="imageFileSelect"
                />
                <span class="file-cta">
                  <span class="file-icon">
                    <i class="fas fa-upload"></i>
                  </span>
                  <span class="file-label"> Upload Product Picture </span>
                </span>
              </label>
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
            <label>Stock</label>
            <div class="control">
              <input type="text" class="input" v-model="stock" />
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
import SimilarProductBox from "@/modules/Shop/components/SimilarProductBox.vue";

export default {
  name: "AddShopDetails",
  data() {
    return {
      prodName: "",
      category: "",
      imageUrl: "",
      stock: "",
      // pincode: "",
      mainUnit: "",
      saleUnit: "",
      weight: "",
      price: "",
      increment: "",

      productImage: "",
      errors: [],
      categories: [],

      modalActive: false,
      similarProducts: [],
    };
  },
  mounted() {
    this.getCategories();
  },

  components: {
    SimilarProductBox,
  },

  methods: {
    showSimilarProducts() {
      this.modalActive = true;
      axios
        .get(`/user/shop/product/similar?prodName=${this.prodName}`)
        .then((response) => {
          this.similarProducts = response.data;
        });
    },
    hideSimilarProducts() {
      this.modalActive = false;
    },

    getCategories() {
      axios.get("/user/shop/product/category").then((response) => {
        console.log(response);
        var tempData = response.data;
        // console.log(tempData[0].category)
        var responseData = response.data;
        for (let item in responseData) {
          console.log(responseData[item].category);
          this.categories.push(responseData[item]);
        }
        // console.log(this.categories)
      });
    },

    imageFileSelect(event) {
      this.$store.commit("setIsLoading", true);
      console.log(event);
      var imageFile = event.target.files[0];
      this.createBase64Image(imageFile);
    },

    createBase64Image(fileObject) {
      var reader = new FileReader();
      reader.onload = (e) => {
        var imageFileData = e.target.result;
        this.productImage = imageFileData.slice(imageFileData.indexOf(",") + 1);
        console.log(this.productImage);
        this.uploadImage();
      };
      reader.readAsDataURL(fileObject);
    },

    async uploadImage() {
      var formData = new FormData();
      formData.append("image", this.productImage);

      const client = axios.create({
        transformRequest: [
          (data, headers) => {
            // add required "Content-Type" whenever body is defined
            delete headers.common.Authorization;
            return data;
          },
        ],
      });

      await client
        .post(
          "https://api.imgbb.com/1/upload?key=0f6650dbe5d582897945e5dd899204bd",
          formData
        )
        .then((response) => {
          console.log(response);
          var imageData = response.data.data;
          this.imageUrl = imageData.url;
        });
      this.$store.commit("setIsLoading", false);
    },

    async submitForm() {
      this.$store.commit("setIsLoading", true);
      const formData = {
        prodName: this.prodName,
        category: this.category,
        imageUrl: this.imageUrl,
        mainUnit: this.mainUnit,
        saleUnit: this.saleUnit,
        weight: this.weight,
        price: this.price,
        increment: this.increment,
        stock: this.stock,
      };

      await axios
        .post("/user/shop/product", formData)
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
            // this.errors.push("Something went wrong. Please try again");

            console.log(JSON.stringify(error));
          }
          this.$store.commit("setIsLoading", false);
        });
    },
  },
};
</script>
