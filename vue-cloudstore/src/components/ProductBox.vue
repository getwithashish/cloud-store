<template>
    <div class="column is-3 product-box">
        <router-link v-bind:to="{path: '/product', query: {id: product.id}}">
        <div class="box">
            <figure class="image">
                <img v-bind:src="product.imageUrl">
            </figure>

            <h3 class="is-size-4">{{ product.prodName }}</h3>
            <p class="is-size-6 has-text-grey">₹{{ product.price }}</p>

            <!-- <router-link v-bind:to="product.image" class="button is-dark mt-4">View details</router-link> -->
        </div>
        </router-link>
    </div>
</template>

<script>
export default {
    name: 'ProductBox',
    props: {
        product: Object
    }
}
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
  .product-box{
      width: 300px;

  }
</style>