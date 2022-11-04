<template>
  <div class="page-checkout">
    <div class="columns is-multiline">
      <div class="column is-12">
        <h1 class="title">Checkout</h1>
      </div>

      <div class="column is-12 box">
        <table class="table is-fullwidth">
          <thead>
            <tr>
              <th>Product</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Total</th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="item in cart.items" v-bind:key="item.product.id">
              <td>{{ item.product.prodName }}</td>
              <td>₹{{ item.product.price }}</td>
              <td>{{ item.quantity }}</td>
              <td>₹{{ getItemTotal(item).toFixed(2) }}</td>
            </tr>
          </tbody>

          <tfoot>
            <tr>
              <td colspan="2">Total</td>
              <td>{{ cartTotalLength }}</td>
              <td>₹{{ cartTotalPrice.toFixed(2) }}</td>
            </tr>
          </tfoot>
        </table>
      </div>

      <div class="column is-12 box">
        <h2 class="subtitle">Shipping details</h2>

        <p class="has-text-grey mb-4">* All fields are required</p>

        <div class="columns is-multiline">
          <div class="column is-6">
            <div class="field">
              <label>First name*</label>
              <div class="control">
                <input type="text" class="input" v-model="first_name" />
              </div>
            </div>

            <div class="field">
              <label>Last name*</label>
              <div class="control">
                <input type="text" class="input" v-model="last_name" />
              </div>
            </div>

            <div class="field">
              <label>E-mail*</label>
              <div class="control">
                <input type="email" class="input" v-model="email" />
              </div>
            </div>

            <div class="field">
              <label>Phone*</label>
              <div class="control">
                <input type="text" class="input" v-model="phone" />
              </div>
            </div>
          </div>

          <div class="column is-6">
            <div class="field">
              <label>House Name*</label>
              <div class="control">
                <input type="text" class="input" v-model="address" />
              </div>
            </div>

            <div class="field">
              <label>Zip code*</label>
              <div class="control">
                <input type="text" class="input" v-model="zipcode" />
              </div>
            </div>

            <div class="field">
              <label>Place*</label>
              <div class="control">
                <input type="text" class="input" v-model="place" />
              </div>
            </div>
          </div>
        </div>

        <div class="notification is-danger mt-4" v-if="errors.length">
          <p v-for="error in errors" v-bind:key="error">{{ error }}</p>
        </div>

        <hr />

        <div id="card-element" class="mb-5"></div>

        <template v-if="cartTotalLength">
          <hr />

          <button class="button is-dark" @click="submitForm">
            Pay with PayTm
          </button>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Checkout",
  data() {
    return {
      cart: {
        items: [],
      },
      stripe: {},
      card: {},
      first_name: "",
      last_name: "",
      email: "",
      phone: "",
      address: "",
      zipcode: "",
      place: "",
      errors: [],

      addressIdReturned: "",
      orderIdReturned: "",
      tokenReturned: "",
      totalAmtReturned: "",

    };
  },
  mounted() {
    document.title = "Checkout | Djackets";

    this.cart = this.$store.state.cart;

    if (this.cartTotalLength > 0) {
      //  ------------- Paytm Code Starts ----------------------------
      var MID = "RUoKbo86921897956224";
      var URL =
        "https://securegw-stage.paytm.in/merchantpgpui/checkoutjs/RUoKbo86921897956224";
      var merchantCallback = null;
      var createDOMElements = function (input) {
        var scriptEle = document.createElement("script"),
          cssEle = document.createElement("link");

        if (cssEle) {
          cssEle.href = input.style;
          cssEle.rel = "stylesheet";
          cssEle.type = "text/css";
          document.head.appendChild(cssEle);
        }
        if (scriptEle) {
          scriptEle.async = true;
          scriptEle.src = input.js;
          scriptEle.type = "application/javascript";
          scriptEle.crossOrigin = "anonymous";
          scriptEle.onload = function () {
            if (window.CheckoutJS) {
              if (window.Paytm && window.Paytm.CheckoutJS) {
                for (var key in window.CheckoutJS) {
                  if (window.CheckoutJS.hasOwnProperty(key)) {
                    window.Paytm.CheckoutJS[key] = window.CheckoutJS[key];
                  }
                }
              }
              try {
                delete window.CheckoutJS; // remove CheckoutJS from window scope
              } catch (e) {}
              if (window.Paytm.CheckoutJS.initLib) {
                window.Paytm.CheckoutJS.initLib(MID).then(function () {
                  if (merchantCallback) {
                    merchantCallback.call();
                  }
                });
              }
            }
          };
          document.body.appendChild(scriptEle);
        }
      };

      var post = function () {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
          if (this.readyState === 4) {
            var data = JSON.parse(this.responseText);
            createDOMElements(data);
          }
        };
        xhr.open("GET", URL, true);
        xhr.setRequestHeader(
          "Content-Type",
          "application/javascript; charset=utf-8"
        );
        xhr.send(null);
      };

      post();

      if (!window.Paytm) {
        // check if window.Paytm exists or not
        window.Paytm = {};
      }

      window.Paytm.CheckoutJS = {
        onLoad: function (callback) {
          if (!callback || callback.constructor !== Function) {
            throw new Error(
              "callback in onLoad function should be of function type"
            );
          }
          merchantCallback = callback;
        },
      };
    }
    // --------------------Paytm code ends ------------------------------
  },
  methods: {
    getItemTotal(item) {
      return item.quantity * item.product.price;
    },
    submitForm() {
      this.errors = [];

      if (this.first_name === "") {
        this.errors.push("The first name field is missing!");
      }

      if (this.last_name === "") {
        this.errors.push("The last name field is missing!");
      }

      if (this.email === "") {
        this.errors.push("The email field is missing!");
      }

      if (this.phone === "") {
        this.errors.push("The phone field is missing!");
      }

      if (this.address === "") {
        this.errors.push("The address field is missing!");
      }

      if (this.zipcode === "") {
        this.errors.push("The zip code field is missing!");
      }

      if (this.place === "") {
        this.errors.push("The place field is missing!");
      }

      //   if (!this.errors.length) {
      //     this.$store.commit("setIsLoading", true);

      //     this.stripe.createToken(this.card).then((result) => {
      //       if (result.error) {
      //         this.$store.commit("setIsLoading", false);

      //         this.errors.push(
      //           "Something went wrong with Stripe. Please try again"
      //         );

      //         console.log(result.error.message);
      //       } else {
      //         this.stripeTokenHandler(result.token);
      //       }
      //     });
      //   }

      if (!this.errors.length) {
        this.$store.commit("setIsLoading", true);

        this.storeAddress();

        this.createOrder();

        // this.paytmJsCheckout();
      }
    },

    storeAddress(deliveryAddress) {
      var deliveryAddress = {
        fullName: this.first_name + this.last_name,
        email: this.email,
        mobile: this.phone,
        houseName: this.address,
        place: this.place,
        pincode: this.zipcode,
      };
      axios
        .post("/user/customer/deliveryaddress", deliveryAddress)
        .then((response) => {
          console.log(response);
          this.addressIdReturned = response.data;
          console.log(this.addressIdReturned);
          this.$store.commit("setAddressId", this.addressIdReturned);
        });
    },

    createOrder(){
        const orderProductList = [];
        for(let i = 0; i < this.cart.items.length; i++){
            const item = this.cart.items[i];
            const obj = {
                prodId: item.product.id,
                shopId: item.currentShopId,
                quantity: item.quantity,
                price: item.product.price * item.quantity,
            };

            orderProductList.push(obj);
        }

        var orderModel = {
            orderProductList: orderProductList,
            addressId: this.$store.state.addressId
            // addressId: this.addressIdReturned
        }

        axios
        .post("/user/customer/payment", orderModel)
        .then((response) => {
            console.log(response)
            this.tokenReturned = response.data.body.txnToken;
            this.orderIdReturned = response.data.orderId;
            console.log(this.tokenReturned);

            this.paytmJsCheckout(this.tokenReturned, this.orderIdReturned)
            // CacheStorage.setItem
            // localStorage.setItem("txnToken")
        })

    },

    paytmJsCheckout(txnToken, orderId){

// ==============================================================================

    if (this.cartTotalLength > 0) {
      //  ------------- Paytm Code Starts ----------------------------
    //   This is stage 
    //   var MID = "RUoKbo86921897956224";
//  This is Prod
      var MID = 'SApWAl73540535701479';

    //   This is stage
    //   var URL =
    //     "https://securegw-stage.paytm.in/merchantpgpui/checkoutjs/RUoKbo86921897956224";

        // This is prod
        var URL =
        "https://securegw.paytm.in/merchantpgpui/checkoutjs/SApWAl73540535701479";
      var merchantCallback = null;
      var createDOMElements = function (input) {
        var scriptEle = document.createElement("script"),
          cssEle = document.createElement("link");

        if (cssEle) {
          cssEle.href = input.style;
          cssEle.rel = "stylesheet";
          cssEle.type = "text/css";
          document.head.appendChild(cssEle);
        }
        if (scriptEle) {
          scriptEle.async = true;
          scriptEle.src = input.js;
          scriptEle.type = "application/javascript";
          scriptEle.crossOrigin = "anonymous";
          scriptEle.onload = function () {
            if (window.CheckoutJS) {
              if (window.Paytm && window.Paytm.CheckoutJS) {
                for (var key in window.CheckoutJS) {
                  if (window.CheckoutJS.hasOwnProperty(key)) {
                    window.Paytm.CheckoutJS[key] = window.CheckoutJS[key];
                  }
                }
              }
              try {
                delete window.CheckoutJS; // remove CheckoutJS from window scope
              } catch (e) {}
              if (window.Paytm.CheckoutJS.initLib) {
                window.Paytm.CheckoutJS.initLib(MID).then(function () {
                  if (merchantCallback) {
                    merchantCallback.call();
                  }
                });
              }
            }
          };
          document.body.appendChild(scriptEle);
        }
      };

      var post = function () {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
          if (this.readyState === 4) {
            var data = JSON.parse(this.responseText);
            createDOMElements(data);
          }
        };
        xhr.open("GET", URL, true);
        xhr.setRequestHeader(
          "Content-Type",
          "application/javascript; charset=utf-8"
        );
        xhr.send(null);
      };

      post();

      if (!window.Paytm) {
        // check if window.Paytm exists or not
        window.Paytm = {};
      }

      window.Paytm.CheckoutJS = {
        onLoad: function (callback) {
          if (!callback || callback.constructor !== Function) {
            throw new Error(
              "callback in onLoad function should be of function type"
            );
          }
          merchantCallback = callback;
        },
      };
    }


// ===============================================================================





        var config = {
            root: "",
            flow: "DEFAULT",
            data: {
                orderId: orderId,
                token: txnToken,
                tokenType: "TXN_TOKEN",
                amount: this.cartTotalPrice
            },
            handler: {
                notifyMerchant: function(eventName, data) {
                    console.log("notifyMerchant handler function called");
                    console.log("eventName => ", eventName);
                    console.log("data => ", data);
                }
            }
        };

        if (window.Paytm && window.Paytm.CheckoutJS) {
            window.Paytm.CheckoutJS.onLoad(function excecuteAfterCompleteLoad() {
                // initialze configuration using init method
                window.Paytm.CheckoutJS.init(config).then(function onSuccess() {
                    // after successfully updating configuration, invoke JS Checkout
                    window.Paytm.CheckoutJS.invoke();
                }).catch(function onError(error) {
                    console.log("error => ", error);
                });
            });
        }

        console.log("Transaction Token: " + txnToken);

        this.$store.commit("setIsLoading", false);

        this.$store.commit("clearCart");
          // this.$router.push("/cart/success");
    },

    // async stripeTokenHandler(token) {
    //   const items = [];

    //   for (let i = 0; i < this.cart.items.length; i++) {
    //     const item = this.cart.items[i];
    //     const obj = {
    //       product: item.product.id,
    //       quantity: item.quantity,
    //       price: item.product.price * item.quantity,
    //     };

    //     items.push(obj);
    //   }

    //   const data = {
    //     first_name: this.first_name,
    //     last_name: this.last_name,
    //     email: this.email,
    //     address: this.address,
    //     zipcode: this.zipcode,
    //     place: this.place,
    //     phone: this.phone,
    //     items: items,
    //     stripe_token: token.id,
    //   };

    //   await axios
    //     .post("/api/v1/checkout/", data)
    //     .then((response) => {
    //       this.$store.commit("clearCart");
    //       this.$router.push("/cart/success");
    //     })
    //     .catch((error) => {
    //       this.errors.push("Something went wrong. Please try again");

    //       console.log(error);
    //     });

    //   this.$store.commit("setIsLoading", false);
    // },

  },
  computed: {
    cartTotalPrice() {
      return this.cart.items.reduce((acc, curVal) => {
        return (acc += curVal.product.price * curVal.quantity);
      }, 0);
    },
    cartTotalLength() {
      return this.cart.items.reduce((acc, curVal) => {
        return (acc += curVal.quantity);
      }, 0);
    },
  },
};
</script>