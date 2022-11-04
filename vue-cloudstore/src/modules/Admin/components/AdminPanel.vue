<template>
  <aside class="menu left-panel">
    <ul class="menu-list">
      <!-- <li>
        <router-link
          to="/admin"
          v-bind:class="{
            'is-active': 'admin-dashboard' == this.$route.name,
          }"
          >Dashboard</router-link
        >
      </li> -->
      <li>
        <router-link
          to="/admin/profile"
          v-bind:class="{
            'is-active': 'admin-profile' == this.$route.name,
          }"
          >Profile</router-link
        >
      </li>
    </ul>

    <p class="menu-label">Products</p>
    <ul class="menu-list">
      <!-- What about adding a functionality to search for shops having a particular product -->
      <li>
        <router-link
          to="/admin/products/view"
          v-bind:class="{
            'is-active': 'admin-product-view' == this.$route.name,
          }"
          >Search Product</router-link
        >
      </li>
      <!-- <li><a v-bind:class="{'is-active': ('shop-view' == this.$route.name)}">Search for all Shops</a></li> -->
      <!-- <li>
        <router-link
          to="/admin/products/add"
          v-bind:class="{
            'is-active': 'admin-product-add' == this.$route.name,
          }"
          >Add Product</router-link
        >
      </li> -->
      <!-- <li>
        <router-link
          to="/admin/products/edit"
          v-bind:class="{
            'is-active': 'admin-product-edit' == this.$route.name,
          }"
          >Edit Product</router-link
        >
      </li> -->
      <!-- <li>
        <router-link
          to="/admin/products/disable"
          v-bind:class="{
            'is-active': 'admin-product-disable' == this.$route.name,
          }"
          >Disable Product</router-link
        >
      </li> -->
      <!-- <li>
        <router-link
          to="/admin/products/enable"
          v-bind:class="{
            'is-active': 'admin-product-enable' == this.$route.name,
          }"
          >Enable Product</router-link
        >
      </li> -->
      <li>
        <router-link
          to="/admin/products/category"
          v-bind:class="{
            'is-active': 'shop-product-category-add' == this.$route.name,
          }"
          >Add Category</router-link
        >
      </li>
      <!-- <li><a v-bind:class="{'is-active': ('shop-view' == this.$route.name)}">View Product Log</a></li> -->
    </ul>

    <p class="menu-label">Shops</p>
    <ul class="menu-list">
      <li>
        <router-link
          to="/admin/shops/view"
          v-bind:class="{
            'is-active': 'admin-shop-view' == this.$route.name,
          }"
          >Search Shop</router-link
        >
      </li>
      <li>
        <router-link
          to="/admin/shops/add"
          v-bind:class="{
            'is-active': 'admin-shop-add' == this.$route.name,
          }"
          >Add Shop</router-link
        >
      </li>
      <!-- <li>
        <router-link
          to="/admin/shops/edit"
          v-bind:class="{
            'is-active': 'admin-shop-edit' == this.$route.name,
          }"
          >Edit Shop</router-link
        >
      </li> -->
      <!-- <li>
        <router-link
          to="/admin/shops/disable"
          v-bind:class="{
            'is-active': 'admin-shop-disable' == this.$route.name,
          }"
          >Disable Shop</router-link
        >
      </li> -->
      <!-- <li>
        <router-link
          to="/admin/shops/enable"
          v-bind:class="{
            'is-active': 'admin-shop-enable' == this.$route.name,
          }"
          >Enable Shop</router-link
        >
      </li> -->
    </ul>

    <p class="menu-label">Customers</p>
    <ul class="menu-list">
      <li>
        <router-link
          to="/admin/customers/view"
          v-bind:class="{
            'is-active': 'admin-customer-view' == this.$route.name,
          }"
          >Search Customer</router-link
        >
      </li>
      <li>
        <router-link
          to="/admin/customers/add"
          v-bind:class="{
            'is-active': 'admin-customer-add' == this.$route.name,
          }"
          >Add Customer</router-link
        >
      </li>
      <!-- <li>
        <router-link
          to="/admin/customers/edit"
          v-bind:class="{
            'is-active': 'admin-customer-edit' == this.$route.name,
          }"
          >Edit Customer</router-link
        >
      </li> -->
      <!-- <li>
        <router-link
          to="/admin/customers/disable"
          v-bind:class="{
            'is-active': 'admin-customer-disable' == this.$route.name,
          }"
          >Disable Customer</router-link
        >
      </li> -->
      <!-- <li>
        <router-link
          to="/admin/customers/enable"
          v-bind:class="{
            'is-active': 'admin-customer-enable' == this.$route.name,
          }"
          >Enable Customer</router-link
        >
      </li> -->
    </ul>

    <!-- <p class="menu-label">Transacti  ons</p>
    <ul class="menu-list">
      <li><a>Search Transaction</a></li>
    </ul> -->

    <!-- <p class="menu-label">Administration</p>
    <ul class="menu-list">
      <li><a>Team Settings</a></li>
      <li>
        <a class="is-active">Manage Your Team</a>
        <ul >
          <li><a>Members</a></li>
          <li><a>Plugins</a></li>
          <li><a>Add a member</a></li>
        </ul>
      </li>

      <li><a>Invitations</a></li>
      <li><a>Cloud Storage Environment Settings</a></li>
      <li><a>Authentication</a></li>
    </ul>
    <p class="menu-label">Transactions</p>
    <ul class="menu-list">
      <li><a>Payments</a></li>
      <li><a>Transfers</a></li>
      <li><a>Balance</a></li>
    </ul> -->
  </aside>
</template>

<style>

.left-panel {
  width: fit-content;
  margin-left: 0px;
}

/* .style-section{
        padding: 0px;
    } */
</style>
