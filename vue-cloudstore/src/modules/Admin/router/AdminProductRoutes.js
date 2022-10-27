import ViewAdminProducts from "@/modules/Admin/views/AdminProducts/ViewAdminProducts.vue";
import AddAdminProducts from "@/modules/Admin/views/AdminProducts/AddAdminProducts.vue";
import EditAdminProducts from "@/modules/Admin/views/AdminProducts/EditAdminProducts.vue";
import EnableAdminProducts from "@/modules/Admin/views/AdminProducts/EnableAdminProducts.vue";
import DisableAdminProducts from "@/modules/Admin/views/AdminProducts/DisableAdminProducts.vue";
import AddAdminProductCategory from "@/modules/Admin/views/AdminProducts/AddAdminProductCategory.vue";

export const adminProductRoutes = [
  {
    path: "products",
    children: [
      {
        path: "view",
        name: "admin-product-view",
        components: {
          adminPage: ViewAdminProducts,
        },
      },
      {
        path: "add",
        name: "admin-product-add",
        components: {
          adminPage: AddAdminProducts,
        },
      },
      {
        path: "edit",
        name: "admin-product-edit",
        components: {
          adminPage: EditAdminProducts,
        },
      },
      {
        path: "disable",
        name: "admin-product-disable",
        components: {
          adminPage: DisableAdminProducts,
        },
      },
      {
        path: "enable",
        name: "admin-product-enable",
        components: {
          adminPage: EnableAdminProducts,
        },
      },
      {
        path: "category",
        name: "shop-product-category-add",
        components: {
          adminPage: AddAdminProductCategory,
        },
      },
    ],
  },
];
