<template>
  <aside class="menu left-panel">
    <ul class="menu-list">

      <!-- <li>
        <router-link
          to="/shop"
          v-bind:class="{
            'is-active': 'shop-dashboard' == this.$route.name,
          }"
          >Dashboard</router-link
        >
      </li> -->

      <li>
        <router-link
          to="/shop/profile"
          v-bind:class="{
            'is-active': 'shop-profile' == this.$route.name,
          }"
          >Profile</router-link
        >
      </li>

      <li>
        <router-link
          to=""
          >Products</router-link>
        <ul>
          <li>
            <router-link
              to="/shop/products/add"
              v-bind:class="{
                'is-active': 'shop-product-add' == this.$route.name,
              }"
              >Add Product</router-link
            >
          </li>
          <!-- <li>
            <router-link
              to="/shop/products/edit"
              v-bind:class="{
                'is-active': 'shop-product-edit' == this.$route.name,
              }"
              >Edit Product</router-link
            >
          </li> -->
          <li>
            <router-link
              to="/shop/products/view"
              v-bind:class="{
                'is-active': 'shop-product-view' == this.$route.name,
              }"
              >View Products</router-link
            >
          </li>
          <!-- <li>
            <router-link
              to="/shop/products/delete"
              v-bind:class="{
                'is-active': 'shop-product-delete' == this.$route.name,
              }"
              >Delete Product</router-link
            >
          </li> -->

          <!-- <li>
            <router-link
              to="/shop/category/add"
              v-bind:class="{
                'is-active': 'shop-product-add' == this.$route.name,
              }"
              >Add Category</router-link
            >
          </li> -->

        </ul>
      </li>

      <!-- <li>
        <router-link
          to="/shop/transactions"
          v-bind:class="{
            'is-active': 'shop-transactions' == this.$route.name,
          }"
          >Transactions</router-link
        >
      </li> -->
      <!-- <li>
        <router-link
          to="/shop/log"
          v-bind:class="{
            'is-active': 'shop-log' == this.$route.name,
          }"
          >Log</router-link
        >
      </li> -->
      <!-- <li>
        <router-link
          to="/shop/settings"
          v-bind:class="{
            'is-active': 'shop-settings' == this.$route.name,
          }"
          >Settings</router-link
        >
      </li> -->
      <li><a @click="disableAccount()">Disable Account</a>
      </li>
    </ul>
  </aside>
</template>

<script>
import axios from 'axios';
export default {
  
  methods: {
    disableAccount(){
      axios
      .delete("/user/shop")
      .then((response) => {
        console.log(response)
      })
    }
  }
}
</script>

<style>
.section-contents {
  display: inline-flex;
  padding-block-start: inherit;
}
.left-panel {
  width: fit-content;
  margin-left: 0px;
}
.section-main-contents {
  margin-left: 30px;
}
/* .style-section{
        padding: 0px;
    } */
</style>
