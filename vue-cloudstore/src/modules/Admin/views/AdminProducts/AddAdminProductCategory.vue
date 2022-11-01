<template>
  <div class="page-log-in">
    <div class="columns">
      <div class="column is-4 is-offset-4">
        <h1 class="title">Add New Category</h1>

        <form @submit.prevent="submitCategory">
          <div class="field">
            <label>Category Name</label>
            <div class="control">
              <input type="text" class="input" v-model="categoryName" />
            </div>
          </div>

          <div class="field">
            <div class="control">
              <button class="button is-dark">Submit</button>
            </div>
          </div>

        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
    data(){
        return{
            categoryName: ""
        }
    },
    methods: {
        async submitCategory(){
            axios
            .post(`/admin/product/category?category=${this.categoryName}`)
            .then((response) => {
                console.log(response);
                alert("Successfully added the category")
            })
        }
    }
}
</script>
