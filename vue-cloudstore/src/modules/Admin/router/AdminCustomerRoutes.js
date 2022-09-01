import ViewAdminCustomers from "@/modules/Admin/views/AdminCustomers/ViewAdminCustomers.vue"
import AddAdminCustomers from "@/modules/Admin/views/AdminCustomers/AddAdminCustomers.vue"
import EditAdminCustomers from "@/modules/Admin/views/AdminCustomers/EditAdminCustomers.vue";
import DisableAdminCustomers from "@/modules/Admin/views/AdminCustomers/DisableAdminCustomers.vue";
import EnableAdminCustomers from "@/modules/Admin/views/AdminCustomers/EnableAdminCustomers.vue";

export const adminCustomerRoutes = [
  {
    path: "customers",
    // components: {
    //     adminPage: ViewAdminCustomers
    // },
    children: [
      {
        path: "view",
        name: "admin-customer-view",
        components: {
          adminPage: ViewAdminCustomers,
        },
      },
      {
        path: "add",
        name: "admin-customer-add",
        components: {
          adminPage: AddAdminCustomers,
        },
      },
      {
        path: "edit",
        name: "admin-customer-edit",
        components: {
          adminPage: EditAdminCustomers,
        },
      },
      {
        path: "disable",
        name: "admin-customer-disable",
        components: {
          adminPage: DisableAdminCustomers,
        },
      },
      {
        path: "enable",
        name: "admin-customer-enable",
        components: {
          adminPage: EnableAdminCustomers,
        },
      },
    ],
  },
];