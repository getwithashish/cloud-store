<template>
  <div class="column is-3 product-box">
    <!-- <router-link v-bind:to="{path: '/product', query: {id: product.id}}"> -->
    <button class="delete" @click="removeProduct(product)"></button>
    <div class="box">
      <figure class="image">
        <img v-bind:src="product.imageUrl" />
      </figure>

      <h3 class="is-size-4">{{ product.prodName }}</h3>
      <div class="field is-horizontal">
      <p class="is-size-6 has-text-grey">₹{{ product.price }}</p>
      <p class="is-size-6 has-text-grey ml-6">Stock: {{ stock }}</p>
      </div>

      <div class="field has-addons">
        <div class="control">
          <input class="input" type="text" placeholder="Enter Stock" v-model="stockInput" />
        </div>
        <div class="control">
          <a class="button is-info" @click="updateStock"> Update </a>
        </div>
      </div>

      <!-- <router-link v-bind:to="product.image" class="button is-dark mt-4">View details</router-link> -->
    </div>
    <!-- </router-link> -->
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "ProductBox",
  data(){
      return {
        //   userId: this.$store.state.userId,
          userId: localStorage.getItem("userid"),
          shops: this.product.shops,
          stock: 0,
          stockInput: 0
      }
  },

  mounted() {
      console.log(this.shops)
    //   console.log(this.shops)

      for(var item in this.shops){
          console.log(this.shops[item])
          console.log(this.shops[item].shopId)
          if(this.shops[item].shopId == this.userId){
              this.stock = this.shops[item].stock;
          }
      }
  },

  computed: {
      getStock(){

      }
  },

  methods: {
      updateStock() {
          var stockData = {
              prodId: this.product.id,
              stock: this.stockInput
          }

          axios
          .post("/user/shop/product/stock", stockData)
          .then((response) => {
              console.log(response)
              this.stock = stockData.stock
          })
      },

      removeProduct(item){
        console.log(item.prodName)
        var tmpName = item.prodName;
        var prodData = {
          prodNames: tmpName
        }
        axios
        .delete(`/user/shop/product?productName=${tmpName}`)
        .then((response) => {
          console.log(response)
        })
      }
  },

  props: {
    product: Object,
  },
};
</script>

<style scoped>
.image {
  margin-top: -1.25rem;
  margin-left: -1.25rem;
  margin-right: -1.25rem;
}
.image img {
  width: 256px;
  height: 256px;
}
.product-box {
  width: 300px;
}
</style>