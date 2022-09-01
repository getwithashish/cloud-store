import Shop from "@/modules/Shop/Shop.vue";
import ShopDashboard from "@/modules/Shop/views/DashboardShop.vue";
import ShopProfile from "@/modules/Shop/views/ProfileShop.vue";
import ShopTransactions from "@/modules/Shop/views/TransactionsShop.vue";
import ShopLog from "@/modules/Shop/views/LogShop.vue";
import ShopSettings from "@/modules/Shop/views/SettingsShop.vue";

export const shopRoutes = [
  {
    path: "/shop",
    component: Shop,
    children: [
      {
        path: "dashboard",
        components: {
          customerPage: ShopDashboard,
        },
      },
      {
        path: "profile",
        components: {
          customerPage: ShopProfile,
        },
      },
      {
        path: "transactions",
        components: {
          customerPage: ShopTransactions,
        },
      },
      {
        path: "log",
        components: {
          customerPage: ShopLog,
        },
      },
      {
        path: "settings",
        components: {
          customerPage: ShopSettings,
        },
      },
    ],
  },
];
