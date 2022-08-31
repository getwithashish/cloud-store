import { createRouter, createWebHistory } from 'vue-router'

import store from '../store'

import Home from '../views/Home.vue'

import Product from '../views/Product.vue'
import Category from '../views/Category.vue'
import Search from '../views/Search.vue'
import Cart from '../views/Cart.vue'
import SignUp from '../views/SignUp.vue'
import LogIn from '../views/LogIn.vue'
import MyAccount from '../views/MyAccount.vue'
import Checkout from '../views/Checkout.vue'
import Success from '../views/Success.vue'

import AdminCustomer from '../modules/Admin/views/AdminCustomers/ViewAdminCustomers.vue'
import Admin from '../views/AdminPanel.vue'
import AdminEditCustomers from '../modules/Admin/views/AdminCustomers/EditAdminCustomers.vue'
import AdminAddCustomers from '../modules/Admin/views/AdminCustomers/AddAdminCustomers.vue'
import AdminDisableCustomers from '../modules/Admin/views/AdminCustomers/DisableAdminCustomers.vue'
import AdminEnableCustomers from '../modules/Admin/views/AdminCustomers/EnableAdminCustomers.vue'
import AdminShop from '../modules/Admin/views/AdminShops/ViewAdminShops.vue'
import AdminAddShops from '../modules/Admin/views/AdminShops/AddAdminShops.vue'
import AdminEnableShops from '../modules/Admin/views/AdminShops/EnableAdminShops.vue'
import AdminDisableShops from '../modules/Admin/views/AdminShops/DisableAdminShops.vue'
import AdminEditShops from '../modules/Admin/views/AdminShops/EditAdminShops.vue'

import { adminRoutes } from "@/modules/Admin/router/AdminRoutes"

const routes = [

  // {
  //   path: '/adminpanel',
  //   name: 'AdminPanel',
  //   component: Admin
  // },

  // {
  //   path: '/admin/view/customers',
  //   name: 'AdminCustomer',
  //   component: AdminCustomer
  // },

  // {
  //   path: '/admin/add/customers',
  //   name: 'AdminAddCustomers',
  //   component: AdminAddCustomers
  // },

  // {
  //   path: '/admin/edit/customers',
  //   name: 'AdminEditCustomers',
  //   component: AdminEditCustomers
  // },

  // {
  //   path: '/admin/disable/customers',
  //   name: 'AdminDisableCustomers',
  //   component: AdminDisableCustomers
  // },

  // {
  //   path: '/admin/enable/customers',
  //   name: 'AdminEnableCustomers',
  //   component: AdminEnableCustomers
  // },

  // {
  //   path: '/admin/view/shops',
  //   name: 'AdminShop',
  //   component: AdminShop
  // },

  // {
  //   path: '/admin/add/shops',
  //   name: 'AdminAddShops',
  //   component: AdminAddShops
  // },

  // {
  //   path: '/admin/disable/shops',
  //   name: 'AdminDisableShops',
  //   component: AdminDisableShops
  // },

  // {
  //   path: '/admin/enable/shops',
  //   name: 'AdminEnableShops',
  //   component: AdminEnableShops
  // },

  // {
  //   path: '/admin/edit/shops',
  //   name: 'AdminEditShops',
  //   component: AdminEditShops
  // },

  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/sign-up',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/log-in',
    name: 'LogIn',
    component: LogIn
  },
  {
    path: '/my-account',
    name: 'MyAccount',
    component: MyAccount,
    meta: {
        requireLogin: true
    }
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
  {
    path: '/cart',
    name: 'Cart',
    component: Cart
  },
  {
    path: '/cart/success',
    name: 'Success',
    component: Success
  },
  {
    path: '/cart/checkout',
    name: 'Checkout',
    component: Checkout,
    meta: {
        requireLogin: true
    }
  },
  {
    path: '/:category_slug/:product_slug',
    name: 'Product',
    component: Product
  },
  {
    path: '/:category_slug',
    name: 'Category',
    component: Category
  },

  ...adminRoutes
  

]

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
