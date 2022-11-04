<template>
  <table class="table is-fullwidth is-bordered is-striped is-hoverable">
    <thead>
      <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Enabled</th>
        <th>House Name</th>
        <th>Mobile Number</th>
        <th>Disable Account</th>
        <th>Enable Account</th>
      </tr>
    </thead>

    <tbody>
      <tr v-for="customer in customersDetails" v-bind:key="customer">
        <td>{{ customer.fullName }}</td>
        <td>{{ customer.email }}</td>
        <td>{{ customer.role }}</td>
        <td>{{ customer.enableStatus }}</td>
        <td>{{ customer.houseName }}</td>
        <td>{{ customer.mobile }}</td>
        <td>
          <button class="delete" @click="disableAccount(customer)"></button>
        </td>
        <td>
          <button class="button is-primary" @click="enableAccount(customer)">Enable</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import axios from "axios";

export default {
  name: "CustomersDetails",
  data() {
    return {
      customersDetails: [],
      errors: [],
    };
  },
  methods: {
    disableAccount(item) {
      axios
        .delete(`/admin/customers?deleteModel=${item.email}`)
        .then((response) => {
          console.log(response);
        });
    },
    enableAccount(item){
      axios
      .patch(`/admin/customers?enableModel=${item.email}`)
      .then((response) => {
        console.log(response);
      })
    }
  },
  mounted() {
    console.log("hi");
    axios
      .get("/admin/customers")
      .then((response) => {
        this.customersDetails = response.data;



  // ------------------What is responseDetails below? ----------------------------

        this.$store.commit("setAdminCustomerDetails", responseDetails);

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