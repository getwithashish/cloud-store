<template>
  <div class="page-log-in">
    <div class="columns">
      <div class="column is-4 is-offset-4">
        <h1 class="title">Add New Category</h1>

        <form @submit.prevent="submitForm">
          <div class="field">
            <label>Category Name</label>
            <div class="control">
              <input type="text" class="input" v-model="prodName" />
            </div>
          </div>

          
        </form>
      </div>
    </div>
  </div>
</template>