<template>
  <div class="page-product">
    <div class="columns is-multiline">
      <div class="column is-9">
        <figure class="image mb-6">
          <img
            class="product-image"
            style="width: 500px; height: 500px"
            v-bind:src="product.imageUrl"
          />
        </figure>

        <h1 class="title">{{ product.prodName }}</h1>

        <p>{{ product.category }}</p>
      </div>

      <div class="column is-3">
        <h2 class="subtitle">Information</h2>

        <p><strong>Price: </strong>₹{{ product.price }}</p>
        <br />
        <p><strong>Stock: </strong>{{ stock }}</p>

        <div class="field has-addons mt-6">
          <div class="control">
            <input type="number" class="input" min="1" v-bind:max="stock" v-model="quantity" />
          </div>

          <div class="control">
            <a class="button is-dark" @click="addToCart()">Add to cart</a>
          </div>
        </div>

        <div class="select is-normal">
          <select>
            <option>Select Shop</option>
            <option 
            v-for="item in shops"
            v-bind:key="item.shopId"
            @click="stockUpdate(item)"
            >{{ item.shopName }}</option>
          </select>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import axios from "axios";
import { toast } from "bulma-toast";

export default {
  name: "Product",
  data() {
    return {
      product: {},
      quantity: 1,
      prodId: this.$route.query.id,

      stock: "",
      currShopId: "",
      currProdId: "",

      shopList: [],
      shopIds: [],
      shops: [],
    };
  },
  mounted() {
    this.getProduct();
    // this.getShops();
  },
  methods: {

      stockUpdate(item){
          var tempProduct = this.$store.state.selectedProduct;
          // var tempProduct = JSON.parse(localStorage.getItem('product'))
          this.currProdId = tempProduct.id
          this.currShopId = item.id
          var tempShops = tempProduct.shops
          for(var tmp in tempShops){
              console.log(tempShops[tmp])
              console.log(item.stock)
              if(tempShops[tmp].shopId == item.id){
                  this.stock = tempShops[tmp].stock
              }
          }
          console.log(this.stock)
      },

    async getProduct() {
      this.$store.commit("setIsLoading", true);

      // const category_slug = this.$route.params.category_slug
      // const product_slug = this.$route.params.product_slug
      await axios
        .get(`/product?prodId=${this.prodId}`)
        .then((response) => {
          this.product = response.data;
          this.$store.commit("setSelectedProduct", this.product)
          // localStorage.setItem('product', JSON.stringify(this.product))
          this.shopList = response.data.shops;

          for (var item in this.shopList) {
              this.shopIds.push(this.shopList[item].shopId)
          }
          this.$store.commit("setShopIdsForProduct", this.shopIds)
          // localStorage.setItem('shopIds', JSON.stringify(this.shopIds))

          document.title = this.product.prodName + " | CloudStore";
        })
        .catch((error) => {
          console.log(error);
        });

      this.$store.commit("setIsLoading", false);

      console.log(this.product)

      await this.getShops();
    },

    async getShops() {
        var tempIds = this.$store.state.shopIdsForProduct
        // var tempIds = JSON.parse(localStorage.getItem('shopIds'))
        // localStorage.removeItem('shopIds')
        console.log(tempIds)
      var sendData = {
        shopIds: tempIds,
      };
      axios.put("/user/shops", sendData)
      .then((response) => {
        this.shops = response.data;
        console.log(this.shops)
      });

    },

    addToCart() {
      if (isNaN(this.quantity) || this.quantity < 1) {
        this.quantity = 1;
      }
      if(this.quantity > this.stock){
            this.quantity = 1
            return
        }
        this.stock = this.stock - this.quantity

      const item = {
        product: this.product,
        quantity: this.quantity,
        currentShopId: this.currShopId
      };

      this.$store.commit("addToCart", item);

      toast({
        message: "The product was added to the cart",
        type: "is-success",
        dismissible: true,
        pauseOnHover: true,
        duration: 2000,
        position: "bottom-right",
      });
    },
  },
};
</script>