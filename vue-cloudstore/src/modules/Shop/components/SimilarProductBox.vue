<template>
  <div class="column is-3 product-box">
    <div class="box">
      <figure class="image">
        <img v-bind:src="product.imageUrl" />
      </figure>

      <h3 class="is-size-4">{{ product.prodName }}</h3>
      <div class="field is-horizontal">
        <p class="is-size-6 has-text-grey">₹{{ product.price }}</p>
      </div>

      <div class="field">
        <div class="control">
          <a class="button is-info is-fullwidth" @click="addSimilarProduct">
            Add to My Products
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ProductBox",
  data() {
    return {};
  },

  mounted() {},

  computed: {
    getStock() {},
  },

  methods: {
    addSimilarProduct() {
      axios
      .post(`/user/shop/product/similar?prodId=${this.product.id}`)
      .then((response) => {
        console.log(response.data)
      })
    },
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