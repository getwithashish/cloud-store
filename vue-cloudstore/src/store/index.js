import { createStore } from 'vuex'

export default createStore({
  state: {
    cart: {
        items: [],
    },
    isAuthenticated: false,
    token: '',
    isLoading: false,
    
    adminCustomersDetails: [],
    adminShopsDetails: [],
    userRole: '',
    userId: '',

    addressId: "",
    txnToken: "",

    shopIdsForProduct: [],
    selectedProduct: {},
    
    custPincode: ""

  },
  mutations: {
    initializeStore(state) {
      if (localStorage.getItem('cart')) {
        state.cart = JSON.parse(localStorage.getItem('cart'))
      } else {
        localStorage.setItem('cart', JSON.stringify(state.cart))
      }

      if (localStorage.getItem('token')) {
          state.token = localStorage.getItem('token')
          state.isAuthenticated = true
      } else {
          state.token = ''
          state.isAuthenticated = false
        
        state.adminCustomersDetails = []
        state.adminShopsDetails = []
        
      } 
    },
    addToCart(state, item) {
      const exists = state.cart.items.filter(i => i.product.id === item.product.id)
      if (exists.length) {
        exists[0].quantity = parseInt(exists[0].quantity) + parseInt(item.quantity)
      } else {
        state.cart.items.push(item)
      }

      localStorage.setItem('cart', JSON.stringify(state.cart))
    },
    setIsLoading(state, status) {
      state.isLoading = status
    },
    setToken(state, token) {
        state.token = token
        state.isAuthenticated = true
    },  
    removeToken(state) {
        state.token = ''
        state.isAuthenticated = false
      
        state.adminCustomersDetails = []
      
    },
    clearCart(state) {
      state.cart = { items: [] }

      localStorage.setItem('cart', JSON.stringify(state.cart))
    },

    setAdminCustomerDetails(state, details) {
        state.adminCustomersDetails = details
    },

    setAdminShopDetails(state, details) {
        state.adminShopsDetails = details
    },

    setUserRole(state, role) {
      state.userRole = role
    },

    setUserId(state, id) {
      state.userId = id
      localStorage.setItem("userid", id);
    },

    setAddressId(state, addressId) {
      state.addressId = addressId
    },

    setTxnToken(state, txnToken) {
      state.txnToken = txnToken
    },

    setShopIdsForProduct(state, shopIds) {
      state.shopIdsForProduct = shopIds
    },

    setSelectedProduct(state, selectedProduct) {
      state.selectedProduct = selectedProduct
    },

    setCustPincode(state, pincode) {
      state.custPincode = pincode
    }

  },
  actions: {
  },
  modules: {
  }
})
