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
        path: "dashboard",
        components: {
          customerPage: CustomerDashboard,
        },
      },
      {
        path: "profile",
        components: {
          customerPage: CustomerProfile,
        },
      },
      {
        path: "transactions",
        components: {
          customerPage: CustomerTransactions,
        },
      },
      {
        path: "log",
        components: {
          customerPage: CustomerLog,
        },
      },
      {
        path: "settings",
        components: {
          customerPage: CustomerSettings,
        },
      },
    ],
  },
];
