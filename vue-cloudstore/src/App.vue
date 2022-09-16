<template>
  <div id="wrapper">
    <nav class="navbar is-dark">
      <div class="navbar-brand">
        <router-link to="/" class="navbar-item"
          ><strong>Cloud Store</strong></router-link
        >

        <a
          class="navbar-burger"
          aria-label="menu"
          aria-expanded="false"
          data-target="navbar-menu"
          @click="showMobileMenu = !showMobileMenu"
        >
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
        </a>
      </div>

      <div
        class="navbar-menu"
        id="navbar-menu"
        v-bind:class="{ 'is-active': showMobileMenu }"
      >
        <div class="navbar-end mx-auto">
          <div class="navbar-item">
            <form method="get" action="/search">
              <div class="field has-addons">
                <div class="control searchBox">
                  <input
                    type="text"
                    class="input"
                    placeholder="What are you looking for?"
                    name="query"
                  />
                </div>

                <div class="control">
                  <button class="button is-success">
                    <span class="icon">
                      <i class="fas fa-search"></i>
                    </span>
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>

        <div class="navbar-end ml-0">
          <!-- <router-link to="/summer" class="navbar-item">Summer</router-link>
          <router-link to="/winter" class="navbar-item">Winter</router-link> -->

          <div class="navbar-item">
            <div class="buttons">
              <template v-if="$store.state.isAuthenticated">
                <router-link to="/admin" class="button is-light"
                 v-if="$store.state.userRole == 'ADMIN'"
                  >My account</router-link
                >

                <router-link class="button is-light"
                v-if="$store.state.userRole == 'SHOP'"
                to="/shop"
                  >My account</router-link
                >

                <router-link class="button is-light"
                v-if="$store.state.userRole == 'CUSTOMER'"
                to="/customer"
                  >My account</router-link
                >

                <button @click="logout()" class="button is-danger">Log out</button>
              
                

              </template>

              <template v-else>
                <router-link to="/login" class="button is-light"
                  >Log in</router-link
                >
                <router-link to="/signup" class="button is-light"
                  >Sign up</router-link
                >

              </template>

              <router-link to="/cart" class="button is-success">
                <span class="icon"><i class="fas fa-shopping-cart"></i></span>
                <span>Cart ({{ cartTotalLength }})</span>
              </router-link>

              <template v-if="$store.state.isAuthenticated">
                  <div class="profile-dp-div">
              <img
                class="profile-dp"
                src="http://www.thegurughantal.com/uploads/7/5/8/2/75825867/delhinightclubs-5-bwxyimsnzqm_orig.jpg"
              />
            </div>
              </template>

            </div>
          </div>
        </div>
      </div>
    </nav>

    <div
      class="is-loading-bar has-text-centered"
      v-bind:class="{ 'is-loading': $store.state.isLoading }"
    >
      <div class="lds-dual-ring"></div>
    </div>

    <!-- I added 'style-section' class below in order to edit it's properties in other templates, if I want.
        But, when I changed the style properties, it reflects in every other page as well. That's a problem. -->
    <section class="section style-section">
      <router-view />
    </section>

    <footer class="footer">
      <p class="has-text-centered">CloudStore</p>
    </footer>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      showMobileMenu: false,
      cart: {
        items: [],
      },
    };
  },
  beforeCreate() {
    this.$store.commit("initializeStore");

    const token = this.$store.state.token;

    if (token) {
      axios.defaults.headers.common["Authorization"] = "Bearer " + token;
    } else {
      axios.defaults.headers.common["Authorization"] = "";
    }
  },
  mounted() {
    this.cart = this.$store.state.cart;
  },
  computed: {
    cartTotalLength() {
      let totalLength = 0;

      for (let i = 0; i < this.cart.items.length; i++) {
        totalLength += this.cart.items[i].quantity;
      }

      return totalLength;
    },
  },

  methods: {
    logout() {
            axios.defaults.headers.common["Authorization"] = ""

            localStorage.removeItem("token")
            localStorage.removeItem("username")
            localStorage.removeItem("userid")

            this.$store.commit('removeToken')

            this.$router.push('/')
        },
  }

};
</script>

<style lang="scss">
@import "../node_modules/bulma";

.profile-dp {
  width: max-width;
  height: max-width;
  border-radius: 50%;
}

.lds-dual-ring {
  display: inline-block;
  width: 80px;
  height: 80px;
}
.lds-dual-ring:after {
  content: " ";
  display: block;
  width: 64px;
  height: 64px;
  margin: 8px;
  border-radius: 50%;
  border: 6px solid #ccc;
  border-color: #ccc transparent #ccc transparent;
  animation: lds-dual-ring 1.2s linear infinite;
}
@keyframes lds-dual-ring {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

.is-loading-bar {
  height: 0;
  overflow: hidden;

  -webkit-transition: all 0.3s;
  transition: all 0.3s;

  &.is-loading {
    height: 80px;
  }
}

.searchBox {
  width: 500px;
}
</style>
