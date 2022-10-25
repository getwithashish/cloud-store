import Customer from "@/modules/Customer/Customer.vue"
import CustomerDashboard from "@/modules/Customer/views/DashboardCustomer.vue"
import CustomerProfile from "@/modules/Customer/views/ProfileCustomer.vue";
import CustomerTransactions from "@/modules/Customer/views/TransactionsCustomer.vue";
import CustomerLog from "@/modules/Customer/views/LogCustomer.vue";
import CustomerSettings from "@/modules/Customer/views/SettingsCustomer.vue";


export const customerRoutes = [
  {
    path: "/customer",
    component: Customer,
    children: [
      {
        path: "",
        name: "customer-dashboard",
        components: {
          customerPage: CustomerDashboard,
        },
      },
      // {
      //   path: "dashboard",
      //   name: "customer-dashboard",
      //   components: {
      //     customerPage: CustomerDashboard,
      //   },
      // },
      {
        path: "profile",
        name: "customer-profile",
        components: {
          customerPage: CustomerProfile,
        },
      },
      {
        path: "transactions",
        name: "customer-transactions",
        components: {
          customerPage: CustomerTransactions,
        },
      },
      {
        path: "log",
        name: "customer-log",
        components: {
          customerPage: CustomerLog,
        },
      },
      {
        path: "settings",
        name: "customer-settings",
        components: {
          customerPage: CustomerSettings,
        },
      },
    ],
  },
];
