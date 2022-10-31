<template>
  <table class="table is-fullwidth is-bordered is-striped is-hoverable">
    <thead>
      <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Document</th>
        <th>Enabled</th>
        <!-- <th>Address</th> -->
        <th>Mobile Number</th>
        <th>Disable Account</th>
        <th>Enable Account</th>
      </tr>
    </thead>

    <tbody>
      <tr v-for="shop in shopsDetails" v-bind:key="shop">
        <td>{{ shop.shopName }}</td>
        <td>{{ shop.email }}</td>
        <td>{{ shop.role }}</td>
        <td> <a v-bind:href="shop.documentUrl" target="_blank">View Document</a> </td>
        <td>{{ shop.enableStatus }}</td>
        <!-- <td>{{ shop.houseName }}</td> -->
        <td>{{ shop.mobile }}</td>
        <td><button class="delete" @click="disableAccount(shop)"></button></td>
        <td>
          <button class="button is-primary" @click="enableAccount(shop)">Enable</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import axios from "axios";

export default {
  name: "ShopsDetails",
  data() {
    return {
      shopsDetails: [],
      errors: [],
    };
  },
  methods: {
    disableAccount(item){
      axios
      .delete(`/admin/shops?deleteModel=${item.email}`)
      .then((response) => {
        console.log(response);
        window.location.reload();
      })
    },
    enableAccount(item){
      axios
      .patch(`/admin/shops?enableModel=${item.email}`)
      .then((response) => {
        console.log(response);
        window.location.reload();
      })
    }
  },
  mounted() {
    console.log("hi");
    axios
      .get("/admin/shops")
      .then((response) => {
        this.shopsDetails = response.data;

        this.$store.commit("setAdminShopDetails", shopsDetails);

        // const toPath = this.$route.query.to || '/cart'

        // this.$router.push(toPath)
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
};
</script>
