import ViewAdminShops from "@/modules/Admin/views/AdminShops/ViewAdminShops.vue"
import AddAdminShops from "@/modules/Admin/views/AdminShops/AddAdminShops.vue";
import EditAdminShops from "@/modules/Admin/views/AdminShops/EditAdminShops.vue";
import EnableAdminShops from "@/modules/Admin/views/AdminShops/EnableAdminShops.vue";
import DisableAdminShops from "@/modules/Admin/views/AdminShops/DisableAdminShops.vue";

export const adminShopRoutes = [
  {
    path: "shops",
    children: [
      {
        path: "view",
        components: {
          adminPage: ViewAdminShops,
        },
      },
      {
        path: "add",
        components: {
          adminPage: AddAdminShops,
        },
      },
      {
        path: "edit",
        components: {
          adminPage: EditAdminShops,
        },
      },
      {
        path: "disable",
        components: {
          adminPage: DisableAdminShops,
        },
      },
      {
        path: "enable",
        components: {
          adminPage: EnableAdminShops,
        },
      },
    ],
  },
];
