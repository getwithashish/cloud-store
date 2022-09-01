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
        path: "",
        name: "shop-dashboard",
        components: {
          customerPage: ShopDashboard,
        },
      },
      {
        path: "profile",
        name: "shop-profile",
        components: {
          customerPage: ShopProfile,
        },
      },
      {
        path: "transactions",
        name: "shop-transactions",
        components: {
          customerPage: ShopTransactions,
        },
      },
      {
        path: "log",
        name: "shop-log",
        components: {
          customerPage: ShopLog,
        },
      },
      {
        path: "settings",
        name: "shop-settings",
        components: {
          customerPage: ShopSettings,
        },
      },
    ],
  },
];
