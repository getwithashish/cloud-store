import Admin from "@/modules/Admin/Admin.vue";
import AdminDashboard from "@/modules/Admin/views/DashboardAdmin.vue";
import AdminProfile from "@/modules/Admin/views/ProfileAdmin.vue";

import { adminCustomerRoutes } from "./AdminCustomerRoutes";
import { adminShopRoutes } from "./AdminShopRoutes";

export const adminRoutes = [
  {
    path: "/admin",
    component: Admin,
    children: [
      {
        path: "dashboard",
        components: {
          adminPage: AdminDashboard,
        },
      },
      {
        path: "profile",
        components: {
          adminPage: AdminProfile,
        },
      },

      ...adminCustomerRoutes,
      ...adminShopRoutes,
    ],
  },
];

// export adminRoutes
