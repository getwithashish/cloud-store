import Admin from "@/modules/Admin/Admin.vue";
import AdminProfile from "@/modules/Admin/views/AdminProfile.vue";
import { adminCustomerRoutes } from "./AdminCustomerRoutes";
import { adminShopRoutes } from "./AdminShopRoutes";

export const adminRoutes = [
  {
    path: "/admin",
    component: Admin,
    children: [
      {
        path: "profile",
        components: {
          adminPage: AdminProfile,
        },
      },
     
        ...adminCustomerRoutes,
      ...adminShopRoutes
        
    ],
  },
];

// export adminRoutes
