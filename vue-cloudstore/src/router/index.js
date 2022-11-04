import { createRouter, createWebHistory } from 'vue-router'

import store from '../store'

import Home from '../views/Home.vue'

import Product from '../views/Product.vue'
import Category from '../views/Category.vue'
import Search from '../views/Search.vue'
import Cart from '../views/Cart.vue'
import MyAccount from '../views/MyAccount.vue'
import Checkout from '../views/Checkout.vue'
import Success from '../views/Success.vue'
import Failure from '../views/Failure.vue'
import UserVerificationFailed from '../views/UserVerificationFailed.vue'

import Location from '../LocationTest.vue'

import { authenticationRoutes } from '@/modules/Authentication/router/authenticationRoutes'
import { adminRoutes } from "@/modules/Admin/router/AdminRoutes"
import { customerRoutes } from '@/modules/Customer/router/CustomerRoutes'
import { shopRoutes } from '@/modules/Shop/router/ShopRoutes'

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },

  {
    path: "/my-account",
    name: "MyAccount",
    component: MyAccount,
    meta: {
      requireLogin: true,
    },
  },
  {
    path: "/search",
    name: "Search",
    component: Search,
  },
  {
    path: "/cart",
    name: "Cart",
    component: Cart,
  },
  {
    path: "/cart/success",
    name: "Success",
    component: Success,
  },
  {
    path: "/cart/failure",
    name: "Failure",
    component: Failure,
  },
  {
    path: "/verification/failure",
    name: "userVerificationFailed",
    component: UserVerificationFailed,
  },
  {
    path: "/cart/checkout",
    name: "Checkout",
    component: Checkout,
    meta: {
      // requireLogin: true,
    },
  },
  // {
  //   path: "/:category_slug/:product_slug",
  //   name: "Product",
  //   component: Product,
  // },

  // {
  //   path: "/:category_slug",
  //   name: "Category",
  //   component: Category,
  // },

  {
    path: "/category",
    name: "Category",
    component: Category,
  },

  {
    path: "/product",
    name: "Product",
    component: Product,
  },

  {
    path: "/location",
    name: "LocationGeo",
    component: Location,
  },

  // Routes for authentication module
  ...authenticationRoutes,

  // Routes for admin module
  ...adminRoutes,

  // Routes for customer module
  ...customerRoutes,

  // Routes for shop module
  ...shopRoutes,
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requireLogin) && !store.state.isAuthenticated) {
    next({ name: 'LogIn', query: { to: to.path } });
  } else {
    next()
  }
})

export default router
