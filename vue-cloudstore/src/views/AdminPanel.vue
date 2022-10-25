<template>
  <div class="columns is-centered">
    <div class="column is-narrow">
      <router-link to="/admin/view/customers">
        <div
          class="box has-background-primary is-clickable"
          style="width: 300px; height: 300px;"
        >
          <p class="title is-5">View Customer Details</p>
          <p class="subtitle"></p>
        </div>
      </router-link>
    </div>

    <div class="column is-narrow">
      <router-link to="/admin/add/customers">
        <div
          class="box has-background-primary is-clickable"
          style="width: 300px; height: 300px;"
        >
          <p class="title is-5">Add New Customer</p>
          <p class="subtitle"></p>
        </div>
      </router-link>
    </div>

    <div class="column is-narrow">
      <router-link to="/admin/edit/customers">
        <div
          class="box has-background-primary is-clickable"
          style="width: 300px; height: 300px;"
        >
          <p class="title is-5">Edit Customer Details</p>
          <p class="subtitle"></p>
        </div>
      </router-link>
    </div>

    <div class="column is-narrow">
      <router-link to="/admin/disable/customers">
        <div
          class="box has-background-primary is-clickable"
          style="width: 300px; height: 300px;"
        >
          <p class="title is-5">Disable Customer Details</p>
          <p class="subtitle"></p>
        </div>
      </router-link>
    </div>
    <div class="column is-narrow">
      <router-link to="/admin/enable/customers">
        <div
          class="box has-background-primary is-clickable"
          style="width: 300px; height: 300px;"
        >
          <p class="title is-5">Enable Customer Details</p>
          <p class="subtitle"> </p>
        </div>
      </router-link>
    </div>
  </div>

  <div class="columns is-centered">
    <div class="column is-narrow">
      <router-link to="/admin/view/shops">
        <div
          class="box has-background-primary is-clickable"
          style="width: 300px; height: 300px;"
        >
          <p class="title is-5">View Shop Details</p>
          <p class="subtitle"> </p>
        </div>
      </router-link>
    </div>

    <div class="column is-narrow">
      <router-link to="/admin/add/shops">
        <div
          class="box has-background-primary is-clickable"
          style="width: 300px; height: 300px;"
        >
          <p class="title is-5">Add Shop</p>
          <p class="subtitle"> </p>
        </div>
      </router-link>
    </div>

    <div class="column is-narrow">
      <router-link to="/admin/edit/shops">
        <div
          class="box has-background-primary is-clickable"
          style="width: 300px; height: 300px;"
        >
          <p class="title is-5">Edit Shop Details</p>
          <p class="subtitle"> </p>
        </div>
      </router-link>
    </div>
    <div class="column is-narrow">
      <router-link to="/admin/disable/shops">
        <div
          class="box has-background-primary is-clickable"
          style="width: 300px; height: 300px;"
        >
          <p class="title is-5">Disable Shop Details</p>
          <p class="subtitle"> </p>
        </div>
      </router-link>
    </div>
    <div class="column is-narrow">
      <router-link to="/admin/enable/shops">
        <div
          class="box has-background-primary is-clickable"
          style="width: 300px; height: 300px;"
        >
          <p class="title is-5">Enable Shop Details</p>
          <p class="subtitle"> </p>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AdminPanel",

  mounted() {
    document.title = "Admin Panel | CloudStore";
  },
};
</script>