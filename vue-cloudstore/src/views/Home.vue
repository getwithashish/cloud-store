<template>
  <div class="home">
    <!-- <section class="hero is-medium is-dark mb-6">
        <div class="hero-body has-text-centered">
            <p class="title mb-6">
                Welcome to Cloud Store
            </p>
            <p class="subtitle">
                The best Store for buying anything
            </p>
        </div>
    </section> -->

    


    <div class="columns is-multiline">
      <div class="column is-12">
          <!-- <h2 class="is-size-2 has-text-centered">Latest products</h2> -->
      </div>

      <ProductBox 
        v-for="product in latestProducts"
        v-bind:key="product.id"
        v-bind:product="product" />
    </div>
  </div>
</template>

<script>
import axios from 'axios'

import ProductBox from '@/components/ProductBox'

import * as locationService from '@/views/location'

export default {
  name: 'Home',
  data() {
    return {
      latestProducts: [],
      address: ""
    }
  },
  components: {
    ProductBox
  },
  mounted() {
    

    // this.getLocationAddress()

    // this.getLatestProductsByPincode()

    this.getLatestProducts()

    

    document.title = 'Home | CloudStore'

    
  },
  methods: {
    async getLatestProducts() {
      this.$store.commit('setIsLoading', true)

      // axios
      //   .get('/products')
      //   .then(response => {
      //     this.latestProducts = response.data
      //   })
      //   .catch(error => {
      //     console.log(error)
      //   })

      // this.$store.commit('setIsLoading', false)


      await this.getLocationAddress()

      // await this.getLatestProductsByPincode()

    },

    getLatestProductsByPincode(pincode) {
      

      // var pincode = await this.$store.state.pincode
      axios
        .get(`/productsbypin?pincode=${pincode}`)
        .then(response => {
          this.latestProducts = response.data
        })
        .catch(error => {
          console.log(error)
        })

      
    },

    async getLocationAddress(){
      this.address = await locationService.currentAddress();
      await this.$store.commit("setCustPincode", this.address)
      console.log("Address: " + this.address)

      this.getLatestProductsByPincode(this.address)
      this.$store.commit('setIsLoading', false)
  }


  },

}
</script>