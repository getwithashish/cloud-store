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
        name: "admin-shop-view",
        components: {
          adminPage: ViewAdminShops,
        },
      },
      {
        path: "add",
        name: "admin-shop-add",
        components: {
          adminPage: AddAdminShops,
        },
      },
      {
        path: "edit",
        name: "admin-shop-edit",
        components: {
          adminPage: EditAdminShops,
        },
      },
      {
        path: "disable",
        name: "admin-shop-disable",
        components: {
          adminPage: DisableAdminShops,
        },
      },
      {
        path: "enable",
        name: "admin-shop-enable",
        components: {
          adminPage: EnableAdminShops,
        },
      },
    ],
  },
];
