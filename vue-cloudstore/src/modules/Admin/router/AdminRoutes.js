import Admin from "@/modules/Admin/Admin.vue";
import AdminDashboard from "@/modules/Admin/views/DashboardAdmin.vue";
import AdminProfile from "@/modules/Admin/views/ProfileAdmin.vue";

import { adminCustomerRoutes } from "./AdminCustomerRoutes";
import { adminShopRoutes } from "./AdminShopRoutes";
import { adminProductRoutes } from "./AdminProductRoutes";

export const adminRoutes = [
  {
    path: "/admin",
    component: Admin,
    children: [
      {
        path: "",
        name: "admin-dashboard",
        components: {
          adminPage: AdminDashboard,
        },
      },
      // {
      //   path: "dashboard",
      //   name: "admin-dashboard",
      //   components: {
      //     adminPage: AdminDashboard,
      //   },
      // },
      {
        path: "profile",
        name: "admin-profile",
        components: {
          adminPage: AdminProfile,
        },
      },

      ...adminCustomerRoutes,
      ...adminShopRoutes,
      ...adminProductRoutes,
    ],
  },
];

// export adminRoutes
